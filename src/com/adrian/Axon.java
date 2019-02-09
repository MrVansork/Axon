package com.adrian;

import com.adrian.net.Client;
import com.adrian.util.Log;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("mvc/view/LoginView.fxml"));
        WIDTH = 768;
        HEIGHT = 480;
        scenes.put("login", new Scene(loader.load(), WIDTH, HEIGHT));

        //startConnection();
        stage.setScene(scenes.get("login"));
        stage.setTitle("Axon");
        stage.getIcons().addAll(new Image(getClass().getResourceAsStream("../../res/icon.png")));

        popup = new Stage();
        popup.initOwner(stage);

        stage.show();
    }

    private void startConnection(){
        client = new Client("localhost", 8190);
        running = true;
        Thread receive = new Thread(() -> {
            while (running) {
                byte[] data = client.receive();
                String msg = new String(data);
                System.out.println("Server: " + msg);
            }
        });
        receive.start();
    }

    public void switchPopup(String key){
        switchPopup(scenes.get(key));
    }

    public void switchPopup(Scene scene){
        if(!popup.getScene().equals(scene) && scene != null){
            popup.setScene(scene);
        }
    }

    public void switchScene(String key){
        switchScene(scenes.get(key));
    }

    public void switchScene(Scene scene){
        if(!stage.getScene().equals(scene) && scene != null){
            stage.setScene(scene);
        }
    }

    public Client getClient() {
        return client;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
