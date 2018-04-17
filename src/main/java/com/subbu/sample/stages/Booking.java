package com.subbu.sample.stages;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Booking {
    Stage primaryStage;

    public Booking(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setStageView() throws IOException {
        URL location = Thread.currentThread().getContextClassLoader().getResource("stages/BuyTicket.fxml");

        Parent base = FXMLLoader.load(location);
        primaryStage.setScene(new Scene(base));
        primaryStage.show();
    }
}
