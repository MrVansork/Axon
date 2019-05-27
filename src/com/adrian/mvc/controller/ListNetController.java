package com.adrian.mvc.controller;

import com.adrian.Axon;
import com.adrian.mvc.model.NeuralNet;
import com.adrian.mvc.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ListNetController implements Initializable {

    public enum Mode{ TRAIN ("Entrenar"), EXPORT("Exportar");
        String value;
        Mode(String value){
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }
    private Mode mode;

    private ObservableList<NeuralNet> nets;

    @FXML
    private VBox previews;

    @FXML
    private Label nombre;
    @FXML
    private Text descripcion;
    @FXML
    private Label creador;
    @FXML
    private Label fecha;
    @FXML
    private Button accion;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NeuralNet.getNets().clear();
        NeuralNet.getNets().add(new NeuralNet(1, "Red de prueba", "Esta es la descripcion de una red de prueba pero un poco mas larga para probar esto un pooc mas", "12/03/1998", new User("MrVansork")));
        nets = FXCollections.observableArrayList(NeuralNet.getNets());
        nets.addListener((ListChangeListener<NeuralNet>) c -> loadPreview());
        NeuralNet.getNets().add(new NeuralNet(2, "Red de prueba 2", "Esta es la descripcion de una red de prueba", "12/03/1998", new User("MrVansork")));
        descripcion.setWrappingWidth(200);
    }

    public void init(Mode mode){
        accion.setText(mode.getValue());
        if(mode == Mode.TRAIN){
            accion.setOnAction(e -> {
                close();
                //Axon.get().switchScene("trainScene");
            });
        }else{
            accion.setOnAction(e -> {
                close();
                //TODO: Create file data and save-it
            });
        }
    }

    @FXML
    public void close(){
        Axon.get().disableGaussian();
        Axon.get().getPopup().close();
    }

    private void loadPreview(){
        previews.getChildren().clear();
        for(NeuralNet net:nets){
            previews.getChildren().add(getPreViewPane(net));
        }

    }

    private void loadNetDetail(NeuralNet net){
        nombre.setText(net.getName());
        descripcion.setText(net.getDescription());
        creador.setText(net.getCreator().getUsername());
        fecha.setText(net.getDate());
    }

    private BorderPane getPreViewPane(NeuralNet neuralNet){
        BorderPane pane = new BorderPane();
        pane.getStyleClass().add("messagePane");
        pane.setOnMouseClicked(e -> loadNetDetail(neuralNet));


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
