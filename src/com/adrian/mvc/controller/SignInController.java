package com.adrian.mvc.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

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

    @FXML
    private ImageView user_check;
    @FXML
    private ImageView email_check;
    @FXML
    private ImageView passwd_2_check;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void fieldsCheck(){
        if(passwd_1.getText().equals(passwd_2.getText())){
        }
    }

}
