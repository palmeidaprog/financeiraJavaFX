package com.github.palmeidaprog.financeira.gui;

import com.github.palmeidaprog.financeira.info.Cpf;
import com.github.palmeidaprog.financeira.info.Estado;
import com.github.palmeidaprog.financeira.info.TipoEndereco;
import com.github.palmeidaprog.financeira.info.telefone.TipoTelefone;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class ControllerNovoCliente {
    @FXML private RadioButton cpfRadio;
    @FXML private RadioButton cnpjRadio;
    @FXML private Label primeiroNomeLabel, meioNomeLabel, sobrenomeLabel;
    @FXML private TextField primeiroNomeText, meioNomeText, sobrenomeText;
    @FXML private Label cpfLabel;
    @FXML private TextField cpfText, ruaText, noText, complText, bairroText;
    @FXML private TextField cidadeText, estadoText, paisText, cepText;
    @FXML private ComboBox<TipoTelefone> tipoNumeroCombo;
    @FXML private ComboBox<TipoEndereco> tipoEnderecoCombo;
    @FXML private TextField codPaisText, codAreaText, numText, comentText;
    @FXML private TextField orgaoText, estadoOrgText;
    @FXML private Button criarClienteBtn;

    private static volatile ControllerNovoCliente instance;
    private ControllerNovoCliente() { }
    public synchronized static ControllerNovoCliente getInstance() {
        if(instance == null) {
            instance = new ControllerNovoCliente();
        }
        return instance;
    }

    private boolean campoVazio(TextField textField, String nome) {
        if(textField.getText().trim().isEmpty()) {
            dialogoErro("Campo Vazio", nome + " deve ser preench"
                + "ido.");
            textField.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validarCampos() {
        /*primeiroNomeText, meioNomeText, sobrenomeText
        cpfText, ruaText, noText, complText, bairroText
        cidadeText, estadoText, paisText, cepText
        codPaisText, codAreaText, numText, comentText
        orgaoText, estadoOrgText*/

    }

    public void criarClienteBtnClicked() {
        /*if(cpfRadio.isSelected()) {
            Cpf cpf;
            try {

                cpf = new Cpf(cpfText.getText(), orgaoText.getText(),
                    new Estado(estadoOrgText.getText()));

                criaPessoaFisica(cpf);
            } catch(IOException e) {

            } catch()

        }*/

    }

    private void criaPessoaFisica

    public void cpfRadioSelected() {
        if(!cpfRadio.isSelected()) {
            cpfRadio.setSelected(true);
        }
        cnpjRadio.setSelected(false);
        cpfLabel.setText("CPF (Sem Pontos):");
        cpfText.setPromptText("Digite o CPF do cliente");

    }

    public void cnpjRadioSelected() {
        if(!cnpjRadio.isSelected()) {
            cnpjRadio.setSelected(true);
        }
        cpfRadio.setSelected(false);
        cpfLabel.setText("CNPJ (Sem Pontos):");
        cpfText.setPromptText("Digite o CNPJ do cliente");
    }

    private void dialogoErro(String titulo, String texto) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(texto);
        alert.showAndWait();
    }


}
