package com.adrian.mvc.controller;

import com.adrian.Axon;
import com.adrian.mvc.model.Message;
import com.adrian.util.Assets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private Button backToMain;
    @FXML
    private VBox messages;
    @FXML
    private CheckBox selectAll;

    public ObservableList<Message> messagesList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        backToMain.setGraphic(new ImageView(Assets.getImage("BACK ICON")));

        newMail.setGraphic(new ImageView(Assets.getImage("SENT-MAIL ICON")));
        newMail.setText("NUEVO");


        removeSelected.setGraphic(new ImageView(Assets.getImage("TRASH ICON")));
        removeSelected.setText("ELIMINAR");

        messagesList.addAll(new Message("MrVansork", new String[]{"Adrian", "MrVansork"}, "Este mensaje es una prueba para ver como funciona el tema de visualizar mensajes\nTambien es un poco mas largo para ver si se visualiza correctamente.\nUn saludo Adrian Blesa Moreno", new Date(), 1),
                new Message("Adrian", new String[]{"Adrian", "MrVansork"}, "Este mensaje es una prueba para ver como funciona el tema de visualizar mensajes\nTambien es un poco mas largo para ver si se visualiza correctamente.\nUn saludo Adrian Blesa Moreno", new Date(), 1));

        selectAll.setOnAction(e -> {
            if(((CheckBox) e.getSource()).isSelected())
                selectAll();
            else
                unselectAll();
        });

        loadMessages("");
        search_text.textProperty().addListener((observable, oldValue, newValue) -> {
            loadMessages(newValue);
        });

    }

    @FXML
    public void back(){
        Axon.get().switchScene("mainMenu");
    }

    private void selectAll(){
        for(int i = 0; i < messages.getChildren().size(); i++){
            BorderPane pane = (BorderPane) messages.getChildren().get(i);
            pane.getStyleClass().clear();
            pane.getStyleClass().add("messagePaneSelected");
            ((CheckBox)((Pane)pane.getLeft()).getChildren().get(0)).setSelected(true);
        }
    }

    public void unselectAll(){
        for(int i = 0; i < messages.getChildren().size(); i++){
            BorderPane pane = (BorderPane) messages.getChildren().get(i);
            pane.getStyleClass().clear();
            pane.getStyleClass().add("messagePane");
            ((CheckBox)((Pane)pane.getLeft()).getChildren().get(0)).setSelected(false);
        }
    }

    private void loadMessages(String filter){
        messages.getChildren().clear();
        for(Message m:messagesList){
            if(filter.isEmpty() || m.getEmisor().toLowerCase().contains(filter.toLowerCase()) || m.getMensaje().toLowerCase().contains(filter.toLowerCase()))
                messages.getChildren().add(getMessagePane(m));
        }
    }

    private BorderPane getMessagePane(Message message){
        BorderPane result = new BorderPane();
        result.getStyleClass().add("messagePane");

        result.setPadding(new Insets(5));

        CheckBox selected = new CheckBox("");
        selected.setOnAction(e -> {
            result.getStyleClass().clear();
            if(((CheckBox) e.getSource()).isSelected())
                result.getStyleClass().add("messagePaneSelected");
            else{
                result.getStyleClass().add("messagePane");
                selectAll.setSelected(false);
            }

        });
        Label user = new Label(message.getEmisor());
        user.setStyle("-fx-font-weight: bold;");
        Label subject = new Label(message.getMensaje());
        subject.setMaxHeight(16);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd/MM/YYYY");

        Label datetime = new Label(sdf.format(message.getFecha()));
        datetime.setStyle("-fx-font-weight: bold;");

        HBox sel_name = new HBox();
        sel_name.setSpacing(15);
        sel_name.getChildren().addAll(selected, user, subject);

        result.setLeft(sel_name);
        result.setRight(datetime);

        return result;
    }

}
