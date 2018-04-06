package com.github.palmeidaprog.financeira.gui.operacoes_gui;

import com.github.palmeidaprog.financeira.gui.Controller;
import com.github.palmeidaprog.financeira.gui.ControllerNovoCliente;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OperacoesViewController implements Initializable {
    public static volatile OperacoesViewController instance;

    private OperacoesViewController() { }

    public synchronized static OperacoesViewController getInstance() {
        if(instance == null) {
            instance = new OperacoesViewController();
        }
        return instance;
    }

    private void initialize(URL u, ResourceBundle rb) {

    }

    public void novoClienteLoad() {
        VBox root;
        FXMLLoader novoClienteLoaader = new FXMLLoader(getClass()
                .getResource("novo_cliente.fxml"));
        novoClienteLoaader.setController(ControllerNovoCliente.getInstance());
        try {
            root = novoClienteLoaader.load();
            Controller.getInstance().getMain().setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
