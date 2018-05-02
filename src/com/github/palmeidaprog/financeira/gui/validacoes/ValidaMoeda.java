package com.github.palmeidaprog.financeira.gui.validacoes;

import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Locale;

public class ValidaMoeda<T extends Control & ControlValidationable> {
    private final T tipo;

    // TODO: (TJ) View ou outra coisa / Code Smell?
    public ValidaMoeda(T tipo) {
        this.tipo = tipo;
        eventos();
    }

    private void eventos() {

    }

    private boolean muitosPontos() {
        String valor = tipo.getText();
        int count = 0;

        for(int i = valor.length(); i >= 0; i--) {
            if(valor.charAt(i) == '.') {
                if(++count >= 2) {
                    return true;
                }
            }
        }
        return true;
    }

    private String formataValor(String valor) throws NumberFormatException {
        double value = Double.parseDouble(valor);
        return String.format(Locale.getDefault(), "%.2f", value);
    }


}
