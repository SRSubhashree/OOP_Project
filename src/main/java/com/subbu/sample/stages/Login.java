package com.subbu.sample.stages;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Login {
    Stage primaryStage;

    public Login(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setLoginView() throws IOException {
        URL location = Thread.currentThread().getContextClassLoader().getResource("stages/login.fxml");

        Parent base = FXMLLoader.load(location);
        primaryStage.setScene(new Scene(base));
    }
}
