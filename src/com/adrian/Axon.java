package com.adrian;

import com.adrian.util.Log;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.HashMap;

public class Axon extends Application {

    private static HashMap<String, Scene> scenes = new HashMap<>();

    private static double WIDTH, HEIGHT;

    @Override
    public void start(Stage stage) throws Exception {
        Log.d(getClass().getName(), "started javaFX");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("mvc/view/LoginView.fxml"));
        WIDTH = 1024;
        HEIGHT = 720;
        scenes.put("login", new Scene(loader.load(), WIDTH, HEIGHT));

        stage.setScene(scenes.get("login"));
        stage.setTitle("Axon");
        stage.getIcons().addAll(new Image(getClass().getResourceAsStream("../../res/icon.png")));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
