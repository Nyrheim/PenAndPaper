package com.github.liamvii.penandpaper.utils;

import com.typesafe.config.Config;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionPool {

    private ConnectionPool() {

    }

    /*
    Draws values from /resources/application.conf

    Getter method for HikariDataSource, allows us to maintain two connection pools.

    Online documentation I'm referring to talks about separating these into 'OLAP' and 'OLTP' pools.
    For our purposes, these pools will instead be simplified to "write" and "read".

    Assumption here is that we can leverage read-only optimizations, but I'm not sure.

    This may be over-engineering.
     */

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
