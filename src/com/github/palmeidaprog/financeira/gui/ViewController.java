package com.github.palmeidaprog.financeira.gui;

import com.github.palmeidaprog.financeira.clientes.Cliente;
import com.github.palmeidaprog.financeira.gui.cadastro.CadastrosViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ViewController { // factory (frankenstein) // viewFactory
    private static volatile ViewController instance;

    private ViewController() { }

    public synchronized static ViewController getInstance() {
        if(instance == null) {
            instance = new ViewController();
        }
        return instance;
    }

    public void showNovoCliente() {
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

    public void showViewCliente(Cliente cliente) {
        VBox viewCliente;
        FXMLLoader vClienteLoad = new FXMLLoader(getClass()
                .getResource("view_cliente.fxml"));
        vClienteLoad.setController(ControllerViewCliente.getInstance());
        try {
            viewCliente = vClienteLoad.load();
            Controller.getInstance().getMain().setCenter(viewCliente);
            ControllerViewCliente.getInstance().mostraCliente(cliente);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public Cliente getCliente() {
        return ControllerViewCliente.getInstance().getCliente();
    }

    public void dialogoErro(String titulo, String texto) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(texto);
        alert.showAndWait();
    }

}
