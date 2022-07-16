package com.example.ludo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

import static com.example.ludo.DBManager.insert;
import static com.example.ludo.Main.changeScene;
import static com.example.ludo.Account.accounts;

public class Register {

    @FXML
    public Button menuBtn;

    @FXML
    public Button registerBtn;

    @FXML
    public TextField usernameInp;

    @FXML
    public PasswordField passwordInp;

    @FXML
    public Label passwordAlert;

    @FXML
    public Label successMessage;

    @FXML
    public Label usernameAlert;

    @FXML
    void onMenu(ActionEvent event) throws IOException {
        changeScene("start.fxml", "Start Page", false);
    }

    @FXML
    void onRegister(ActionEvent event) throws SQLException {
        String username = usernameInp.getText().trim();
        String password = passwordInp.getText().trim();
        boolean usernameExists = usernameChecker(username);
        clear();

        if ( !username.isEmpty() && password.length() >= 6 && !usernameExists )
        {
            insert(username,password);
            successMessage.setText("your account was successfully created...");
            usernameInp.setText("");
            passwordInp.setText("");
        }

        else if (usernameExists)
            usernameAlert.setText("already exists!");

        else if (username.isEmpty())
            usernameAlert.setText("can't be empty!");

        else
            passwordAlert.setText("6 characters or more!");

    }

    public boolean usernameChecker(String username) {
        boolean usernameExists = false;

        for(Account account: accounts)
            if (account.username.equals(username)) {
                usernameExists = true;
                break;
            }
        return usernameExists;
    }

    private void clear(){
        usernameAlert.setText("");
        passwordAlert.setText("");
        successMessage.setText("");
    }

}
