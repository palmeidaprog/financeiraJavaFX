package com.github.palmeidaprog.financeira.gui.operacoes_gui;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;

public class ControllerViewNovoCredito {
    private static volatile ControllerViewNovoCredito instance;
    @FXML private RadioButton outraOperacaoRadio, financiamentoRadio,
        emprestimoPessoalRadio;

    private ControllerViewNovoCredito() { }

    public synchronized static ControllerViewNovoCredito getInstance() {
        if(instance == null) {
            instance = new ControllerViewNovoCredito();
        }
        return instance;
    }

    private void resetRadioButtons() {
        outraOperacaoRadio.setSelected(false);
        financiamentoRadio.setSelected(false);
        emprestimoPessoalRadio.setSelected(false);
    }

    private void selectRadio(RadioButton r) {
        resetRadioButtons();
        r.setSelected(true);
    }

    public void emprestimoPessoalRadioSelected() {
        selectRadio(emprestimoPessoalRadio);
    }

    public void financiamentoRadioSelected() {
        selectRadio(financiamentoRadio);
    }

    public void outraOperacaoRadioSelected() {
        selectRadio(outraOperacaoRadio);
    }


}
