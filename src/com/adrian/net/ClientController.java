package com.adrian.net;

import com.adrian.Axon;
import com.adrian.mvc.controller.LoginController;
import com.adrian.mvc.controller.SignUpController;
import com.adrian.util.Log;
import javafx.application.Platform;

public class ClientController {

    //----SINGLETON----
    private static ClientController singleton;
    public static ClientController getInstance(){
        return singleton != null ? singleton : new ClientController();
    }
    private ClientController(){ singleton = this; }
    //-----------------

    public void startReceiving(){
        Thread receive = new Thread(() -> {
            while (process(Axon.get().getClient().receive()));
        });
        receive.start();
    }

    private boolean process(String txt){
        if(txt.startsWith("@@LOGIN@@")){
            if(txt.split("@@LOGIN@@")[1].equals("@@OK@@")){
                Log.d("Client", "Logged in!");
                callFX(()->Axon.get().switchScene("mainMenu"));
            }else if(txt.split("@@LOGIN@@")[1].equals("@@FAILED@@")){
                Log.d("Client", "Error: User or password incorrect!");
                callFX(()->((LoginController)Axon.get().getController("logIn")).showError());
            }else{
                Log.d("Client", "??: "+txt);
            }
        }else if(txt.startsWith("@@SIGNUP@@")){
            if(txt.split("@@SIGNUP@@")[1].equals("@@OK@@")){
                Log.d("Client", "Signed Up!");
                callFX(() ->{
                    ((SignUpController)Axon.get().getController("signUp")).close();
                    ((LoginController)Axon.get().getController("logIn")).showSignup();
                });
            }else if(txt.split("@@SIGNUP@@")[1].equals("@@FAILED@@")){
                Log.d("Client", "Error: Username already exists!");
            }else{
                Log.d("Client", "??: "+txt);
            }
        }else if(txt.equals("@@QUIT@@")){
            Axon.get().getClient().quit();
            return false;
        }else {
            Log.d("Client", "Received: "+txt);
        }
        return true;
    }

    private void callFX(Runnable r){
        Platform.runLater(r);
    }
}
