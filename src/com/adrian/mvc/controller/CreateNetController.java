package com.adrian.mvc.controller;

import com.adrian.util.Assets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateNetController implements Initializable {

    @FXML
    private Button plus_input;
    @FXML
    private Button plus_hidden;
    @FXML
    private Button plus_output;
    @FXML
    private Button minus_input;
    @FXML
    private Button minus_hidden;
    @FXML
    private Button minus_output;

    @FXML
    private VBox input_box;
    @FXML
    private VBox hidden_box;
    @FXML
    private VBox output_box;

    @FXML
    private ComboBox<String> functions;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        plus_input.setGraphic(new ImageView(Assets.getImage("PLUS ICON")));
        plus_hidden.setGraphic(new ImageView(Assets.getImage("PLUS ICON")));
        plus_output.setGraphic(new ImageView(Assets.getImage("PLUS ICON")));
        minus_input.setGraphic(new ImageView(Assets.getImage("MINUS ICON")));
        minus_hidden.setGraphic(new ImageView(Assets.getImage("MINUS ICON")));
        minus_output.setGraphic(new ImageView(Assets.getImage("MINUS ICON")));

        functions.setItems(FXCollections.observableArrayList("Sigmoide", "ReLU"));

        plus_input.setOnAction(e->{

        });

    }

}
