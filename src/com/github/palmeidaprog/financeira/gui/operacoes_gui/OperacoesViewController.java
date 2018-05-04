package com.github.palmeidaprog.financeira.gui.operacoes_gui;

import com.github.palmeidaprog.financeira.gui.ViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class OperacoesViewController {
    private ControllerViewCalculaCredito controllerViewCalculaCredito =
            ControllerViewCalculaCredito.getInstance();
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

    public void showFinanciamento() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource
                ("view_calcula_credito.fxml"));
        loader.setController(ControllerViewCalculaCredito.getInstance());
        ControllerViewCalculaCredito.getInstance().setTipo("Financiamento");
        try {
            root = loader.load();
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showEmprestimo() {
        ControllerViewCalculaCredito.getInstance().setTitle("Empréstimo " +
                "Pessoal");
        loadVBox("view_novo_credito_emprestimo.fxml",
                "Empréstimo")
                .setController(ControllerViewNovoCreditoEmprestimo
                        .getInstance());
    }

    public void showOutraOperacao() {
        ControllerViewCalculaCredito.getInstance().setTitle("Outra Operação "
                + "Financeira");
        loadVBox("view_novo_credito_outra_operacao.fxml",
                "Outra Operação Financeira")
                .setController(ControllerViewNovoCreditoOutraOperacao
                        .getInstance());
    }

    public FXMLLoader loadVBox(String fxml, String tipo) {
        VBox root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource
                (fxml));
        controllerViewCalculaCredito.setTipo(tipo);
        try {
            root = loader.load();
            controllerViewCalculaCredito.setTipoVBox(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loader;
    }

    public void dialogoErro(String titulo, String msg) {
        ViewController.getInstance().dialogoErro(titulo, msg);
    }

    //--Suporte---------------------------------------------------------------




    //TODO: keypressed getKeyChar() == 8
}
