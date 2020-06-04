package net.nyrheim.penandpaper.database;

import net.nyrheim.penandpaper.PenAndPaper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import net.nyrheim.penandpaper.database.table.*;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import static org.jooq.SQLDialect.MYSQL;

public class Database {

    private final PenAndPaper plugin;

    private final DataSource dataSource;
    private final CacheManager cacheManager;

    private final Map<Class<? extends Table>, Table> tables;

    public Database(PenAndPaper plugin, String url, String username, String password) {
        this.plugin = plugin;

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        dataSource = new HikariDataSource(hikariConfig);

        cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build(true);

        tables = new HashMap<>();

        addTable(new CharacterTable(plugin, this));
        addTable(new CharacterAbilityScoreTable(this));
        addTable(new CharacterTempAbilityScoreTable(this));
        addTable(new CharacterAbilityScoreChoiceTable(this));
        addTable(new CharacterClassTable(this));
        addTable(new ActiveCharacterTable(this));
        addTable(new PlayerTable(plugin, this));
    }

    public CacheManager getCacheManager() {
        return cacheManager;
    }

    public DSLContext create() {
        return DSL.using(dataSource, MYSQL);
    }

    public <T extends Table> void addTable(T table) {
        table.create();
        tables.put(table.getClass(), table);
    }

    public <T extends Table> T getTable(Class<T> type) {
        return (T) tables.get(type);
    }

}
