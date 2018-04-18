package com.subbu.sample.controllers;

import com.subbu.sample.OnlineTicketApp;
import com.subbu.sample.constants.BookedFlight;
import com.subbu.sample.constants.FareDetails;
import com.subbu.sample.constants.FlightData;
import com.subbu.sample.constants.UserProfile;
import com.subbu.sample.helper.DBQueries;
import com.subbu.sample.stages.Booking;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    private Button book;

    @FXML
    public void initialize() throws SQLException {
        for (String source :DBQueries.getSource()) {
            from.getItems().add(source);
            from.setValue(source);
        }

        SimpleDateFormat formatter = new SimpleDateFormat("dd - MM - yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        for (int i = 0; i < 5; i++) {
            c.add(Calendar.DAY_OF_MONTH,1);
            String dateValue = formatter.format(c.getTime());
            date.getItems().add(dateValue);
            date.setValue(dateValue);
        }
    }

    @FXML
    private void populateTo() throws SQLException {
        to.getItems().removeAll(to.getItems());
        for (String dest :DBQueries.getDestinations(from.getValue())) {
            to.getItems().add(dest);
            to.setValue(dest);
        }
    }

    @FXML
    private void populateFlights() throws SQLException {
        flightDetails.getItems().removeAll(flightDetails.getItems());
        firstSeats.getItems().removeAll(firstSeats.getItems());
        businessSeats.getItems().removeAll(businessSeats.getItems());
        economySeats.getItems().removeAll(economySeats.getItems());

        flightDetails.getItems().add("Flight Num\t\tStart\t\tEnd\t\tFirst\t\tBusiness\t\tEconomy");
        for (FlightData data : DBQueries.getFlights(from.getValue(), to.getValue())) {
            flightDetails.getItems().add(data.getNumber() + "\t\t\t\t" + data.getStart() + "\t\t" + data.getEnd() + "\t" + data.getFirstFare()  + "\t\t" + data.getBusinessFare() + "\t\t\t" + data.getEconomyFare());
            flightList.getItems().add(data.getNumber());
            flightList.setValue(data.getNumber());
        }

        for (int i = 0; i <= 5; i++) {
            firstSeats.getItems().add(i);
            businessSeats.getItems().add(i);
        }

        for (int i = 0; i <= 15; i++) {
            economySeats.getItems().add(i);
        }

        firstSeats.setValue(0);
        businessSeats.setValue(0);
        economySeats.setValue(0);
    }

    @FXML
    private void calculateFare() throws SQLException {
        if ((firstSeats.getValue() + businessSeats.getValue() + economySeats.getValue()) == 0) {
            totalPrice.setText("Please select a few seats");
            return;
        }
        List<Integer> fares = DBQueries.getFare(flightList.getValue());
        int totalFare = (fares.get(0) * firstSeats.getValue() + fares.get(1) * businessSeats.getValue() + fares.get(2) * economySeats.getValue());
        String faretext =
                "First Class Fare : " + fares.get(0) * firstSeats.getValue() + "\n" +
                        "Business Class Fare : " + fares.get(1) * businessSeats.getValue() + "\n" +
                        "Economy Class Fare : " + fares.get(2) * economySeats.getValue() + "\n" +
                        "Total Fare : " + totalFare;
        if (totalFare <= UserProfile.credits) {
            faretext = faretext + "\nProceed to Buy";
            FareDetails.addedMoney = 0;
        } else {
            faretext = faretext + "\nPlease add " + (totalFare - UserProfile.credits) + " credits to continue transaction";
            book.setText("ADD " + (totalFare - UserProfile.credits) + " and\nbook tickets");
            FareDetails.addedMoney = totalFare - UserProfile.credits;
        }
        totalPrice.setText(faretext);
        FareDetails.firstFare = fares.get(0) * firstSeats.getValue();
        FareDetails.businessFare = fares.get(1) * businessSeats.getValue();
        FareDetails.economyFare = fares.get(2) * economySeats.getValue();
    }

    @FXML
    private void buyTickets() throws SQLException, IOException {
        if ((firstSeats.getValue() + businessSeats.getValue() + economySeats.getValue()) == 0) {
            totalPrice.setText("Please select a few seats");
            return;
        }
        int price = (FareDetails.firstFare + FareDetails.businessFare + FareDetails.economyFare - FareDetails.addedMoney);
        UserProfile.credits -= price;
        DBQueries.useCredits(price);
        String pnr = UUID.randomUUID().toString();
        DBQueries.addReservation(pnr, flightList.getValue(), firstSeats.getValue(), businessSeats.getValue(), economySeats.getValue(), date.getValue());

        BookedFlight.uuid = pnr;
        BookedFlight.flightNum = flightList.getValue();
        BookedFlight.source = from.getValue();
        BookedFlight.destination = to.getValue();
        populateFlights();
        totalPrice.clear();
        Booking page = new Booking(OnlineTicketApp.stage);
        page.setStageView();
        OnlineTicketApp.stage.show();
    }
}
