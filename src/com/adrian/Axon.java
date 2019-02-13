package com.adrian;

import com.adrian.net.Client;
import com.adrian.util.Assets;
import com.adrian.util.Log;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
        WIDTH = 768;
        HEIGHT = 480;

        popup = new Stage(StageStyle.TRANSPARENT);
        popup.initOwner(stage);
        popup.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("mvc/view/LoginView.fxml"));
        scenes.put("login", new Scene(loader.load(), WIDTH, HEIGHT));

        loader = new FXMLLoader(getClass().getResource("mvc/view/SignUpView.fxml"));
        scenes.put("signup", new Scene(loader.load(), 400, 250));

        loader = new FXMLLoader(getClass().getResource("mvc/view/MainMenuView.fxml"));
        scenes.put("mainMenu", new Scene(loader.load(), WIDTH, HEIGHT));

        //startConnection();
        stage.setScene(scenes.get("login"));
        stage.setTitle("Axon");
        stage.getIcons().addAll(Assets.getImage("APP_ICON"));

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
            stage.setScene(scene);
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

    public static void main(String[] args) {
        Assets.init();
        launch(args);
    }

}
