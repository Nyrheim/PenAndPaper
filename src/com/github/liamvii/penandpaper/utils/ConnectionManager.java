package com.github.liamvii.penandpaper.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.bukkit.Bukkit.getLogger;

public class ConnectionManager {

    public static Connection connectDB(String url, String username, String password) {
        Connection connection;
        try { //We use a try catch to avoid errors.
            Class.forName("com.mysql.jdbc.Driver"); //this accesses the JDBC driver.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("JDBC driver unavailable. You have removed the driver, somehow.");
            return null;
        }
        try { //Another try catch to get any SQL errors (for example connection errors)
            // We attempt a connection through JDBC using the details provided in config.yml.
            connection = DriverManager.getConnection(url, username, password);
            getLogger().info("Connection to database successful.");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void disconnectDB(Connection connection) {
        // invoke on disable.
        try { //using a try catch to catch connection errors (like wrong sql password...)
            if (connection != null && !connection.isClosed()) { //checking if connection isn't null to
                //avoid receiving a nullpointer
                connection.close(); //closing the connection field variable.
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}