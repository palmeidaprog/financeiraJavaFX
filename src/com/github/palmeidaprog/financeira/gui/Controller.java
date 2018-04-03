package com.github.palmeidaprog.financeira.gui;

import com.github.palmeidaprog.financeira.clientes.ClienteController;
import com.github.palmeidaprog.financeira.exception.InscricaoInvalidaException;
import com.github.palmeidaprog.financeira.exception.ProcuraSemResultadoException;
import com.github.palmeidaprog.financeira.info.Cnpj;
import com.github.palmeidaprog.financeira.info.Cpf;
import com.github.palmeidaprog.financeira.info.Estado;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class Controller {
    private ClienteController clientes = new ClienteController();

    private volatile static Controller instance;
    @FXML private TextField cpfText;
    @FXML private Button okCpfBtn;
    @FXML private RadioButton cpfRadio, cnpjRadio;

    private Controller() { }
    public synchronized static Controller getInstance() {
        if(instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    //--Eventos-------------------------------------------------

    public void okCpfBtnClicked() {
        if(cpfRadio.isSelected()) {
            try {
                Cpf cpf = new Cpf(cpfText.getText());
                clientes.procurar(cpf);
            } catch(InscricaoInvalidaException e) {
                dialogoErro("CPF Inválido", e.getMessage());
            } catch(ProcuraSemResultadoException e) {
                dialogoErro("Procura Sem Resultado", e.getMessage());
            }
        } else {
            try {
                Cnpj cnpj = new Cnpj(cpfText.getText());
                clientes.procurar(cnpj);
            } catch(InscricaoInvalidaException e) {
                dialogoErro("CNPJ Inválido", e.getMessage());
            } catch(ProcuraSemResultadoException e) {
                dialogoErro("Procura Sem Resultado", e.getMessage());
            }
        }
    }

    public void cpfRadioSelected() {
        cnpjRadio.setSelected(!cpfRadio.isSelected());
    }

    public void cnpjRadioSelected() {
        cpfRadio.setSelected(!cnpjRadio.isSelected());
    }

    private void dialogoErro(String titulo, String texto) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(texto);
        alert.showAndWait();
    }

}
