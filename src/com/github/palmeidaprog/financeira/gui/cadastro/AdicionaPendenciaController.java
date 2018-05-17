package com.github.palmeidaprog.financeira.gui.cadastro;

/*
 * UNICAP - Univesidade Catolica de Pernambuco
 * @author Aluno: Paulo R. Almeida Filho
 * http://www.github.com/palmeidaprog/financeira
 * @email pauloalmeidaf@gmail.com
 * @email palmeidaprogramming@gmail.com
 * Professor: Antonio Canvalcanti
 */

import com.github.palmeidaprog.financeira.clientes.Pendencia;
import com.github.palmeidaprog.financeira.clientes.PendenciaController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AdicionaPendenciaController {
    private static volatile AdicionaPendenciaController instance;

    @FXML
    private Label titleLabel;
    @FXML
    private TextField descrText, valorText;
    @FXML
    private Button addBtn, cancelarBtn;



    private AdicionaPendenciaController() { }

    public static synchronized AdicionaPendenciaController getInstance() {
        if(instance == null) {
            instance = new AdicionaPendenciaController();
        }
        return instance;
    }

    private boolean validaCampos() {
        return !(campoVazio(descrText, "Descrição") || campoVazio(
                valorText,"Valor"));
    }

    private boolean campoVazio(TextField text, String titulo) {
        if(text.getText().trim().isEmpty()) {
            CadastroViewFrontController.getInstance().dialogoErro(titulo,
                    "Necessário preencher o campo de " + titulo + ".");
            text.requestFocus();
            return true;
        }
        return false;
     }

     public void addBtnClicked() {
        if(validaCampos()) {
            AdicionaAutomovelController.getInstance().adicionaPendencia(
                    new Pendencia(Double.parseDouble(valorText.getText()),
                            descrText.getText()));
            cancelarBtnClicked();
        }
     }

     public void cancelarBtnClicked() {
        CadastroViewFrontController.getInstance().closeNovaPendencia();
     }
}
