package com.github.liamvii.penandpaper.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.github.liamvii.penandpaper.Pen.connection;

public class Exists {

    public static boolean existsFromDB(String sql, String val) {
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, val);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    public static boolean existsFromDB(String sql, String[] vals) {
        int len = vals.length;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            for (int i = 0; i < len; i++) {
                stmt.setString(i+1, vals[i]);
            }
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
}

