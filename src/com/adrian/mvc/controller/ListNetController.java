package com.adrian.mvc.controller;

import com.adrian.mvc.model.NeuralNet;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ListNetController implements Initializable {

    public enum Mode{ TRAIN, EXPORT }
    private Mode mode;

    private ObservableList<NeuralNet> nets;

    @FXML
    private VBox previews;
    @FXML
    private VBox details;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nets = FXCollections.observableArrayList(NeuralNet.getNets());
        nets.addListener((ListChangeListener<NeuralNet>) c -> {
            loadPreview();
        });
        loadPreview();
    }

    private void loadPreview(){
        previews.getChildren().clear();
        for(NeuralNet net:nets){
            previews.getChildren().add(getPreViewPane(net));
        }
    }

    private void loadNetDetail(NeuralNet net){

    }

    public BorderPane getPreViewPane(NeuralNet neuralNet){
        BorderPane pane = new BorderPane();
        Label name = new Label(neuralNet.getName());
        Label creator = new Label(neuralNet.getCreator().getUsername());
        Label date = new Label(neuralNet.getDate());

        pane.setTop(name);
        pane.setLeft(creator);
        pane.setRight(date);
        return pane;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }
}
