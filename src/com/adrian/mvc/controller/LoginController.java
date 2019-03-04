package com.adrian.mvc.controller;

import com.adrian.Axon;
import com.adrian.util.Assets;
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
        icon.setImage(Assets.getImage("APP ICON"));
        icon.setFitHeight(128);
        icon.setFitWidth(128);
    }

    /**
     * Check the credentials and logIn if all is good switch to the Main Menu View
     */
    @FXML
    public void send(){
        //TODO: Check the user login
        Axon.get().switchScene("mainMenu");
    }

    /**
     * Switch to the signUp View
     */
    @FXML
    public void signUp(){
        Axon.get().switchPopup("signUp");
        Axon.get().applyGaussian();
        Axon.get().getPopup().show();
    }

}
