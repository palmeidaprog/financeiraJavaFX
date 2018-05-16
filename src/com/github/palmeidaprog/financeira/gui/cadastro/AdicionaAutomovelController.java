package com.github.palmeidaprog.financeira.gui.cadastro;

import com.github.palmeidaprog.financeira.clientes.Automovel;
import com.github.palmeidaprog.financeira.clientes.Cadastro;
import com.github.palmeidaprog.financeira.clientes.Pendencia;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AdicionaAutomovelController implements Initializable {
    private static volatile AdicionaAutomovelController instance;
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
    private TableColumn<Pendencia, String> valorPendCol;
    private ObservableList<Pendencia> pendencias;

    // Singleton
    private AdicionaAutomovelController() { }

    public synchronized static AdicionaAutomovelController getInstance() {
        if(instance == null) {
            instance = new AdicionaAutomovelController();
        }
        return instance;
    }

    public void initialize(URL u, ResourceBundle rb) {
        descrPendCol.setCellValueFactory(cellData -> cellData.getValue()
                .descricaoPProperty());
        valorPendCol.setCellValueFactory(cellData -> cellData.getValue()
                .valorPProperty());
    }

    public void setCadastro(Cadastro cadastro) {
        this.cadastro = cadastro;
    }

    public void adicBtnClicked() {
        if(validaCampos()){
            try {
                // remover depois (verificar) TODO: verificar
                cadastro.getBens().inserir(new Automovel(Double.parseDouble(
                        valorText.getText()), marcaText.getText(), modeloText
                        .getText(), Integer.parseInt(anoModText.getText()),
                        Integer.parseInt(anoFabText.getText())));
                CadastroViewFrontController.getInstance().fechaAdiciona();
                EditaCadastroController.getInstance().atualizaRendas();
            } catch (NumberFormatException e) {
                CadastroViewFrontController.getInstance().dialogoErro(
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
            CadastroViewFrontController.getInstance().dialogoErro("Erro",
                    "Você precisa preencher o campo " + nome + " para " +
                            "adionar o novo automóvel.");
            text.requestFocus();
            return true;
        }
        return false;
    }

    public void cancelBtnClicked() {
        CadastroViewFrontController.getInstance().fechaAdiciona();
    }



}
