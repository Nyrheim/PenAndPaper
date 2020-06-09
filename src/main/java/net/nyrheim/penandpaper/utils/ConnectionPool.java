package net.nyrheim.penandpaper.utils;

import com.typesafe.config.Config;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionPool {

    private ConnectionPool() {

    }

    public static HikariDataSource getDataSourceFromConfig(Config config) {
        HikariConfig jdbcConfig = new HikariConfig();
        jdbcConfig.setPoolName(config.getString("poolName"));
        jdbcConfig.setMaximumPoolSize(config.getInt("maximumPoolSize"));
        jdbcConfig.setMinimumIdle(config.getInt("minimumIdle"));
        jdbcConfig.setJdbcUrl(config.getString("jdbcUrl"));
        jdbcConfig.setUsername(config.getString("username"));
        jdbcConfig.setPassword(config.getString("password"));

        jdbcConfig.addDataSourceProperty("cachePrepStmts", config.getBoolean("cachePrepStmts"));
        jdbcConfig.addDataSourceProperty("prepStmtCacheSize", config.getInt("prepStmtCacheSize"));
        jdbcConfig.addDataSourceProperty("prepStmtCacheSqlLimit", config.getInt("prepStmtCacheSqlLimit"));
        jdbcConfig.addDataSourceProperty("useServerPrepStmts", config.getBoolean("useServerPrepStmts"));
        jdbcConfig.addDataSourceProperty("readOnly", config.getBoolean("readOnly"));

        return new HikariDataSource(jdbcConfig);
    }



}
