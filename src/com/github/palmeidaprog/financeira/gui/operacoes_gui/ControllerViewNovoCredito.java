package com.github.palmeidaprog.financeira.gui.operacoes_gui;

import com.github.palmeidaprog.financeira.clientes.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;

public class ControllerViewNovoCredito {
    private Cliente cliente;
    private static volatile ControllerViewNovoCredito instance;
    @FXML private RadioButton outraOperacaoRadio, financiamentoRadio,
        emprestimoPessoalRadio;
    @FXML private Button continuarBtn;


    private ControllerViewNovoCredito() { }

    public synchronized static ControllerViewNovoCredito getInstance() {
        if(instance == null) {
            instance = new ControllerViewNovoCredito();
        }
        return instance;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public void  continuarBtnClicked() {
        OperacoesViewController operacoesViewController =
                OperacoesViewController.getInstance();

        operacoesViewController.showFinanciamento();
        if(outraOperacaoRadio.isSelected()) {
            operacoesViewController.showOutraOperacao();
        } else if (emprestimoPessoalRadio.isSelected()){
            operacoesViewController.showEmprestimo();
        }
    }




}
