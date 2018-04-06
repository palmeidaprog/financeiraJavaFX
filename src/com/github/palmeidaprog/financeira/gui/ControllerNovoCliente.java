package com.github.palmeidaprog.financeira.gui;

import com.github.palmeidaprog.financeira.clientes.*;
import com.github.palmeidaprog.financeira.exception.InscricaoInvalidaException;
import com.github.palmeidaprog.financeira.exception.ProcuraSemResultadoException;
import com.github.palmeidaprog.financeira.info.*;
import com.github.palmeidaprog.financeira.info.telefone.CodigoArea;
import com.github.palmeidaprog.financeira.info.telefone.NumeroTelefone;
import com.github.palmeidaprog.financeira.info.telefone.Telefone;
import com.github.palmeidaprog.financeira.info.telefone.TipoTelefone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerNovoCliente implements Initializable {
    @FXML private RadioButton cpfRadio;
    @FXML private RadioButton cnpjRadio;
    @FXML private Label primeiroNomeLabel, meioNomeLabel, sobrenomeLabel;
    @FXML private Label cpfLabel, outroLabel;
    @FXML private TextField primeiroNomeText, meioNomeText, sobrenomeText;
    @FXML private TextField cpfText, ruaText, noText, complText, bairroText;
    @FXML private TextField cidadeText, estadoText, paisText, cepText;
    @FXML private ComboBox<String> tipoNumeroCombo;
    @FXML private ComboBox<String> tipoEnderecoCombo;
    @FXML private ComboBox<String> paisCombo;
    @FXML private TextField codPaisText, codAreaText, numText, comentText;
    @FXML private TextField orgaoText, estadoOrgText, outroText, siglaText;
    @FXML private Button criarClienteBtn;

    private ViewController viewController = ViewController.getInstance();
    private ClienteController clientes;
    private VBox viewCliente;

    private static volatile ControllerNovoCliente instance;
    private ControllerNovoCliente() {
        try {
            clientes = ClienteController.getInstance();
        } catch (IOException e) {
            viewController.dialogoErro("Erro", e.getMessage());
        }
    }
    public synchronized static ControllerNovoCliente getInstance() {
        if(instance == null) {
            instance = new ControllerNovoCliente();
        }
        return instance;
    }



    public void initialize(URL u, ResourceBundle rb) {
        ObservableList<String> itens = FXCollections.observableArrayList();
        for(Pais p : Pais.values()) {
            itens.add(p.formatado());
        }
        paisCombo.setItems(itens);
        paisCombo.setValue(Pais.BRAZIL.formatado());

        ObservableList<String> tiposTel = FXCollections
                .observableArrayList();
        for(TipoTelefone t : TipoTelefone.values()) {
            tiposTel.add(t.formatado());
        }
        tipoNumeroCombo.setItems(tiposTel);
        tipoNumeroCombo.setValue(TipoTelefone.RESIDENCIAL.formatado());

        ObservableList<String> tiposEnd = FXCollections
                .observableArrayList();
        for(TipoEndereco t : TipoEndereco.values()) {
            tiposEnd.add(t.formatado());
        }
        tipoEnderecoCombo.setItems(tiposEnd);
        tipoEnderecoCombo.setValue(TipoEndereco.RESIDENCIAL.formatado());

    }

    private boolean campoVazio(TextField textField, String nome) {
        if(textField.getText().trim().isEmpty()) {
            viewController.dialogoErro("Campo Vazio ", nome + " deve ser preen"
                + "chido.");
            textField.requestFocus();
            return false;
        }
        return true;
    }

    public void paisComboSelected() {
        if(Pais.getPaisPeloNome(paisCombo.getValue()) == Pais.OUTRO) {
            outroLabel.setDisable(false);
            outroText.setDisable(false);
        } else {
            outroLabel.setDisable(true);
            outroText.setDisable(true);
        }
    }


    private boolean validarCampos() {
        if(!campoVazio(primeiroNomeText, (cnpjRadio.isSelected() ?
                "Razão Social" : "Primeiro Nome"))) {
            return false;
        }
        if(cnpjRadio.isSelected() && !campoVazio(meioNomeText,
                "Nome Fantasia")) {
            return false;
        }
        if(cpfRadio.isSelected()) {
            if (!campoVazio(sobrenomeText, "Sobrenome")) {
                return false;
            }
        }
        if(!campoVazio(cpfText, (cnpjRadio.isSelected() ? "CPF" : "CNPJ"))) {
            return false;
        }
        if(!campoVazio(cpfText, (cnpjRadio.isSelected() ? "CPF" : "CNPJ"))) {
            return false;
        }
        if(!campoVazio(ruaText, "Rua")) {
            return false;
        }
        if(!campoVazio(noText, "Numero (Endereço)")) {
            return false;
        }
        if(!campoVazio(bairroText, "Bairro")) {
            return false;
        }
        if(!campoVazio(cidadeText, "Cidade")) {
            return false;
        }
        if(!campoVazio(estadoText, "Estado")) {
            return false;
        }
        if(!campoVazio(siglaText, "Sigla do Estado")) {
            return false;
        }
        if(paisCombo.getValue().equals("Outro") && !campoVazio(outroText,
                "País (Outro / Especificar)")) {
            return false;
        }
        if(!campoVazio(cepText, "CEP")) {
            return false;
        }
        if(!campoVazio(codPaisText, "Telefone Codigo do Pais")) {
            return false;
        }
        if(!campoVazio(codAreaText, "Telefone Codigo de área")) {
            return false;
        }
        if(!campoVazio(numText, "Telefone Número")) {
            return false;
        }
        if(!campoVazio(orgaoText, "Orgão Expedidor")) {
            return false;
        }
        if(!campoVazio(estadoOrgText, "Estado do Orgão Expedidor")) {
            return false;
        }

        return true;
    }

    public void criarClienteBtnClicked() {
        Cliente cliente = null;
        boolean sucesso = false;
        if(validarCampos()) {
            if (cpfRadio.isSelected()) {
                try {
                    Cpf cpf = new Cpf(cpfText.getText(), orgaoText.getText(),
                            new Estado(estadoOrgText.getText()));
                    try {
                        clientes.procurar(cpf);
                        viewController.dialogoErro("Cliente já existe",
                                "Cliente com CPF " + cpf + " já" +
                                        " existe.");
                    } catch (ProcuraSemResultadoException e) {
                        cliente = criaPessoaFisica(cpf);
                        sucesso = true;
                    }
                } catch (InscricaoInvalidaException e) {
                    viewController.dialogoErro("CPF Inválido", "O CPF " + cpfText
                           .getText() + "não é válido.");
                }
            } else {
                try {
                    Cnpj cnpj = new Cnpj(cpfText.getText(), orgaoText.getText(),
                            new Estado(estadoOrgText.getText()));
                    try {
                        clientes.procurar(cnpj);
                        viewController.dialogoErro("Cliente já existe",
                                "Cliente com CNPJ " + cnpj + " já" +
                                        " existe.");
                    } catch (ProcuraSemResultadoException e) {
                        cliente = criaPessoaJuridica(cnpj);
                        sucesso = true;
                    }
                } catch (InscricaoInvalidaException e) {
                    viewController.dialogoErro("CNPJ Inválido",
                            "O CNPJ " + cpfText.getText() +
                                    "não é válido.");
                }
            }
        }
        if(sucesso) {
            Controller.getInstance().resetButtons();
            ViewController.getInstance().showViewCliente(cliente);
        }
    }

    private Cliente criaPessoaFisica(Cpf cpf) {
        PessoaFisica pf = new PessoaFisica(criaEndereco(), criaTelefone(), new
                Cadastro(), primeiroNomeText.getText(), meioNomeText.getText()
                , sobrenomeText.getText(), cpf);
        if(!comentText.getText().trim().isEmpty()) {
            pf.setComentario(comentText.getText());
        }
        try {
            clientes.inserir(pf);
        } catch(IOException e) {
            viewController.dialogoErro("Erro", e.getMessage());
        }
        return pf;
    }

    private Cliente criaPessoaJuridica(Cnpj cnpj) {
        PessoaJuridica pj = new PessoaJuridica(criaEndereco(), criaTelefone(),
                new Cadastro(), primeiroNomeText.getText(), meioNomeText
                .getText(), cnpj);
        if(!comentText.getText().trim().isEmpty()) {
            pj.setComentario(comentText.getText());
        }
        try {
            clientes.inserir(pj);
        } catch(IOException e) {
            ViewController.getInstance().dialogoErro("Erro",
                    e.getMessage());
        }
        return pj;
    }

    private Telefone criaTelefone() {
        return new Telefone(new CodigoArea(codAreaText.getText()), new
                NumeroTelefone(noText.getText()),Pais.getPais(codPaisText
                .getText()), TipoTelefone.getTipo(tipoNumeroCombo
                .getValue()));
    }

    private Endereco criaEndereco() {
        return new Endereco(ruaText.getText(), noText.getText(),
                Pais.getPaisPeloNome(paisCombo.getValue()), new Estado(estadoText.getText(),
                siglaText.getText()), new Cidade(cidadeText.getText()), new
                Bairro(bairroText.getText()), new Cep(cepText.getText()),
                TipoEndereco.getTipo(tipoEnderecoCombo.getValue()));
    }

    public void cpfRadioSelected() {
        if(!cpfRadio.isSelected()) {
            cpfRadio.setSelected(true);
        }
        cnpjRadio.setSelected(false);
        cpfLabel.setText("CPF (Sem Pontos):");
        primeiroNomeLabel.setText("Primeiro Nome:");
        meioNomeLabel.setText("Nome do Meio:");
        sobrenomeLabel.setVisible(true);
        sobrenomeText.setVisible(true);
    }

    public void cnpjRadioSelected() {
        if(!cnpjRadio.isSelected()) {
            cnpjRadio.setSelected(true);
        }
        cpfRadio.setSelected(false);
        cpfLabel.setText("CNPJ (Sem Pontos):");
        primeiroNomeLabel.setText("Razão Social:");
        meioNomeLabel.setText("Nome Fantasia:");
        sobrenomeLabel.setVisible(false);
        sobrenomeText.setVisible(false);
    }
}
