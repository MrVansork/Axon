package com.adrian.mvc.controller;

import com.adrian.Axon;
import com.adrian.util.Assets;
import com.adrian.util.Log;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import org.bson.Document;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private Label error_label;

    @FXML
    private TextField user;
    @FXML
    private PasswordField passwd_1;
    @FXML
    private PasswordField passwd_2;

    @FXML
    private ImageView user_check;
    @FXML
    private ImageView passwd_2_check;

    private boolean correct = true;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Axon.get().getPopup().setOnCloseRequest(event->{
            reset();
            Axon.get().disableGaussian();
        });

    }

    @FXML
    private void userCheck(){
        if(user.getText().equals("null")){
            user_check.setImage(Assets.getImage("FAILED ICON"));
            correct = false;
        }else{
            correct = correct && true;
            user_check.setImage(Assets.getImage("CORRECT ICON"));
        }
    }

    @FXML
    private void passCheck(KeyEvent e){
        String letter = e.getText();
        TextField source = (TextField)e.getSource();
        String original = source.getText();
        source.appendText(letter);

        if(passwd_1.getText().equals(passwd_2.getText())){
            passwd_2_check.setImage(Assets.getImage("CORRECT ICON"));
            correct = correct && true;
        }else{
            passwd_2_check.setImage(Assets.getImage("FAILED ICON"));
            correct = false;
        }
        source.setText(original);
        source.positionCaret(original.length());
    }

    @FXML
    private void accept(){
        if(correct){
            Document doc = new Document().append("username", user.getText())
                    .append("password", passwd_1.getText());

            Axon.get().getClient().send("@@SIGNUP@@"+doc.toJson());
        }else{
            correct = true;
        }
    }

    public void close(){
        reset();
        Axon.get().disableGaussian();
        Axon.get().getPopup().close();
    }

    @FXML
    private void cancel(){
        close();
    }

    private void reset(){
        error_label.setText("");
        user.clear();
        passwd_1.clear();
        passwd_2.clear();
        user_check.setImage(null);
        passwd_2_check.setImage(null);
    }

}
