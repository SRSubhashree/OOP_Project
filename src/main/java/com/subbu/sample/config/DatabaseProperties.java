package com.subbu.sample.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseProperties {

    public static Connection conn;
    public static void connectToDB() throws ClassNotFoundException {

        String host = "jdbc:mysql://127.0.0.1:3306/live_ticket";
        String uName = "root";
        String pass = "root";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(host, uName, pass);
        } catch (Exception e) {
            System.out.println("oh no");
            e.printStackTrace();
        }
    }
}
