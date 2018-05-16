package com.github.palmeidaprog.financeira.gui;

import com.github.palmeidaprog.financeira.clientes.Cliente;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ViewFrontController { // factory (frankenstein) // viewFactory
    private static volatile ViewFrontController instance;

    private ViewFrontController() { }

    public synchronized static ViewFrontController getInstance() {
        if(instance == null) {
            instance = new ViewFrontController();
        }
        return instance;
    }

    public void showNovoCliente() {
        VBox root;
        FXMLLoader novoClienteLoaader = new FXMLLoader(getClass()
                .getResource("NovoClienteView.fxml"));
        novoClienteLoaader.setController(NovoClienteController.getInstance());
        try {
            root = novoClienteLoaader.load();
            MainController.getInstance().getMain().setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showViewCliente(Cliente cliente) {
        VBox viewCliente;
        FXMLLoader vClienteLoad = new FXMLLoader(getClass()
                .getResource("MostraClienteView.fxml"));
        vClienteLoad.setController(MostraClienteController.getInstance());
        try {
            viewCliente = vClienteLoad.load();
            MainController.getInstance().getMain().setCenter(viewCliente);
            MostraClienteController.getInstance().mostraCliente(cliente);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public Cliente getCliente() {
        return MostraClienteController.getInstance().getCliente();
    }

    public void dialogoErro(String titulo, String texto) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(texto);
        alert.showAndWait();
    }

}
