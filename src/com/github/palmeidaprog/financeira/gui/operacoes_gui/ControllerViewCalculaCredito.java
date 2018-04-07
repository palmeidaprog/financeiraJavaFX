package com.github.palmeidaprog.financeira.gui.operacoes_gui;

import com.github.palmeidaprog.financeira.gui.ViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class ControllerViewCalculaCredito implements Initializable {
    private static volatile ControllerViewCalculaCredito instance;
    private String tipo;

    @FXML private Label descrLabel, parcelaValorLabel, bemLabel;
    @FXML private TextField valorOpText, dataTextom, jurosText;
    @FXML private ComboBox<String> numParcelasCombo, bemCombo;
    @FXML private VBox tipoVBox;
    @FXML private Button novoBemBtn, aceitarBtn;
    @FXML private RadioButton imovelRadio, automovelRadio;

    private ControllerViewCalculaCredito() { }

    public synchronized static ControllerViewCalculaCredito getInstance() {
        if(instance == null) {
            instance = new ControllerViewCalculaCredito();
        }
        return instance;
    }

    public void initialize(URL u, ResourceBundle rb) {
        descrLabel.setText(tipo);
        ObservableList<String> parc = FXCollections.observableArrayList(
                "1x", "2x", "3x", "4x", "5x", "6x", "8x", "10x", "12x"
                , "18x", "24x", "30x", "36x", "48x", "60x");
        numParcelasCombo.setItems(parc);
        numParcelasCombo.setValue("12x");


    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setTipoVBox(VBox tipoVBox) {
        this.tipoVBox.getChildren().clear();
        this.tipoVBox.getChildren().add(tipoVBox);
    }

    //--Events----------------------------------------------------------------

    public void valorOpLabelAction() {
        try {
            valorOpText.setText(formataValor(valorOpText.getText()));
        } catch (NumberFormatException e) {
            ViewController.getInstance().dialogoErro("Erro",
                    valorOpText.getText() + " não é um valor válido!");
        }
    }

    private void resetaRadio() {
        imovelRadio.setSelected(false);
        automovelRadio.setSelected(false);
    }

    public void imovelRadioSelected() {
        resetaRadio();
        imovelRadio.setSelected(true);
    }

    public void automovelRadioSelected() {
        resetaRadio();
        automovelRadio.setSelected(true);
    }

    public void valorOpTextKeyTyped() {

    }

    private String formataValor(String valor) throws NumberFormatException {
        double value = Double.parseDouble(valor);
        return String.format(Locale.getDefault(), "%.2f", value);
    }
}
