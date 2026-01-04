package com.roomate.dao;

import java.sql.*;

import com.roomate.util.DBConnection;




//this class interacts with db to validate login credentials.

public class UserDao {

    public static boolean isValid(String username, String password)
            throws SQLException{


        boolean isValid = false; //this variable stores the value of the
        String sql = "select * from roommates where name = ? and password =? ";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    isValid = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return isValid;

    }
}


















