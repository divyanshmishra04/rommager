package com.roomate.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/RoomMateManager";
    private static final String USER = "root";
    private static final String PASS = "pass";

    //this method establishes connection to mysql and is used by all dao classes.
    public static Connection getConnection() throws SQLException {

        Connection conn = null;

        try {

            //load jdbc driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //establish connection
            conn = DriverManager.getConnection(URL, USER, PASS);


        } catch (ClassNotFoundException e) {
            System.out.println(" MySQL JDBC Driver not found. Please add MySQL connector JAR to your project.");
            e.printStackTrace();

        } catch (SQLException e) {
            System.out.println(" Failed to connect to database: " + e.getMessage());
            throw e;


        }
        return conn;
    }
}
