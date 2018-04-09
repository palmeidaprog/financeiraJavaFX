package com.github.palmeidaprog.financeira.gui.cadastro;

import com.github.palmeidaprog.financeira.clientes.Bem;
import com.github.palmeidaprog.financeira.clientes.Cadastro;
import com.github.palmeidaprog.financeira.clientes.Renda;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerViewCadastro {
    private static volatile ControllerViewCadastro instance;

    @FXML private Label credTotalLabel, debNomLabel, bensLabel, credDispLabel;
    @FXML private Label debTotLabel, rendasLabel;
    @FXML private Button adicRendaBtn, delRendaBtn, adicBemBtn, delBemBtn;
    @FXML private Button OkBtn;

    // tables
    @FXML private TreeTableView<Renda> rendaTable;
    @FXML private TreeTableColumn<Renda, String> descrRendaCol;
    @FXML private TreeTableColumn<Renda, String> valorRendaCol;
    @FXML private TreeTableView<Bem> bemTable;
    @FXML private TreeTableColumn<Bem, String> descrBemCol;
    @FXML private TreeTableColumn<Bem, String> valorBemCol;
    private Cadastro cadastro;

    // Singleton
    private ControllerViewCadastro() { }

    public synchronized static ControllerViewCadastro getInstance() {
        if(instance == null) {
            instance = new ControllerViewCadastro();
        }
        return instance;
    }

    public void setCadastro(Cadastro cadastro) {
        this.cadastro = cadastro;
        updateCadastro();
    }

    private void updateCadastro() {
        mostraCadastro();
    }

    private String formataValor(double v) {
        return String.format("%.2f", v);
    }


    private void mostraCadastro() {
        bensLabel.setText(formataValor(cadastro.getBens().totalLiquido()));
        rendasLabel.setText(formataValor(cadastro.getRendas().total()));
        credTotalLabel.setText(formataValor(cadastro.getCredito()
                .getFinanciamento()));
        credDispLabel.setText(formataValor(cadastro.getCredito()
                .getPessoal()));
    }

}
