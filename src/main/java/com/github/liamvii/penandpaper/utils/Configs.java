package com.github.liamvii.penandpaper.utils;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigRenderOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class Configs {
    private static final Logger log = LoggerFactory.getLogger(Configs.class);

    private Configs() { }


    private static final Config systemProperties = ConfigFactory.systemProperties();
    private static final Config systemEnvironment = ConfigFactory.systemEnvironment();

    // Always start with a blank config and add fallbacks
    private static final AtomicReference<Config> propertiesRef = new AtomicReference<>(null);

    public static void initProperties(Config config) {
        boolean success = propertiesRef.compareAndSet(null, config);
        if (!success) {
            throw new RuntimeException("propertiesRef Config has already been initialized. This should only be called once.");
        }
    }

    public static Config properties() {
        return propertiesRef.get();
    }

    public static Config systemProperties() {
        return systemProperties;
    }

    public static Config systemEnvironment() {
        return systemEnvironment;
    }

    public static Configs.Builder newBuilder() {
        return new Builder();
    }

    // This should return the current executing user path
    public static String getExecutionDirectory() {
        return systemProperties.getString("user.dir");
    }

    public static <T> T getOrDefault(Config config, String path, BiFunction<Config, String, T> extractor, T defaultValue) {
        if (config.hasPath(path)) {
            return extractor.apply(config, path);
        }
        return defaultValue;
    }

    public static <T> T getOrDefault(Config config, String path, BiFunction<Config, String, T> extractor, Supplier<T> defaultSupplier) {
        if (config.hasPath(path)) {
            return extractor.apply(config, path);
        }
        return defaultSupplier.get();
    }

    /* Not sure what library Seq belongs to. Looks like something to do with Scala - for now, we don't need this method.
    public static Map<String, Object> asMap(Config config) {
        return Seq.seq(config.entrySet())
                .toMap(e -> e.getKey(), e -> e.getValue().unwrapped());
    }
     */

    public static class Builder {
        private Config conf = ConfigFactory.empty();

        public Builder() {
            log.info("Loading configs first row is highest priority, second row is fallback and so on");
        }

        public Builder withResource(String resource) {
            Config resourceConfig = ConfigFactory.parseResources(resource);
            String empty = resourceConfig.entrySet().size() == 0 ? " contains no values" : "";
            conf = conf.withFallback(resourceConfig);
            log.info("Loaded config file from resource ({}){}", resource, empty);
            return this;
        }

        public Builder withSystemProperties() {
            conf = conf.withFallback(systemProperties);
            log.info("Loaded system properties into config");
            return this;
        }

        public Builder withSystemEnvironment() {
            conf = conf.withFallback(systemEnvironment);
            log.info("Loaded system environment into config");
            return this;
        }

        public Builder withOptionalFile(String path) {
            File secureConfFile = new File(path);
            if (secureConfFile.exists()) {
                log.info("Loaded config file from path ({})", path);
                conf = conf.withFallback(ConfigFactory.parseFile(secureConfFile));
            } else {
                log.info("Attempted to load file from path ({}) but it was not found", path);
            }
            return this;
        }

        public Builder withOptionalRelativeFile(String path) {
            return withOptionalFile(getExecutionDirectory() + path);
        }

        public Builder withConfig(Config config) {
            conf = conf.withFallback(config);
            return this;
        }

        public Config build() {
            // Resolve substitutions.
            conf = conf.resolve();
            if (log.isDebugEnabled()) {
                log.debug("Logging properties. Make sure sensitive data such as passwords or secrets are not logged!");
                log.debug(conf.root().render());
            }
            return conf;
        }
    }
}
