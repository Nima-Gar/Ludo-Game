package com.example.ludo;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Piece {

    private final static int radius = 10;

    static Circle[] reds = new Circle[4];
    static Circle[] greens = new Circle[4];
    static Circle[] yellows = new Circle[4];
    static Circle[] blues = new Circle[4];

    public static Circle[] getInstance(Paint paint) {

        if (paint == Color.DARKRED) {
            new Piece(reds, paint, 335.5, 69, 291.5, 112, 374.7, 112, 335, 154);
            return reds;
        }
        else if (paint == Color.GREEN) {
            new Piece(greens, paint, 662.5, 69, 619, 112, 701, 112, 662.5, 154);
            return greens;
        }
        else if (paint == Color.YELLOW){
            new Piece(yellows, paint, 662.5, 395, 619, 439, 701.5, 439, 662.5, 480);
            return yellows;
        }
        else {
            new Piece(blues, paint, 335.7, 395, 291, 439, 375, 439, 335 ,480.5);
            return blues;
        }
    }

    private Piece(Circle[] circles, Paint paint, double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {

        double x=0, y=0;

        for (int counter=0 ; counter<4 ; counter++){
            circles[counter] = new Circle();

            switch (counter+1){
                case 1:
                    x = x1;
                    y = y1;
                    break;
                case 2:
                    x = x2;
                    y = y2;
                    break;
                case 3:
                    x = x3;
                    y = y3;
                    break;
                case 4:
                    x = x4;
                    y = y4;
                    break;
            }
            circles[counter].setLayoutX(x);
            circles[counter].setLayoutY(y);
            circles[counter].setRadius(radius);
            circles[counter].setFill(Color.BLACK);
            circles[counter].setStroke(paint);
            circles[counter].setStrokeWidth(12);
        }
    }

    public static void piecesSetter(AnchorPane root, Circle[] pieces) {
        for (Circle circle: pieces)
            root.getChildren().add(circle);
    }
}

