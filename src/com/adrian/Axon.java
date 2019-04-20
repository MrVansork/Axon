package com.adrian;

import com.adrian.net.Client;
import com.adrian.net.ClientController;
import com.adrian.util.Assets;
import com.adrian.util.Log;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.HashMap;

public class Axon extends Application {

    private static Axon singleton;
    public static Axon get(){ return singleton; }

    private static HashMap<String, Scene> scenes = new HashMap<>();
    private static double WIDTH, HEIGHT;

    private Client client;
    private boolean running;

    private Stage stage;
    private Stage popup;

    @Override
    public void start(Stage stage) throws Exception {
        singleton = this;
        this.stage = stage;
        Log.d(getClass().getName(), "started javaFX");
        WIDTH = 1024;
        HEIGHT = 580;
        popup = new Stage(StageStyle.TRANSPARENT);
        popup.initOwner(stage);
        popup.initModality(Modality.APPLICATION_MODAL);

        loadView("LoginView.fxml", "logIn", WIDTH, HEIGHT);
        loadView("signupview.fxml", "signUp", 400, 250);
        loadView("MainMenuView.fxml", "mainMenu", WIDTH, HEIGHT);
        loadView("CreateNetView.fxml", "createNet", WIDTH, HEIGHT);
        loadView("MailView.fxml", "mail", WIDTH, HEIGHT);

        scenes.get("signUp").setOnKeyPressed(e ->{
            if(e.getCode() == KeyCode.ESCAPE){
                disableGaussian();
                popup.close();
            }
        });

        stage.setScene(scenes.get("logIn"));
        stage.setTitle("Axon");
        stage.getIcons().addAll(Assets.getImage("APP ICON"));

        startConnection();
        ClientController.getInstance().startReceiving();
        stage.show();

        stage.setOnCloseRequest(e -> client.send("@@QUIT@@"));
    }

    private void loadView(String name, String key, double width, double height){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mvc/view/"+name));
            Scene scene =  new Scene(loader.load(), width, height);
            scene.setUserData(loader);
            scenes.put(key, scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startConnection(){
        client = new Client("localhost", 8192);
        running = true;
    }

    public void applyGaussian(){
        stage.getScene().getRoot().setEffect(new GaussianBlur(2));
    }

    public void disableGaussian(){
        stage.getScene().getRoot().setEffect(null);
    }

    public void switchPopup(String key){
        switchPopup(scenes.get(key));
    }

    public void switchPopup(Scene scene){
        if(scene != null){
            popup.setScene(scene);
        }
    }

    public void switchScene(String key){
        switchScene(scenes.get(key));
    }

    public void switchScene(Scene scene){
        if(scene != null){
            double width = stage.getScene().getWindow().getWidth();
            double height = stage.getScene().getWindow().getHeight();

            stage.setScene(scene);

            stage.getScene().getWindow().setWidth(width);
            stage.getScene().getWindow().setHeight(height);
        }
    }

    public Client getClient() {
        return client;
    }

    public Stage getStage() {
        return stage;
    }

    public Stage getPopup() {
        return popup;
    }

    public HashMap<String, Scene> getScenes() {
        return scenes;
    }

    public Object getController(String scene){
        return ((FXMLLoader)scenes.get(scene).getUserData()).getController();
    }

    public static void main(String[] args) {
        Assets.init();
        launch(args);
    }

}
