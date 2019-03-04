package com.adrian.mvc.controller;

import com.adrian.util.Assets;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MailController implements Initializable {

    @FXML
    private TextField search_text;

    @FXML
    private Button newMail;
    @FXML
    private Button search;
    @FXML
    private Button removeSelected;

    @FXML
    private VBox messages;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newMail.setGraphic(new ImageView(Assets.getImage("SENT-MAIL ICON")));
        newMail.setText("NUEVO");


        removeSelected.setGraphic(new ImageView(Assets.getImage("TRASH ICON")));
        removeSelected.setText("ELIMINAR");

        loadMessages();
    }

    private void loadMessages(){

        for(int i = 0; i < 2; i++){
            messages.getChildren().add(getMessagePane());
        }
    }

    private HBox getMessagePane(){
        HBox result = new HBox();


        Label user = new Label("Usuario Prueba");
        Label subject = new Label("Mensaje de prueba para ti");
        Label datetime = new Label("12:50");

        result.getChildren().addAll(user, subject, datetime);

        return result;
    }

}
