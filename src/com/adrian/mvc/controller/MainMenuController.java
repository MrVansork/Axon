package com.adrian.mvc.controller;

import com.adrian.Axon;
import com.adrian.util.Assets;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    @FXML
    private Button messages_btn;
    @FXML
    private Button settings_btn;
    @FXML
    private Button singout_btn;

    @FXML
    private ImageView avatar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        messages_btn.setGraphic(new ImageView(Assets.getImage("MAIL ICON")));
        settings_btn.setGraphic(new ImageView(Assets.getImage("SETTINGS ICON")));
        singout_btn.setGraphic(new ImageView(Assets.getImage("SIGN-OUT ICON")));

        avatar.setImage(Assets.getImage("APP ICON"));
        avatar.setFitWidth(80);
        avatar.setFitHeight(80);
    }

    @FXML
    private void signOut(){
        Axon.get().switchScene("logIn");
    }

    @FXML
    private void settings(){

    }

    @FXML
    private void messages(){

    }

}
