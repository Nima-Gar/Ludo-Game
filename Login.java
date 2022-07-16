package com.example.ludo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;


import static com.example.ludo.Account.accounts;
import static com.example.ludo.Main.changeScene;
import static com.example.ludo.CurrentManager.currentFiller;

public class Login {


    @FXML
    public Button menuBtn;

    @FXML
    public Label alertMessage;

    @FXML
    public PasswordField passwordInp;

    @FXML
    public Button registerBtn;

    @FXML
    public TextField usernameInp;

    @FXML
    void onMenu(ActionEvent event) throws IOException {
        changeScene("start.fxml","Start Page",false);
    }

    @FXML
    void onSubmit(ActionEvent event) throws IOException {
        boolean correctInformation =  userChecker( usernameInp.getText().trim() , passwordInp.getText().trim() );
        clear();

        if (correctInformation)
            changeScene("profile.fxml", "User Profile Page", false);
        else
            alertMessage.setText("username or password isn't correct!");
    }


    public static boolean userChecker(String enteredUsername, String enteredPassword){
        boolean correctInformation = false;

        for(Account account: accounts){
            String username = account.username;
            String password = account.password;

            if (username.equals(enteredUsername) && password.equals(enteredPassword)) {
                correctInformation = true;
                currentFiller( username, password, String.valueOf(account.points) );
            }
        }
        return correctInformation;
    }

    private void clear(){
        usernameInp.setText("");
        passwordInp.setText("");
        alertMessage.setText("");
    }

}
