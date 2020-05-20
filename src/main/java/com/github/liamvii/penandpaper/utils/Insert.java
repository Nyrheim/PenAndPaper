package com.github.liamvii.penandpaper.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static com.github.liamvii.penandpaper.Pen.connection;

public class Insert {

    public static void insertToDB(String sql, ArrayList<String> vals){
        int len = vals.size();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            for (int i = 0; i < len; i++) {
                stmt.setString(i+1, vals.get(i));
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void serialize(String sql, ArrayList<String> vals){
        int len = vals.size();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            for (int i = 0; i < len; i++) {
                stmt.setString(i+1, vals.get(i));
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int insertReturnID(String sql, ArrayList<String> vals){
        int len = vals.size();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < len; i++) {
                stmt.setString(i+1, vals.get(i));
            }
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
