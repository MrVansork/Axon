package com.adrian;

import com.adrian.util.Log;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Axon extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Log.d(getClass().getName(), "started javaFX");
        Scene scene = new Scene(new AnchorPane(), 1024, 720);
        stage.setScene(scene);
        stage.setTitle("Axon");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
