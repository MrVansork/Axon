package com.adrian.mvc.controller;

import com.adrian.Axon;
import com.adrian.util.Assets;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.bson.Document;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField user;
    @FXML
    private PasswordField password;
    @FXML
    private ImageView icon;
    @FXML
    private Label error;
    @FXML
    private Label message;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        icon.setImage(Assets.getImage("APP ICON"));
        icon.setFitHeight(128);
        icon.setFitWidth(128);
        user.setText("mrvansork");
        password.setText("B00le");

    }

    /**
     * Check the credentials and logIn if all is good switch to the Main Menu View
     */
    @FXML
    public void send(){
        Document doc = new Document().append("username", user.getText()).append("password", password.getText());
        Axon.get().getClient().send("@@LOGIN@@"+doc.toJson());
    }

    public void showSignup(){
        error.setText("");
        message.setText("Usuario registrado correctamente");
    }

    public void showError(){
        error.setText("Usuario o contraseña incorrectos");
        message.setText("");
    }

    /**
     * Switch to the signUp View
     */
    @FXML
    public void signUp(){
        Axon.get().switchPopup("signUp");
        Axon.get().applyGaussian();
        Axon.get().getPopup().show();
    }

}
