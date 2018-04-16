package com.subbu.sample.controllers;

import com.subbu.sample.constants.FlightData;
import com.subbu.sample.helper.DBQueries;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SearchTicketController {
    @FXML
    private ChoiceBox<String> from;

    @FXML
    private ChoiceBox<String> to;

    @FXML
    private ChoiceBox<String> date;

    @FXML
    private ListView<String> flightDetails;

    @FXML
    private ChoiceBox<Integer> firstSeats;

    @FXML
    private ChoiceBox<Integer> businessSeats;

    @FXML
    private ChoiceBox<Integer> economySeats;

    @FXML
    private TextArea totalPrice;

    @FXML
    private ChoiceBox<Integer> flightList;

    @FXML
    public void initialize() throws SQLException {
        for (String source :DBQueries.getSource()) {
            from.getItems().add(source);
        }

        SimpleDateFormat formatter = new SimpleDateFormat("dd - MM - yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        String dateValue = formatter.format(c.getTime());
        for (int i = 0; i < 5; i++) {
            date.getItems().add(dateValue);
            c.add(Calendar.DAY_OF_MONTH,1);
            dateValue = formatter.format(c.getTime());
        }
    }

    @FXML
    private void populateTo() throws SQLException {
        for (String dest :DBQueries.getDestinations(from.getValue())) {
            to.getItems().add(dest);
        }
    }

    @FXML
    private void populateFlights() throws SQLException {
        for (FlightData data : DBQueries.getFlights(from.getValue(), to.getValue())) {
            flightDetails.getItems().add(data.getNumber() + "\t" + data.getStart() + "\t" + data.getEnd());
            flightList.getItems().add(data.getNumber());
        }

        for (int i = 1; i <= 5; i++) {
            firstSeats.getItems().add(i);
            businessSeats.getItems().add(i);
        }

        for (int i = 1; i <= 15; i++) {
            economySeats.getItems().add(i);
        }
    }

    @FXML
    private void calculateFare(){}
}
