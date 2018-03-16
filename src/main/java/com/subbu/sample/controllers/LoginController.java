package com.subbu.sample.controllers;

import com.subbu.sample.helper.DBQueries;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField userName;

    @FXML
    private PasswordField password;

    @FXML
    private Label loginLabel;

    @FXML
    private void login() throws SQLException {
        if (DBQueries.doesUserExist(userName.getText())) {
            if (DBQueries.verifyUserCredentials(userName.getText(), password.getText()))
                loginLabel.setText("Yayy");
            else
                loginLabel.setText("Biscuit!");
        }else
            loginLabel.setText("esesaav");
    }

    @FXML
    private void test(){
        loginLabel.setText("clicked");
    }
}
