package com.subbu.sample.helper;

import com.subbu.sample.config.DatabaseProperties;
import com.subbu.sample.constants.FareDetails;
import com.subbu.sample.constants.FlightData;
import com.subbu.sample.constants.UserProfile;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBQueries {
    public static boolean verifyUserCredentials(String uid, String pwd) throws SQLException {
        Connection conn = DatabaseProperties.conn;

        String query = "select * from user where user_name = '" + uid + "'";
        ResultSet resultSet = conn.createStatement().executeQuery(query);
        if(resultSet.next() && pwd.equals(resultSet.getString("user_pw"))) {
            ConstantsConfig.setUserDetails(resultSet);
            return true;
        }
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

    public static List<String> getSource() throws SQLException {
        Connection conn = DatabaseProperties.conn;
        String query = "select DISTINCT source from flights";
        ResultSet resultSet = conn.createStatement().executeQuery(query);
        List<String> destinations = new ArrayList<>();
        while (resultSet.next()){
            destinations.add(resultSet.getString(1));
        }
        return destinations;
    }

    public static List<String> getDestinations(String source) throws SQLException {
        Connection conn = DatabaseProperties.conn;
        String query = "select distinct destination from flights where source = '" + source + "'";
        ResultSet resultSet = conn.createStatement().executeQuery(query);
        List<String> destinations = new ArrayList<>();
        while (resultSet.next()){
            destinations.add(resultSet.getString(1));
        }
        return destinations;
    }

    public static List<FlightData> getFlights(String source, String dest) throws SQLException {
        Connection conn = DatabaseProperties.conn;
        String query = "select number, start_time, end_time, first_fare, business_fare, economy_fare from flights where source = '" + source + "' and destination = '" + dest + "'";
        ResultSet resultSet = conn.createStatement().executeQuery(query);
        List<FlightData> data = new ArrayList<>();
        while (resultSet.next()){
            data.add(new FlightData(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));
        }
        return data;
    }

    public static List<Integer> getFare(Integer number) throws SQLException {
        Connection conn = DatabaseProperties.conn;
        String query = "select first_fare, business_fare, economy_fare from flights where number = " + number;
        ResultSet resultSet = conn.createStatement().executeQuery(query);
        List<Integer> data = new ArrayList<>();
        if (resultSet.next()){
            for (int i = 1; i <= 3; i++) {
                data.add(resultSet.getInt(i));
            }
        }
        return data;
    }

    public static void useCredits(int price) throws SQLException {
        Connection conn = DatabaseProperties.conn;
        String query = "update user set credits = credits - " + price + " where id = " + UserProfile.id;
        conn.createStatement().executeUpdate(query);
    }

    public static void addReservation(String uuid, int flightNum, int firstSeats, int businessSeats, int economySeats, String date) throws SQLException {
        Connection conn = DatabaseProperties.conn;
        String query = "insert into reservations (pnr, user_id, flight_num, first_seats, business_seats, economy_seats, fare, date) values ('" +
                uuid + "', " +
                UserProfile.id + ", " +
                flightNum + ", " +
                firstSeats + ", " +
                businessSeats + ", " +
                economySeats + ", " +
                (FareDetails.firstFare + FareDetails.businessFare + FareDetails.economyFare) + ", '" +
                date + "')";
        conn.createStatement().executeUpdate(query);
    }
}
