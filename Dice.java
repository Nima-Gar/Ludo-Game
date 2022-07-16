package com.example.ludo;


import javafx.scene.image.Image;

import java.util.Objects;


public class Dice {


    static Image enterKey = new Image(Objects.requireNonNull(Dice.class.getResourceAsStream("enter_key.jpg")));
    static Image dice1 = new Image(Objects.requireNonNull(Dice.class.getResourceAsStream("dice_1.jpg")));
    static Image dice2 = new Image(Objects.requireNonNull(Dice.class.getResourceAsStream("dice_2.jpg")));
    static Image dice3 = new Image(Objects.requireNonNull(Dice.class.getResourceAsStream("dice_3.jpg")));
    static Image dice4 = new Image(Objects.requireNonNull(Dice.class.getResourceAsStream("dice_4.jpg")));
    static Image dice5 = new Image(Objects.requireNonNull(Dice.class.getResourceAsStream("dice_5.jpg")));
    static Image dice6 = new Image(Objects.requireNonNull(Dice.class.getResourceAsStream("dice_6.jpg")));



//    public static void imageHandler(ImageView dice) {
//        System.out.println(diceNumber);
//        dice.setOnKeyPressed(e -> {
//                    switch (diceNumber) {
//                        case 1:
//                            dice.setImage(dice1);
//                            break;
//                        case 2:
//                            dice.setImage(dice2);
//                            break;
//                        case 3:
//                            dice.setImage(dice3);
//                            break;
//                        case 4:
//                            dice.setImage(dice4);
//                            break;
//                        case 5:
//                            dice.setImage(dice5);
//                            break;
//                        case 6:
//                            dice.setImage(dice6);
//                            break;
//                    }
//
//                    dice.setOnKeyPressed(null);
//                });
//    }


    private static int diceThrowingCounter = 0;
    private static int diceNumber = 3;
    private static int coefficient = 2;
    private static int coefficientCounter = 0;
    private static int addedNumber = 3 ;
    private static int lastNumber = 0 ;
    private static int numberBeforeLastNumber = 0 ;


    public static int throwDice() {
        diceNumber = (coefficient * diceNumber + addedNumber) % 7;                           // mod 7
        diceThrowingCounter++;
        coefficientCounter++;

        coefficientController();
        zeroHandler();

        if ((diceNumber == lastNumber) && (diceNumber == numberBeforeLastNumber))
            throwDice();

        if (diceThrowingCounter > 1)
            numberBeforeLastNumber = lastNumber;

        lastNumber = diceNumber;

         return diceNumber;
    }


    private static void coefficientController() {
        if(coefficientCounter == 3) {
            coefficient++;
            coefficientCounter = 0;
            addedNumber++;
        }
    }

    private static void zeroHandler() {
        if(diceNumber == 0) {
            diceNumber+=addedNumber;                                            // to prevent repeating numbers (necessary where dice number is 0)
            throwDice();
        }
    }

}
