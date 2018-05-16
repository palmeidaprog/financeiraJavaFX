package com.github.palmeidaprog.financeira.gui.operacoes_gui;

import com.github.palmeidaprog.financeira.clientes.Cliente;
import com.github.palmeidaprog.financeira.gui.ViewFrontController;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class OperacoesViewFrontController {
    private CalculaCreditoController calculaCreditoController =
            CalculaCreditoController.getInstance();
    private static volatile OperacoesViewFrontController instance;
    private Stage stage;
    private Scene scene;
    private Parent root;

    private OperacoesViewFrontController() { }

    public synchronized static OperacoesViewFrontController getInstance() {
        if(instance == null) {
            instance = new OperacoesViewFrontController();
        }
        return instance;
    }

    public Cliente getCliente() {
        return ViewFrontController.getInstance().getCliente();
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

    public void showNovaOperacao(Cliente cliente) {
        if(stage != null && stage.isShowing()) {
            stage.requestFocus();
        } else {
            stage = new Stage();
            FXMLLoader mainLoader = new FXMLLoader(getClass()
                    .getResource("NovoCreditoView.fxml"));
            mainLoader.setController(NovoCreditoController.getInstance());
            try {
                root = mainLoader.load();
                stage.setTitle("Financeira - Operações Financeiras");
                scene = new Scene(root, 600, 350);
                stage.setScene(scene);
                NovoCreditoController.getInstance().setCliente(cliente);
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void showFinanciamento() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource
                ("CalculaCreditoView.fxml"));
        loader.setController(CalculaCreditoController.getInstance());
        CalculaCreditoController.getInstance().setTipo("Financiamento");
        try {
            root = loader.load();
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showEmprestimo() {
        CalculaCreditoController.getInstance().setTitle("Empréstimo " +
                "Pessoal");
        loadVBox("NovoCreditoEmprestimoView.fxml",
                "Empréstimo")
                .setController(ControllerViewNovoCreditoEmprestimo
                        .getInstance());
    }

    public void showOutraOperacao() {
        CalculaCreditoController.getInstance().setTitle("Outra Operação "
                + "Financeira");
        loadVBox("NovoCreditoOperacaoView.fxml",
                "Outra Operação Financeira")
                .setController(NovoCreditoOperacaoController
                        .getInstance());
    }

    public FXMLLoader loadVBox(String fxml, String tipo) {
        VBox root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource
                (fxml));
        calculaCreditoController.setTipo(tipo);
        try {
            root = loader.load();
            calculaCreditoController.setTipoVBox(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loader;
    }

    public void dialogoErro(String titulo, String msg) {
        ViewFrontController.getInstance().dialogoErro(titulo, msg);
    }

    //--Suporte---------------------------------------------------------------




    //TODO: keypressed getKeyChar() == 8
}
