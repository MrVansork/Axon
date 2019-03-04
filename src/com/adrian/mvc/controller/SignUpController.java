package com.adrian.mvc.controller;

import com.adrian.Axon;
import com.adrian.util.Assets;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private Label error_label;

    @FXML
    private TextField user;
    @FXML
    private TextField email;
    @FXML
    private PasswordField passwd_1;
    @FXML
    private PasswordField passwd_2;

    @FXML
    private ImageView user_check;
    @FXML
    private ImageView email_check;
    @FXML
    private ImageView passwd_2_check;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Axon.get().getPopup().setOnCloseRequest(event->{
            reset();
            Axon.get().disableGaussian();
        });
    }

    @FXML
    private void userCheck(){
        if(user.getText().equals("null"))
            user_check.setImage(Assets.getImage("FAILED ICON"));
        else
            user_check.setImage(Assets.getImage("CORRECT ICON"));
    }

    @FXML
    private void emailCheck(){
        if(email.getText().contains("@"))
            email_check.setImage(Assets.getImage("CORRECT ICON"));
        else
            email_check.setImage(Assets.getImage("FAILED ICON"));
    }

    @FXML
    private void passCheck(KeyEvent e){
        if(passwd_1.getText().equals(passwd_2.getText())){
            passwd_2_check.setImage(Assets.getImage("CORRECT ICON"));
        }else{
            passwd_2_check.setImage(Assets.getImage("FAILED ICON"));
        }
    }

    @FXML
    private void accept(){
        reset();
        Axon.get().disableGaussian();
        Axon.get().getPopup().close();
    }

    @FXML
    private void cancel(){
        reset();
        Axon.get().disableGaussian();
        Axon.get().getPopup().close();

    }

    private void reset(){
        error_label.setText("");
        user.clear();
        email.clear();
        passwd_1.clear();
        passwd_2.clear();
        user_check.setImage(null);
        email_check.setImage(null);
        passwd_2_check.setImage(null);
    }

}
