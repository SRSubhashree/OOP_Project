package com.subbu.sample.helper;

import com.subbu.sample.constants.UserProfile;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ConstantsConfig {

    static void setUserDetails(ResultSet set) throws SQLException {
        Timestamp dob = set.getTimestamp("dob");
        Timestamp time = new Timestamp(new Date().getTime());
        UserProfile.id = set.getInt("id");
        UserProfile.name = set.getString("first_name") + " " + set.getString("last_name");
        UserProfile.gender = set.getString("gender");
        UserProfile.age = (time.getYear() - dob.getYear());
        UserProfile.phone = set.getString("phone");
        UserProfile.email = set.getString("email");
    }
}
