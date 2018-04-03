package com.github.palmeidaprog.financeira.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root;
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("main.fxml"));
        mainLoader.setController(Controller.getInstance());

        try {
            root = mainLoader.load();
            primaryStage.setTitle("Financeira - Projeto POO");
            primaryStage.setScene(new Scene(root, 900, 600));
            primaryStage.show();
        } catch(IOException e) {
            e.printStackTrace();
        }
        //Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));

    }


    public static void main(String[] args) {
        launch(args);
    }
}
