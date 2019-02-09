package com.adrian.mvc.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignInController implements Initializable {

    @FXML
    private TextField user;
    @FXML
    private TextField email;
    @FXML
    private PasswordField passwd_1;
    @FXML
    private PasswordField passwd_2;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
