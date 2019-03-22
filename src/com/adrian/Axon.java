package com.adrian;

import com.adrian.net.Client;
import com.adrian.util.Assets;
import com.adrian.util.Log;
import com.adrian.util.Security;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.bson.Document;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

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
        /*singleton = this;
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

        stage.show();*/
        Security.init();
        Document user_login = new Document("username", "mrvansork_2")
                .append("password", BCrypt.hashpw("B00le", BCrypt.gensalt()));
                //.append("email", "adrian.blesa.moreno@gmail.com");

        startConnection();

        Thread recv = new Thread(() -> {
            Log.d("Client", "Waiting Message...");
            while(true){
                String txt = client.receive();
                if(txt.startsWith("@@LOGIN@@")){
                    if(txt.split("@@LOGIN@@")[1].equals("@@OK@@")){
                        Log.d("Client", "Logged in!");
                    }else if(txt.split("@@LOGIN@@")[1].equals("@@FAILED@@")){
                        Log.d("Client", "Error: User or password incorrect!");
                    }else{
                        Log.d("Client", "??: "+txt);
                    }
                }else if(txt.startsWith("@@SIGNUP@@")){
                    if(txt.split("@@SIGNUP@@")[1].equals("@@OK@@")){
                        Log.d("Client", "Signed Up!");
                    }else if(txt.split("@@SIGNUP@@")[1].equals("@@FAILED@@")){
                        Log.d("Client", "Error: Username already exists!");
                    }else{
                        Log.d("Client", "??: "+txt);
                    }
                }else {
                    Log.d("Client", "Received: "+txt);
                }
            }
        });
        recv.start();

        Scanner input = new Scanner(System.in);
        Thread send = new Thread(() -> {
            while(true){
                System.out.print("To send: ");
                client.send(input.nextLine());
            }
        });
        //send.start();
        client.send("@@P_KEY@@"+new String(Security.getPublicKey().getEncoded()));
        client.send("@@LOGIN@@"+user_login.toJson());
        //client.send("@@SIGNUP@@"+user_login.toJson());
    }

    private void loadView(String name, String key, double width, double height){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mvc/view/"+name));
            scenes.put(key, new Scene(loader.load(), width, height));
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

    public static void main(String[] args) {
        Assets.init();
        launch(args);
    }

}
