package com.github.palmeidaprog.financeira.gui.operacoes_gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class OperacoesViewController {
    private static volatile OperacoesViewController instance;
    private Stage stage;
    private Scene scene;
    private Parent root;

    private OperacoesViewController() { }

    public synchronized static OperacoesViewController getInstance() {
        if(instance == null) {
            instance = new OperacoesViewController();
        }
        return instance;
    }

    public Scene getMainScene() {
        return scene;
    }

    public Stage getMainStage() {
        return stage;
    }

    public Parent getMainRoot() {
        return root;
    }

    public void showNovaOperacao() {
        if(stage != null && stage.isShowing()) {
            stage.requestFocus();
        } else {
            stage = new Stage();
            FXMLLoader mainLoader = new FXMLLoader(getClass()
                    .getResource("view_novo_credito.fxml"));
            mainLoader.setController(ControllerViewNovoCredito.getInstance());
            try {
                root = mainLoader.load();
                stage.setTitle("Financeira - Operações Financeiras");
                scene = new Scene(root, 600, 350);
                stage.setScene(scene);
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
