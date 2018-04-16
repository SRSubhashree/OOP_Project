package com.subbu.sample.stages;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Tickets {
    Stage primaryStage;

    public Tickets(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setStageView() throws IOException {
        URL location = Thread.currentThread().getContextClassLoader().getResource("stages/SearchTicket.fxml");

        Parent base = FXMLLoader.load(location);
        primaryStage.setScene(new Scene(base));
        primaryStage.show();
    }
}
