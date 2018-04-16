package com.subbu.sample.controllers;

import com.subbu.sample.OnlineTicketApp;
import com.subbu.sample.helper.DBQueries;
import com.subbu.sample.stages.Login;
import com.subbu.sample.stages.Tickets;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField userName;

    @FXML
    private PasswordField password;

    @FXML
    private Label loginLabel;

    @FXML
    private void login() throws SQLException, IOException {
        if (DBQueries.doesUserExist(userName.getText())) {
            if (DBQueries.verifyUserCredentials(userName.getText(), password.getText())) {
                loginLabel.setText("Yayy");
                Tickets page = new Tickets(OnlineTicketApp.stage);
                page.setStageView();
                OnlineTicketApp.stage.show();
            } else
                loginLabel.setText("Biscuit!");
        }else
            loginLabel.setText("esesaav");
    }

    @FXML
    private void test(){
        loginLabel.setText("clicked");
    }
}
