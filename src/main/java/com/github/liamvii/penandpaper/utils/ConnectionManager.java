package com.github.liamvii.penandpaper.utils;

import com.github.liamvii.penandpaper.Pen;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.bukkit.plugin.Plugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

public class ConnectionManager {

    private static final Config conf = ConfigFactory.load("src/main/resources/application.conf");

    private enum Write {
        INSTANCE(ConnectionPool.getDataSourceFromConfig(conf.getConfig("pools.write")));
        private final DataSource dataSource;
        private Write(DataSource dataSource) {
            this.dataSource = dataSource;
        }
        public DataSource getDataSource() {
            return dataSource;
        }
    }

    public static DataSource getRead() {
        return Write.INSTANCE.getDataSource();
    }


    private enum Read {
        INSTANCE(ConnectionPool.getDataSourceFromConfig(conf.getConfig("pools.read")));
        private final DataSource dataSource;
        private Read(DataSource dataSource) {
            this.dataSource = dataSource;
        }
        public DataSource getDataSource() {
            return dataSource;
        }
    }

    public static DataSource getWrite() {
        return Read.INSTANCE.getDataSource();

    }
}