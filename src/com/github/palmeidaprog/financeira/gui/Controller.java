package com.github.palmeidaprog.financeira.gui;

import com.github.palmeidaprog.financeira.clientes.Cliente;
import com.github.palmeidaprog.financeira.clientes.ClienteController;
import com.github.palmeidaprog.financeira.exception.InscricaoInvalidaException;
import com.github.palmeidaprog.financeira.exception.ProcuraSemResultadoException;
import com.github.palmeidaprog.financeira.info.Cnpj;
import com.github.palmeidaprog.financeira.info.Cpf;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Controller {
    private ClienteController clientes;

    private volatile static Controller instance;
    @FXML private BorderPane mainPane;
    @FXML private TextField cpfText;
    @FXML private Button okCpfBtn, criaBtn, escolherBtn;
    @FXML private RadioButton cpfRadio, cnpjRadio;
    @FXML private Label cpfLabel;
    @FXML private VBox escolherVBox;

    private Controller() {
        try {
            clientes = ClienteController.getInstance();
        } catch (IOException e) {
            dialogoErro("Erro", e.getMessage());
            Platform.exit();
        }
    }

    public synchronized static Controller getInstance() {
        if(instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public BorderPane getMain() {
        return mainPane;
    }

    //--Eventos Menu Clientes-------------------------------------------------

    public void resetButtons() {
        criaBtn.setDisable(false);
        escolherBtn.setDisable(false);
    }

    public void escolherBtnClicked() {
        resetButtons();
        mainPane.setCenter(escolherVBox);
    }

    public void criaBtnClicked() {

        resetButtons();
        criaBtn.setDisable(true);


    }

    //--Eventos---------------------------------------------------------------

    public void okCpfBtnClicked() {
        Cliente cliente = null;
        if(cpfRadio.isSelected()) {

            try {
                Cpf cpf = new Cpf(cpfText.getText());
                cliente = clientes.procurar(cpf);
                ControllerNovoCliente.getInstance().showViewCliente(cliente);
            } catch(InscricaoInvalidaException e) {
                dialogoErro("CPF Inválido", e.getMessage());
            } catch(ProcuraSemResultadoException e) {
                dialogoErro("Procura Sem Resultado", e.getMessage());
            }
        } else {
            try {
                Cnpj cnpj = new Cnpj(cpfText.getText());
                cliente = clientes.procurar(cnpj);
                ControllerNovoCliente.getInstance().showViewCliente(cliente);
            } catch(InscricaoInvalidaException e) {
                dialogoErro("CNPJ Inválido", e.getMessage());
            } catch(ProcuraSemResultadoException e) {
                dialogoErro("Procura Sem Resultado", e.getMessage());
            }
        }
    }

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
