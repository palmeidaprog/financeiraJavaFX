package com.github.palmeidaprog.financeira.gui.cadastro;

import com.github.palmeidaprog.financeira.clientes.Cadastro;
import com.github.palmeidaprog.financeira.gui.ViewFrontController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class EditaCadastroController {
    private Stage stage, adiciona, automovel;
    private Scene scene;
    private Parent root;

    // Singleton
    private EditaCadastroController() { }
    private static volatile EditaCadastroController instance;

    public synchronized static EditaCadastroController getInstance() {
        if(instance == null) {
            instance = new EditaCadastroController();
        }
        return instance;
    }

    public void showEditaCadastro(Cadastro cadastro) {
        if(stage != null && stage.isShowing()) {
            stage.requestFocus();
        } else {
            stage = new Stage();
            FXMLLoader mainLoader = new FXMLLoader(getClass()
                    .getResource("EditaCadastroView.fxml"));

            mainLoader.setController(CadastroViewFrontController.getInstance());
            try {
                root = mainLoader.load();
                stage.setTitle("Financeira - Editando Cadastro");
                scene = new Scene(root, 770, 440);
                stage.setScene(scene);
                CadastroViewFrontController.getInstance().setCadastro(cadastro);
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
            automovel.show();
        } catch(IOException e) {
            e.printStackTrace();
        }
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
            try {
                root = loader.load();
                Scene scene = new Scene(root, 520, 220);
                adiciona.setScene(scene);
                adiciona.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    public void fechaAdiciona() {
        adiciona.close();
    }

    public void dialogoErro(String titulo, String msg) {
        ViewFrontController.getInstance().dialogoErro(titulo, msg);
    }
}
