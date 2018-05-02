package com.github.palmeidaprog.financeira.gui.cadastro;

import com.github.palmeidaprog.financeira.clientes.Cadastro;
import com.github.palmeidaprog.financeira.gui.ViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class CadastrosViewController {
    private Stage stage, adiciona;
    private Scene scene;
    private Parent root;

    // Singleton
    private CadastrosViewController() { }
    private static volatile CadastrosViewController instance;

    public synchronized static CadastrosViewController getInstance() {
        if(instance == null) {
            instance = new CadastrosViewController();
        }
        return instance;
    }

    public void showEditaCadastro(Cadastro cadastro) {
        if(stage != null && stage.isShowing()) {
            stage.requestFocus();
        } else {
            stage = new Stage();
            FXMLLoader mainLoader = new FXMLLoader(getClass()
                    .getResource("view_cadastro.fxml"));

            mainLoader.setController(ControllerViewCadastro.getInstance());
            try {
                root = mainLoader.load();
                stage.setTitle("Financeira - Editando Cadastro");
                scene = new Scene(root, 770, 440);
                stage.setScene(scene);
                ControllerViewCadastro.getInstance().setCadastro(cadastro);
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void showNovaRenda(Cadastro cadastro) {
        VBox root = null;

        if(adiciona != null && adiciona.isShowing()) {
            adiciona.requestFocus();
        } else {
            adiciona = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource
                    ("view_adiciona_Renda.fxml"));
            loader.setController(ControllerViewAdicionaRenda.getInstance());
            ControllerViewAdicionaRenda.getInstance().setCadastro(cadastro);
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
        ViewController.getInstance().dialogoErro(titulo, msg);
    }
}
