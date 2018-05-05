package com.github.palmeidaprog.financeira.gui.cadastro;

import com.github.palmeidaprog.financeira.clientes.Automovel;
import com.github.palmeidaprog.financeira.clientes.Cadastro;
import com.github.palmeidaprog.financeira.clientes.Pendencia;
import com.github.palmeidaprog.financeira.clientes.Renda;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.util.HashMap;

public class ControllerViewAdicionaAutomovel {
    private static volatile ControllerViewAdicionaAutomovel instance;
    private Cadastro cadastro;

    @FXML
    private Label descrLabel, bemLabel;
    @FXML
    private TextField marcaText, modeloText, anoModText, anoFabText,
            valorText;
    @FXML
    private VBox tipoVBox;
    @FXML
    private Button adicPendBtn, delPendBtn, criarBtn;
    @FXML
    private TableView<Pendencia> pendTable;
    @FXML
    private TableColumn<Pendencia, String> descrPendCol;
    @FXML
    private TableColumn<Pendencia, double> valorPendCol;

    // Singleton
    private ControllerViewAdicionaAutomovel() { }

    public synchronized static ControllerViewAdicionaAutomovel getInstance() {
        if(instance == null) {
            instance = new ControllerViewAdicionaAutomovel();
        }
        return instance;
    }

    public void setCadastro(Cadastro cadastro) {
        this.cadastro = cadastro;
    }

    public void adicBtnClicked() {
        if(validaCampos()){
            try {
                cadastro.getBens().inserir(new Automovel(Double.parseDouble(
                        valorText.getText()), marcaText.getText(), modeloText
                        .getText(), Integer.parseInt(anoModText.getText()),
                        Integer.parseInt(anoFabText.getText())));
                CadastrosViewController.getInstance().fechaAdiciona();
                ControllerViewCadastro.getInstance().atualizaRendas();
            } catch (NumberFormatException e) {
                CadastrosViewController.getInstance().dialogoErro(
                        "Erro", "Valor digitado não é um numero"
                    + "válido.");
                valorText.requestFocus();
            }
        }
    }

    private boolean validaCampos() {
        if(!campoVazio(marcaText, "Marca") &&
                !campoVazio(modeloText, "Modelo") &&
                !campoVazio(anoModText, "Ano Modelo") &&
                !campoVazio(anoFabText, "Ano de Fabricação") &&
                !campoVazio(valorText, "valor")) {
            return true;
        }
        return false;
    }


    private boolean campoVazio(TextField text, String nome) {
        if(text.getText().trim().isEmpty()) {
            CadastrosViewController.getInstance().dialogoErro("Erro",
                    "Você precisa preencher o campo " + nome + " para " +
                            "adionar o novo automóvel.");
            text.requestFocus();
            return true;
        }
        return false;
    }

    public void cancelBtnClicked() {
        CadastrosViewController.getInstance().fechaAdiciona();
    }



}
