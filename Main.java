package com.example.ludo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static com.example.ludo.DBManager.filler;
import static com.example.ludo.Ludo.prepareScene;


public class Main extends Application {
    private static Stage stageCopy;

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        filler();
        stageCopy = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("start.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 730);
        stage.setTitle("Start Page");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


    static Scene scene;
    public static void changeScene(String newFxml, String title, boolean ludoScene) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(newFxml));

        if (ludoScene) {
            AnchorPane root = fxmlLoader.load();
            prepareScene(root);
            scene = new Scene(root, 1000, 780);
        }
        else
            scene = new Scene(fxmlLoader.load(), 900, 730);

        stageCopy.setTitle(title);
        stageCopy.setResizable(false);
        stageCopy.setScene(scene);
    }



}