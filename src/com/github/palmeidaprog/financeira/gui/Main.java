package com.github.palmeidaprog.financeira.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        Parent root;
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource(
                "MainView.fxml"));
        mainLoader.setController(MainController.getInstance());

        try {
            root = mainLoader.load();
            primaryStage.setTitle("Financeira - Projeto POO");
            primaryStage.setScene(new Scene(root, 900, 600));
            primaryStage.show();
        } catch(IOException e) {
            e.printStackTrace();
        }
        //Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
