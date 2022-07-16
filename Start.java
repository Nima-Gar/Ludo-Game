package com.example.ludo;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.io.IOException;

import static com.example.ludo.Account.pointsOf;
import static com.example.ludo.Main.changeScene;
import static com.example.ludo.Login.userChecker;

import static com.example.ludo.Player.*;

public class Start {

    @FXML
    public Button startBtn;

    @FXML
    public Button loginBtn;

    @FXML
    public CheckBox p1;

    @FXML
    public CheckBox p2;

    @FXML
    public CheckBox p3;

    @FXML
    public CheckBox p4;

    @FXML
    public Label alertMessage;

    @FXML
    public Label alertMessage1;

    @FXML
    public Label alertMessage2;

    @FXML
    public Label alertMessage3;

    @FXML
    public Label alertMessage4;

    @FXML
    public PasswordField passwordInp1;

    @FXML
    public PasswordField passwordInp2;

    @FXML
    public PasswordField passwordInp3;

    @FXML
    public PasswordField passwordInp4;

    @FXML
    public Button registerBtn;

    @FXML
    public TextField usernameInp1;

    @FXML
    public TextField usernameInp2;

    @FXML
    public TextField usernameInp3;

    @FXML
    public TextField usernameInp4;


    private double initial_Y;
    private double initial_X1;
    private double initial_X2;
    private double initial_X3;
    private double initial_X4;

    public void initialize(){
        initial_Y = alertMessage.getScaleY();
        initial_X1 = alertMessage1.getScaleX();
        initial_X2 = alertMessage2.getScaleX();
        initial_X3 = alertMessage3.getScaleX();
        initial_X4 = alertMessage4.getScaleX();
    }

    @FXML
    void onLogin(ActionEvent event) throws IOException {
        changeScene("login.fxml","Login Page", false);
    }

    @FXML
    void onRegister(ActionEvent event) throws IOException {
        changeScene("register.fxml","Register Page", false);
    }

    @FXML
    void onCheckBoxChange(ActionEvent event) {
        clear();
        int selectedCounter = 0;
        selectedCounter += selectedChecker(p1);
        selectedCounter += selectedChecker(p2);
        selectedCounter += selectedChecker(p3);
        selectedCounter += selectedChecker(p4);

        if (selectedCounter < 2) {
            alertMessage.setText("number of players must be at least 2");
            messageMover(initial_Y, alertMessage, true);
            startBtn.setMouseTransparent(true);
        }
        else {
            alertMessage.setText("");
            startBtn.setMouseTransparent(false);
        }
    }

    @FXML
    void onStart(ActionEvent event) throws IOException {
        clear();
        boolean correctInformation1 , correctInformation2, correctInformation3, correctInformation4;
        correctInformation1 = correctInformation2 = correctInformation3 = correctInformation4 = true;

//        if ( p1.isSelected())
//            correctInformation1 = userHandler(usernameInp1.getText().trim(), passwordInp1.getText().trim(), 1, alertMessage1, initial_X1);
//
//        if ( p2.isSelected())
//            correctInformation2 = userHandler(usernameInp2.getText().trim(), passwordInp2.getText().trim(), 2, alertMessage2, initial_X2);
//
//        if ( p3.isSelected())
//            correctInformation3 = userHandler(usernameInp3.getText().trim(), passwordInp3.getText().trim(), 3, alertMessage3, initial_X3);
//
//        if ( p4.isSelected())
//            correctInformation4 = userHandler(usernameInp4.getText().trim(), passwordInp4.getText().trim(), 4, alertMessage4, initial_X4);

        // بخش موقتی که اینستنس های player را به جای متود userHandler مقدار دهی کند:
        player1 = getInstance();
        player1.setter("nima", 0);
        player2 = getInstance();
        player2.setter("nima.gharibloo", 0);
        player3 = getInstance();
        player3.setter("nima", 0);
        player4 = getInstance();
        player4.setter("nima", 0);


        if (correctInformation1 && correctInformation2 && correctInformation3 && correctInformation4)
            changeScene("ludoScene.fxml","Ludo Page", true);

    }

    private boolean userHandler(String username, String password, int playerNumber, Label message, double origin) {
        boolean correctInformation = false;
        if ( userChecker(username, password) )
        {
            correctInformation = true;

            switch (playerNumber) {
                case 1:
                    player1 = Player.getInstance();
                    player1.setter(username, pointsOf(username));
                    break;
                case 2:
                    player2 = Player.getInstance();
                    player2.setter(username, pointsOf(username));
                    break;
                case 3:
                    player3 = Player.getInstance();
                    player3.setter(username, pointsOf(username));
                    break;
                case 4:
                    player4 = Player.getInstance();
                    player4.setter(username, pointsOf(username));
                    break;

            }
        }
        else
        {
            message.setText("incorrect information!");
            messageMover(origin, message, false);
        }

        return correctInformation;
    }


    private int selectedChecker(CheckBox player) {
        if (player.isSelected())
            return 1;
        else
            return 0;
    }


    private void messageMover(double origin, Label message, boolean mainMessage) {

        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.seconds(1));
        if (mainMessage) {
            translateTransition.setFromY(origin);
            translateTransition.setToY(35);
        }
        else {
            translateTransition.setFromX(origin+210);
            translateTransition.setToX(117);
        }

        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.seconds(1));
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(0.95);

        ParallelTransition parallelTransition = new ParallelTransition(translateTransition,fadeTransition);
        parallelTransition.setNode(message);
        parallelTransition.play();

    }

    private void clear() {
        alertMessage1.setText("");
        alertMessage2.setText("");
        alertMessage3.setText("");
        alertMessage4.setText("");
        // alertMessage1 will be cleared automatically when needed
    }

}
