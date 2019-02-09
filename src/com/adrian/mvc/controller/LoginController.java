package com.adrian.mvc.controller;

import com.adrian.Axon;
import com.adrian.util.Constants;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField user;

    @FXML
    private ImageView icon;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        icon.setImage(new Image(getClass().getResourceAsStream("/res/icon.png")));
        icon.setFitHeight(128);
        icon.setFitWidth(128);
    }

    @FXML
    public void send(){
        String msg = user.getText();
        user.setText("");
        Axon.get().getClient().send(msg+"$");
    }
}
