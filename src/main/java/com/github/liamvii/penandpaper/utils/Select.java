package com.github.liamvii.penandpaper.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.github.liamvii.penandpaper.Pen.connection;

public class Select {

    public static ArrayList<Integer> selectCharSlots(String sql, String val) {
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, val);
            ResultSet rs = stmt.executeQuery();
            int count = 0;
            ArrayList<Integer> charSlots = new ArrayList<Integer>();
            rs.next();
            int columnCount = rs.getMetaData().getColumnCount();
            for (int column = 0; column < columnCount; column++) {
                charSlots.add(rs.getInt(column+1));
            }
            return charSlots;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<String> deserialize(String sql, String val) {
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, val);
            ResultSet rs = stmt.executeQuery();
            int count = 0;
            ArrayList<String> deserialize = new ArrayList<>();
            rs.next();
            int columnCount = rs.getMetaData().getColumnCount();
            for (int column = 0; column < columnCount; column++) {
                deserialize.add(rs.getString(column+1));
            }
            return deserialize;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int selectInt(String sql, String val) {
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, val);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            else {
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static String selectString(String sql, String val) {
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, val);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
            else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
