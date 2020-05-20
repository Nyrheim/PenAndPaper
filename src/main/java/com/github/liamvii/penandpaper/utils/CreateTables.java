package com.github.liamvii.penandpaper.utils;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.github.liamvii.penandpaper.Pen.connection;

public class CreateTables {

    // Creates the requisite tables for the plugin to run if they don't exist.

    public static void createCharTable(){
        String sql = "CREATE TABLE IF NOT EXISTS characters(ID TINYINT(255) NOT NULL AUTO_INCREMENT, UUID char(36), playerName varchar(16), firstName varchar(32), familyName varchar(32)," +
                "age TINYINT(4), height varchar(20), weight varchar(20), appearance varchar(400), presence varchar(200), race varchar(24), level TINYINT(2), experience TINYINT(255), maxHP TINYINT(255), hpMod TINYINT(10), currentHP TINYINT(2)," +
                "maxHPModLevel TINYINT(2), str_base TINYINT(2), dex_base TINYINT(2), con_base TINYINT(2), int_base TINYINT(2), wis_base TINYINT(2), cha_base TINYINT(2)," +
                "str_mod TINYINT(2), dex_mod TINYINT(2), con_mod TINYINT(2), int_mod TINYINT(2), wis_mod TINYINT(2), cha_mod TINYINT(2), " +
                "str_temp TINYINT(2), dex_temp TINYINT(2), con_temp TINYINT(2), int_temp TINYINT(2), wis_temp TINYINT(2), cha_temp TINYINT(2), " +
                "jobID1 TINYINT(255), jobID2 TINYINT(255), jobID3 TINYINT(255), slotID TINYINT(255), inventory varchar(255)," +
                "PRIMARY KEY (ID));";
        // prepare the statement to be executed
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            // I use executeUpdate() to update the databases table.
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createJobsTable(){
        String sql = "CREATE TABLE IF NOT EXISTS jobs(ID TINYINT(255) NOT NULL AUTO_INCREMENT, jobName varchar(24), charid TINYINT(255)," +
                "jobLevel TINYINT(2), jobHP TINYINT(255), hitDie varchar(10), dieCount TINYINT(255), archetype varchar(32)," +
                "PRIMARY KEY (ID));";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createCharacterHolderTable(){
        String sql = "CREATE TABLE IF NOT EXISTS characterHolder(uuid char(36), playerName varchar(16), dutymode VARCHAR(10), active TINYINT(255)," +
                "charSlot1 TINYINT(255), charSlot2 TINYINT(255), charSlot3 TINYINT(255), storageSlot1 TINYINT(255), storageSlot2 TINYINT(255), " +
                "storageSlot3 TINYINT(255)," +
                "PRIMARY KEY (uuid));";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
