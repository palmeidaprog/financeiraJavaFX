package com.github.palmeidaprog.financeira.gui.cadastro;

import com.github.palmeidaprog.financeira.clientes.Cadastro;
import com.github.palmeidaprog.financeira.clientes.PendenciaController;
import com.github.palmeidaprog.financeira.gui.ViewFrontController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class CadastroViewFrontController {
    private Stage stage, adiciona, automovel, pendencia;
    private Scene scene;
    private Parent root;

    //--Singleton-------------------------------------------------------------
    private CadastroViewFrontController() { }
    private static volatile CadastroViewFrontController instance;

    public synchronized static CadastroViewFrontController getInstance() {
        if(instance == null) {
            instance = new CadastroViewFrontController();
        }
        return instance;
    }

    //--Chamadas de Views-----------------------------------------------------

    // Editar Cadastro
    public void showEditaCadastro(Cadastro cadastro) {
        if(stage != null && stage.isShowing()) {
            stage.requestFocus();
        } else {
            stage = new Stage();
            FXMLLoader mainLoader = new FXMLLoader(getClass()
                    .getResource("EditaCadastroView.fxml"));

            mainLoader.setController(EditaCadastroController.getInstance());
            try {
                root = mainLoader.load();
                stage.setTitle("Financeira - Editando Cadastro");
                scene = new Scene(root, 770, 440);
                stage.setScene(scene);
                EditaCadastroController.getInstance().setCadastro(cadastro);
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void showAutomovel(Cadastro cadastro, String title) {
        // todo: resolver o nao aparencendo
        if(automovel != null && automovel.isShowing()) {
            automovel.requestFocus();
            return;
        }

        automovel = new Stage();
        FXMLLoader autoLoader = new FXMLLoader(getClass().getResource(
                "AdicionaAutomovelView.fxml"));

        autoLoader.setController(AdicionaAutomovelController
                .getInstance());
        try {
            Parent root = autoLoader.load();
            automovel.setScene(new Scene(root, 585, 544));
            automovel.setTitle(title);
            AdicionaAutomovelController.getInstance().setCadastro(
                    cadastro);
        } catch(IOException e) {
            e.printStackTrace();
        }
        automovel.show();
    }

    // fecha janela de adicionar automovel
    public void fechaAutomovel() {
        automovel.close();
    }

    // Adicionar pendencia (Chamado de AdicionarAutomovel)
    public void showNovaPendencia(PendenciaController novaPendencias) {
        VBox root = null;

        if(pendencia != null && pendencia.isShowing()) {
            pendencia.requestFocus();
        } else {
            pendencia = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource
                    ("AdicionaPendenciaView.fxml"));
            loader.setController(AdicionaPendenciaController.getInstance());
            pendencia.setTitle("Financeira - Adicionando Nova Pendencia");
            loadStage(loader, pendencia);
            AdicionaPendenciaController.getInstance()
                    .setPendencias(novaPendencias);
            pendencia.showAndWait();
        }
    }

    private void loadStage(FXMLLoader loader, Stage stage) {
        try {
            root = loader.load();
            Scene scene = new Scene(root, 520, 220);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // fecha Adicioanr Pendencia
    public void closeNovaPendencia() {
        pendencia.close();
    }


    public void showNovaRenda(Cadastro cadastro) {
        VBox root = null;

        if(adiciona != null && adiciona.isShowing()) {
            adiciona.requestFocus();
        } else {
            adiciona = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource
                    ("AdicionaRendaView.fxml"));
            loader.setController(AdicionaRendaController.getInstance());
            AdicionaRendaController.getInstance().setCadastro(cadastro);
            adiciona.setTitle("Financeira - Adicionando Nova Renda");
            loadStage(loader, adiciona);
            adiciona.showAndWait();
        }
    }

    public void fechaAdiciona() {
        adiciona.close();
    }

    //--Dialogos--------------------------------------------------------------

    public void dialogoErro(String titulo, String msg) {
        ViewFrontController.getInstance().dialogoErro(titulo, msg);
    }
}
