package com.subbu.sample;

import com.subbu.sample.config.DatabaseProperties;
import com.subbu.sample.stages.Login;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class OnlineTicketApp extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException, SQLException {
        primaryStage.setTitle("Online Ticketing");

        Label connection = new Label("Connecting to Database");
        primaryStage.setScene(new Scene(connection));
        primaryStage.show();
        try {
            DatabaseProperties.connectToDB();
        }catch (Exception e) {
            System.out.println("failed to connect to db");
        }

        Login page = new Login(primaryStage);
        page.setLoginView();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
