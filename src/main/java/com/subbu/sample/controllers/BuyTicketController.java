package com.subbu.sample.controllers;

import com.subbu.sample.constants.BookedFlight;
import com.subbu.sample.constants.UserProfile;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class BuyTicketController {

    @FXML
    private TextArea finalArea;

    @FXML
    public void initialize() {
        String bookingText = "Hey " + UserProfile.name + ",\n" +
                "Thank you for booking flight " + BookedFlight.flightNum + " with pnr " + BookedFlight.uuid + "\n" +
                "from " + BookedFlight.source + " to " + BookedFlight.destination + "\n\n" +
                "Your itinerary has been communicated to you over your mobile " + UserProfile.phone + "\n" +
                "and your email " + UserProfile.email + "\n\n" +
                "We look forward to flying with you!";

        finalArea.setText(bookingText);
    }
}
