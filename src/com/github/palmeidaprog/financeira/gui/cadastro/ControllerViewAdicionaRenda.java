package com.github.palmeidaprog.financeira.gui.cadastro;

import com.github.palmeidaprog.financeira.clientes.Cadastro;
import com.github.palmeidaprog.financeira.clientes.Renda;
import com.github.palmeidaprog.financeira.clientes.RendaController;
import com.github.palmeidaprog.financeira.gui.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControllerViewAdicionaRenda {
    private static volatile ControllerViewAdicionaRenda instance;
    private Cadastro cadastro;

    @FXML private Label titleLabel;
    @FXML private TextField descrText, valorText;
    @FXML private Button adicBtn,cancelBtn;

    // Singleton
    private ControllerViewAdicionaRenda() { }

    public synchronized static ControllerViewAdicionaRenda getInstance() {
        if(instance == null) {
            instance = new ControllerViewAdicionaRenda();
        }
        return instance;
    }

    public void setCadastro(Cadastro cadastro) {
        this.cadastro = cadastro;
    }

    public void adicBtnClicked() {
        if(!campoVazio(descrText, "descrição") && !campoVazio(
                valorText, "valor")) {
            try {
                double valor = Double.parseDouble(valorText.getText());
                cadastro.getRendas().inserir(new Renda(valor, descrText
                        .getText()));
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

    private boolean campoVazio(TextField text, String nome) {
        if(text.getText().trim().isEmpty()) {
            CadastrosViewController.getInstance().dialogoErro("Erro",
                    "Você precisa preencher o campo " + nome + " para " +
                            "adionar a nova renda.");
            text.requestFocus();
            return true;
        }
        return false;
    }

    public void cancelBtnClicked() {
        CadastrosViewController.getInstance().fechaAdiciona();
    }



}
