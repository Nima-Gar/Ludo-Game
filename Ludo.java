package com.example.ludo;


import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;


import static com.example.ludo.Dice.*;
import static com.example.ludo.Piece.*;
import static com.example.ludo.Player.*;

public class Ludo {

    @FXML
    public ImageView dice;

    @FXML
    public Button startBtn;

    @FXML
    public Circle pointer;

    @FXML
    public Label name1;

    @FXML
    public Label name2;

    @FXML
    public Label name3;

    @FXML
    public Label name4;

    @FXML
    public Label longName1;

    @FXML
    public Label longName2;

    @FXML
    public Label longName3;

    @FXML
    public Label longName4;

    @FXML
    public Label message;

    @FXML
    public Label shortMessage;

    @FXML
    public Label longMessage;


    private double pointerLocationX_1;
    private double pointerLocationY_1;
    private double pointerLocationX_2;
    private double pointerLocationY_2;
    private double pointerLocationX_3;
    private double pointerLocationY_3;
    private double pointerLocationX_4;
    private double pointerLocationY_4;

    private int finishedPlayers = 0;

    private int diceNumber;
    private boolean throwDice = true;
    private Player currentPlayer ;
    private Circle[] currentPieces = new Circle[4] ;


    MediaPlayer mediaPlayer;

    public void initialize() {
        pointer.setVisible(false);
        buttonTransmitter(true);
    }

    @FXML
    public void onStart(ActionEvent event) {                                                                            // initialize method runs too soon to do these tasks
        buttonTransmitter(false);
        pointer.setVisible(true);


        double player1_X_shortName = 98;
        double player1_Y_shortName = 115;
        double player2_X_shortName = 897;
        double player2_Y_shortName = 115;
        double player3_X_shortName = 897;
        double player3_Y_shortName = 434;
        double player4_X_shortName = 98;
        double player4_Y_shortName = 434;
        double player1_X_longName = 26;
        double player1_Y_longName = 115;
        double player2_X_longName = 971;
        double player2_Y_longName = 115;
        double player3_X_longName = 971;
        double player3_Y_longName = 434;
        double player4_X_longName = 26;
        double player4_Y_longName = 434;


        if (player1 != null) {

            String playerName1 = player1.name.trim();
            if (playerName1.length() <= 5) {
                name1.setText(playerName1);
                pointerLocationX_1 = player1_X_shortName;
                pointerLocationY_1 = player1_Y_shortName;
            }
            else {
                longName1.setText(playerName1);
                pointerLocationX_1 = player1_X_longName;
                pointerLocationY_1 = player1_Y_longName;
            }
        }


        if (player2 != null) {

            String playerName2 = player2.name.trim();
            if (playerName2.length() <= 5) {
                name2.setText(playerName2);
                pointerLocationX_2 = player2_X_shortName;
                pointerLocationY_2 = player2_Y_shortName;
            }
            else {
                longName2.setText(playerName2);
                pointerLocationX_2 = player2_X_longName;
                pointerLocationY_2 = player2_Y_longName;
            }
        }


        if (player3 != null) {

            String playerName3 = player3.name.trim();
            if (playerName3.length() <= 5) {
                name3.setText(playerName3);
                pointerLocationX_3 = player3_X_shortName;
                pointerLocationY_3 = player3_Y_shortName;
            }
            else {
                longName3.setText(playerName3);
                pointerLocationX_3 = player3_X_longName;
                pointerLocationY_3 = player3_Y_longName;
            }
        }


        if (player4 != null) {

            String playerName4 = player4.name.trim();
            if (playerName4.length() <= 5) {
                name4.setText(playerName4);
                pointerLocationX_4 = player4_X_shortName;
                pointerLocationY_4 = player4_Y_shortName;
            }
            else {
                longName4.setText(playerName4);
                pointerLocationX_4 = player4_X_longName;
                pointerLocationY_4 = player4_Y_longName;
            }
        }


        currentPlayer = player4;
        changeCurrentPlayer();

        dice.requestFocus();
    }


    @FXML
    public void onEnter(KeyEvent event) {

            if (throwDice) {
                message.setText("");
                shortMessage.setText("");
                longMessage.setText("");
                diceNumber = throwDice();
//                prepareAudioFile();
//                mediaPlayer.play();
                switch (diceNumber) {
                    case 1:
                        dice.setImage(dice1);
                        break;
                    case 2:
                        dice.setImage(dice2);
                        break;
                    case 3:
                        dice.setImage(dice3);
                        break;
                    case 4:
                        dice.setImage(dice4);
                        break;
                    case 5:
                        dice.setImage(dice5);
                        break;
                    case 6:
                        dice.setImage(dice6);
                        break;
                }
                throwDice = false;

                if ( piecesChecker() ) {

                    // استفاده از لوپ موقع پاس دادن به متود piecesHandler مشکل ایجاد میکند زیرا عدد ایندکس قبل از کلیک بازیکن در لوپ زیاد میشود و ثابت نمی ماند
                    currentPieces[0].setOnMouseClicked(e -> {
                        if( pieceHandler(currentPieces[0], 0) ) {
                            message.setText("");
                            clearMouseEvents();

                            if (diceNumber != 6)
                                changeCurrentPlayer();
                            else
                                sixHandler();

                            dice.setImage(enterKey);
                        }
                        else
                            message.setText("this piece can't move!");
                    });


                     currentPieces[1].setOnMouseClicked(e -> {
                        if( pieceHandler(currentPieces[1], 1) ) {
                            message.setText("");
                            clearMouseEvents();

                            if (diceNumber != 6)
                                changeCurrentPlayer();
                            else
                                sixHandler();

                            dice.setImage(enterKey);
                        }
                        else
                            message.setText("this piece can't move!");
                    });


                     currentPieces[2].setOnMouseClicked(e -> {
                        if( pieceHandler(currentPieces[2], 2) ) {
                            message.setText("");
                            clearMouseEvents();

                            if (diceNumber != 6)
                                changeCurrentPlayer();
                            else
                                sixHandler();

                            dice.setImage(enterKey);
                        }
                        else
                            message.setText("this piece can't move!");
                    });


                     currentPieces[3].setOnMouseClicked(e -> {
                        if( pieceHandler(currentPieces[3], 3) ) {
                            message.setText("");
                            clearMouseEvents();

                            if (diceNumber != 6)
                                changeCurrentPlayer();
                            else
                                sixHandler();

                            dice.setImage(enterKey);
                        }
                        else
                            message.setText("this piece can't move!");
                    });


                }

                else if (diceNumber == 6)
                    sixHandler();

                else {
                    if (currentPlayer.name.length() <= 5)
                        shortMessage.setText("no movable piece for number " + diceNumber + " " + currentPlayer.name + "!");
                    else
                        longMessage.setText("no movable piece for number " + diceNumber + " " + currentPlayer.name + "!");

                    changeCurrentPlayer();
                }

            }

    }

    private void sixHandler(){
        message.setText("throw dice again " + currentPlayer.name);
        throwDice = true;
        gameFinishedChecker();
    }

    private int playerNumber = -1;
    private void pointerTransmitter() {
        playerNumber %= 4 ;

        switch (playerNumber+1) {
            case 1:
                pointer.setLayoutX(pointerLocationX_1);
                pointer.setLayoutY(pointerLocationY_1);
                break;
            case 2:
                pointer.setLayoutX(pointerLocationX_2);
                pointer.setLayoutY(pointerLocationY_2);
                break;
            case 3:
                pointer.setLayoutX(pointerLocationX_3);
                pointer.setLayoutY(pointerLocationY_3);
                break;
            case 4:
                pointer.setLayoutX(pointerLocationX_4);
                pointer.setLayoutY(pointerLocationY_4);
                break;
        }
    }

    private boolean piecesChecker() {
        int pieceNumber;

        for (pieceNumber=0 ; pieceNumber < 4 ; pieceNumber++) {
            Circle piece = currentPieces[pieceNumber] ;

            if (isInHome(piece) && diceNumber == 6)
                return true;

            if (!isInHome(piece)) {
                int currentPath = determinePath(pieceNumber);

                if (currentPath < 16)
                    return true;


                else if (currentPath == 16 && (int) currentPlayer.movements[pieceNumber][currentPath].charAt(7) >= diceNumber)
                    return true;

            }

        }

        return false;
    }

    private boolean isInHome(Node piece) {
        double pieceX = piece.getLayoutX();
        double pieceY = piece.getLayoutY();

        if (pieceX == currentPlayer.homeX1 && pieceY == currentPlayer.homeY1)
            return true;

         if (pieceX == currentPlayer.homeX2 && pieceY == currentPlayer.homeY2)
            return true;

         if (pieceX == currentPlayer.homeX3 && pieceY == currentPlayer.homeY3)
            return true;

        return pieceX == currentPlayer.homeX4 && pieceY == currentPlayer.homeY4;
    }



    private boolean pieceHandler(Circle piece, int pieceNumber){

        if ( isInHome(piece) && diceNumber == 6 ) {
            pieceStarter(piece, currentPlayer.startPointX, currentPlayer.startPointY);
            return true;
        }
        else if (isInHome(piece))
            return false;

        else {
            int stringLength, pathNumber = determinePath(pieceNumber);
            if (pathNumber != 17)
                stringLength = currentPlayer.movements[pieceNumber][pathNumber].length();
            else
                stringLength = 0;

                                                                      /////*  نهایتا سه path در یک حرکت لازم خواهد شد */

            if (stringLength == 8) {
                int pathRemainingSteps = Character.getNumericValue(currentPlayer.movements[pieceNumber][pathNumber].charAt(7));
                String changeString = currentPlayer.movements[pieceNumber][pathNumber];

                String movement= String.valueOf(changeString.charAt(0)) +changeString.charAt(1) + changeString.charAt(2) ;
                char change;

                if (! movement.equals("___"))
                    change = 'x';
                else {
                    movement = String.valueOf(changeString.charAt(4)) + changeString.charAt(5) + changeString.charAt(6);
                    change = 'y';
                }


                if ( pathNumber <= 15 ) {

                    if (diceNumber <= pathRemainingSteps) {
                        if (pathRemainingSteps == 2)
                            pieceTransmitter(piece, change, movement, 40.5, diceNumber);
                        else
                            pieceTransmitter(piece, change, movement, 33.4, diceNumber);

                        if ((pathRemainingSteps -= diceNumber) == 0)
                            pathUpdater(pieceNumber, pathNumber, "");
                        else
                            pathUpdater(pieceNumber, pathNumber, new StringBuilder(changeString).deleteCharAt(7) + String.valueOf(pathRemainingSteps));

                    }

                    return true;
                }
                else if ( pathRemainingSteps >= diceNumber) {
                    pathRemainingSteps -= diceNumber;

                    pieceTransmitter(piece, change, movement, 33.4, diceNumber);

                    if (pathRemainingSteps == 0)
                       pathUpdater(pieceNumber, pathNumber, "");

                    else
                       pathUpdater(pieceNumber, pathNumber, new StringBuilder(changeString).deleteCharAt(7) + String.valueOf(pathRemainingSteps));

                    playerFinishedChecker();
                    return true;
                }
            }


            else if (stringLength == 7) {

                return true;
            }


            return false;
        }

    }



    private int determinePath(int pieceNumber) {
        for (int counter=0 ; counter < 17 ; counter++)
            if (!currentPlayer.movements[pieceNumber][counter].equals(""))
                return counter;

        return 17;
    }


    private void pieceStarter(Node node, double destinationX, double destinationY){
        node.setLayoutX(destinationX);
        node.setLayoutY(destinationY);
    }

    private void pieceTransmitter(Node node, char change, String movement, double distance, int quantity){
        if (movement.equals("dec"))
            distance *= -1;

        TranslateTransition TT = new TranslateTransition(Duration.seconds(0.7),node);
        if (change == 'x')
            TT.setToX(node.getTranslateX() + quantity*distance);
        else
            TT.setToY(node.getTranslateY() + quantity*distance);
        TT.play();
    }

    private void pieceTransmitter(Node node, String movement, boolean diagonalTransition){
        TranslateTransition TT = new TranslateTransition(Duration.seconds(0.5),node);
//        TT.setToX(node.getTranslateX() + );
//        TT.setToY(node.getTranslateY() + );
        TT.play();
    }

    private void pathUpdater(int pieceNumber, int pathNumber, String newPath) {
        System.out.println(currentPlayer.movements[pieceNumber][pathNumber]);

        switch (playerNumber+1) {
            case 1:
                player1.movements[pieceNumber][pathNumber] = newPath;
                break;
            case 2:
                player2.movements[pieceNumber][pathNumber] = newPath;
                break;
            case 3:
                player3.movements[pieceNumber][pathNumber] = newPath;
                break;
            case 4:
                player4.movements[pieceNumber][pathNumber] = newPath;
                break;
        }
        System.out.println(currentPlayer.movements[pieceNumber][pathNumber]);

    }

    private void playerFinishedChecker(){
        int finishedPieces = 0;
        for (int pieceNumber=0 ; pieceNumber < 4 ; pieceNumber++)
            if(determinePath(pieceNumber) >= 16)
                finishedPieces++;

        if (finishedPieces == 4)
            switch (playerNumber+1){
                case 1:
                    if (player1.finishedGame == 0)
                        player1.finishedGame = ++finishedPlayers;
                    break;
                case 2:
                    if (player2.finishedGame == 0)
                     player2.finishedGame = ++finishedPlayers;
                    break;
                case 3:
                    if (player3.finishedGame == 0)
                        player3.finishedGame = ++finishedPlayers;
                    break;
                case 4:
                    if (player4.finishedGame == 0)
                        player4.finishedGame = ++finishedPlayers;
                    break;
            }
    }

    private void clearMouseEvents() {
        for (int counter = 0 ; counter < currentPieces.length ; counter++)
            currentPieces[counter].setOnMouseClicked(null);
    }



    private void changeCurrentPlayer() {

        playerNumber++;
        pointerTransmitter();

        throwDice = true;

        if (currentPlayer == player1) {
            if (player2 != null) {
                currentPlayer = player2;
                currentPieces = greens;
            }

            else if (player3 != null) {
                currentPlayer = player3;
                currentPieces = yellows;
            }

            else {
                currentPlayer = player4;
                currentPieces = blues;
            }

        }

        else if (currentPlayer == player2) {
            if (player3 != null) {
                currentPlayer = player3;
                currentPieces = yellows;
            }

            else if (player4 != null) {
                currentPlayer = player4;
                currentPieces = blues;
            }

            else {
                currentPlayer = player1;
                currentPieces = reds;
            }
        }

        else if (currentPlayer == player3) {
            if (player4 != null) {
                currentPlayer = player4;
                currentPieces = blues;
            }

            else if (player1 != null) {
                currentPlayer = player1;
                currentPieces = reds;
            }

            else {
                currentPlayer = player2;
                currentPieces = greens;
            }

        }

        else {
            if (player1 != null) {
                currentPlayer = player1;
                currentPieces = reds;
            }

            else if (player2 != null) {
                currentPlayer = player2;
                currentPieces = greens;
            }

            else {
                currentPlayer = player3;
                currentPieces = yellows;
            }

        }

        if (! gameFinishedChecker() && currentPlayer.finishedGame !=0)
            changeCurrentPlayer();

    }


    public static void prepareScene(AnchorPane root){
        if (playerChecker(player1))
            piecesSetter(root, getInstance(Color.DARKRED));
        if (playerChecker(player2))
            piecesSetter(root, getInstance(Color.GREEN));
        if (playerChecker(player3))
            piecesSetter(root, getInstance(Color.YELLOW));
        if (playerChecker(player4))
            piecesSetter(root, getInstance(Color.DEEPSKYBLUE));
    }


    private void buttonTransmitter(Boolean bringIn){
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.seconds(1));
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.seconds(0.85));

        if (bringIn) {
            translateTransition.setToX(170);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(0.95);
        }
        else {
            translateTransition.setToX(-170);
            fadeTransition.setFromValue(0.95);
            fadeTransition.setToValue(0);
        }


        ParallelTransition parallelTransition = new ParallelTransition(translateTransition, fadeTransition);
        parallelTransition.setNode(startBtn);
        parallelTransition.play();
    }

    private void prepareAudioFile() {
        try {

            Media audio = new Media(getClass().getResource("Dice_Sound.mp3").toURI().toString());
            mediaPlayer = new MediaPlayer(audio);

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

    public static String gold, silver, bronze, loser ;
    private boolean gameFinishedChecker(){
        int finishedPlayers = 0;
        Player[] players = { player1, player2, player3, player4 };

        for (Player player: players)
            if(player.finishedGame !=0)
                finishedPlayers++;

        if (finishedPlayers == 3) {
            for (Player player : players) {
                if (player.finishedGame == 1)
                    gold = player.name;

                else if (player.finishedGame == 2)
                    silver = player.name;

                else if (player.finishedGame == 3)
                    bronze = player.name;

                else
                    loser = player.name;

            }

            try {
                Main.changeScene("resultScene.fxml", "Results Page", false);
            } catch (IOException e) {
                dice.setVisible(false);
                pointer.setVisible(false);
            }

            return true;
        }

        return false;
    }


}