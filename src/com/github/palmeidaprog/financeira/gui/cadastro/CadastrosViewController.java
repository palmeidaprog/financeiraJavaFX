package com.github.palmeidaprog.financeira.gui.cadastro;

import com.github.palmeidaprog.financeira.clientes.Cadastro;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CadastrosViewController {
    private Stage stage;
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


}
