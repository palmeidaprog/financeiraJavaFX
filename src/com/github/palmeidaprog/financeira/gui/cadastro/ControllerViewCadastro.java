package com.github.palmeidaprog.financeira.gui.cadastro;

import com.github.palmeidaprog.financeira.clientes.Bem;
import com.github.palmeidaprog.financeira.clientes.Cadastro;
import com.github.palmeidaprog.financeira.clientes.Renda;
import com.github.palmeidaprog.financeira.gui.ControllerViewCliente;
import com.github.palmeidaprog.financeira.gui.operacoes_gui.OperacoesViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerViewCadastro implements Initializable {
    private static volatile ControllerViewCadastro instance;

    @FXML private Label credTotalLabel, debNomLabel, bensLabel, credDispLabel;
    @FXML private Label debTotLabel, rendasLabel;
    @FXML private Button adicRendaBtn, delRendaBtn, adicBemBtn, delBemBtn;
    @FXML private Button OkBtn;
    private CadastrosViewController cadastrosViewController =
            CadastrosViewController.getInstance();

    // tables
    @FXML private TableView<Renda> rendaTable;
    @FXML private TableColumn<Renda, String> descrRendaCol;
    @FXML private TableColumn<Renda, String> valorRendaCol;
    @FXML private TableView<Bem> bemTable;
    @FXML private TableColumn<Bem, String> descrBemCol;
    @FXML private TableColumn<Bem, String> valorBemCol;
    private Cadastro cadastro;
    private ObservableList<Bem> bens = FXCollections.observableArrayList();
    private ObservableList<Renda> rendas = FXCollections
            .observableArrayList();

    // Singleton
    private ControllerViewCadastro() { }

    public synchronized static ControllerViewCadastro getInstance() {
        if(instance == null) {
            instance = new ControllerViewCadastro();
        }
        return instance;
    }

    public void initialize(URL u, ResourceBundle rb) {
        try {
            atualizaRendas();
        } catch(NullPointerException e) {
            // faz nada
        }
        descrRendaCol.setCellValueFactory(new PropertyValueFactory<>
                ("descricao"));
        valorRendaCol.setCellValueFactory(new PropertyValueFactory<>
                ("valFormatado"));
        rendaTable.setItems(rendas);
        try {
            atualizaBens();
        } catch(NullPointerException e) {
            // faz nada
        }
        descrBemCol.setCellValueFactory(new PropertyValueFactory<>
                ("descricao"));
        valorBemCol.setCellValueFactory(new PropertyValueFactory<>
                ("valLiqFormatado"));
        bemTable.setItems(bens);
    }

    public <T> ObservableList<T> inicializaLista(List<T> l) {
        return FXCollections.observableArrayList(l);
    }

    public void atualizaRendas() {
        rendas = inicializaLista(cadastro.getRendas().getAll());
    }

    public void atualizaBens() {
        bens = inicializaLista(cadastro.getBens().getAll());
    }

    public void setCadastro(Cadastro cadastro) {
        this.cadastro = cadastro;
        atualizaRendas();
        atualizaBens();
        updateCadastro();
    }

    private void updateCadastro() {
        mostraCadastro();
        ControllerViewCliente.getInstance().mostraCadastro(cadastro);
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

    public void adicRendaBtnClicked() {
        cadastrosViewController.showNovaRenda(cadastro);

    }

    public void delRendaBtnClicked() {
        rendas.remove(rendaTable.getSelectionModel().getSelectedItem());
    }

}
