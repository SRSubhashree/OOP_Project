package com.subbu.sample.helper;

import com.subbu.sample.config.DatabaseProperties;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBQueries {
    public static boolean verifyUserCredentials(String uid, String pwd) throws SQLException {
        Connection conn = DatabaseProperties.conn;

        String query = "select * from user where user_name = '" + uid + "'";
        ResultSet resultSet = conn.createStatement().executeQuery(query);
        if(resultSet.next())
            return pwd.equals(resultSet.getString("user_pw"));
        return false;
    }


    public static boolean doesUserExist(String userName) throws SQLException{
        Connection conn = DatabaseProperties.conn;
        String query = "select COUNT(*) from user where user_name = '" + userName + "'";
        ResultSet resultSet = conn.createStatement().executeQuery(query);
        if(resultSet.next())
            return resultSet.getInt(1) == 1;
        return false;
    }
}
