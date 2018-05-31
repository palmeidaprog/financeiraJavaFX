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
import com.github.palmeidaprog.financeira.gui.validacoes.ValidaMoeda;
import com.github.palmeidaprog.financeira.info.telefone.NumeroTelefone;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AdicionaPendenciaController implements Initializable {
    @FXML
    private Label titleLabel;
    @FXML
    private TextField descrText, valorText;
    @FXML
    private Button addBtn, cancelarBtn;
    private PendenciaController pendencias;

    //--Singleton-------------------------------------------------------------
    private static volatile AdicionaPendenciaController instance;
    private AdicionaPendenciaController() { }

    public static synchronized AdicionaPendenciaController getInstance() {
        if(instance == null) {
            instance = new AdicionaPendenciaController();
        }
        return instance;
    }

    //--Get/Set---------------------------------------------------------------

    public PendenciaController getPendencias() {
        return pendencias;
    }

    public void setPendencias(PendenciaController pendencias) {
        this.pendencias = pendencias;
    }


    //--Initialize------------------------------------------------------------

    public void initialize(URL u, ResourceBundle rb) {
        ValidaMoeda valida = new ValidaMoeda(valorText);
    }

    //--Eventos---------------------------------------------------------------

    public void addBtnClicked() {
        if(validaCampos()) {
            try {
                AdicionaAutomovelController.getInstance().adicionaPendencia(
                        new Pendencia(Double.parseDouble(valorText.getText()),
                                descrText.getText()));
                cancelarBtnClicked();
            } catch(NumberFormatException e) {
                CadastroViewFrontController.getInstance().dialogoErro(
                        "Formato Inválido", "Formato do valor " +
                                "inválido.");
                valorText.requestFocus();
            }
        }
    }

    public void cancelarBtnClicked() {
        CadastroViewFrontController.getInstance().closeNovaPendencia();
    }


    //--Validaçòes------------------------------------------------------------

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
}
