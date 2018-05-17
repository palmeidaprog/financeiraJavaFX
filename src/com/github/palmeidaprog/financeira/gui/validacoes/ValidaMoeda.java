package com.github.palmeidaprog.financeira.gui.validacoes;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.Locale;

public class ValidaMoeda {
    private final TextField textField;

    public ValidaMoeda(TextField textField) {
        this.textField = textField;
        eventos();
    }

    private void eventos() {
        textField.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if(!event.getCode().isDigitKey() && event.getCode()
                        != KeyCode.DEAD_ABOVEDOT) {
                    int pos = textField.getCaretPosition();
                    textField.setText(textField.getText().replace(event
                            .getCode().getName()
                            ,  ""));
                    textField.setText(textField.getText().replace(event
                                    .getCode().getName().toLowerCase()
                            ,  ""));
                    textField.positionCaret(pos);
                }
            }
        });
    }

    //
    private void updateField(String text) {
        textField.setText(text);
        textField.positionCaret(textField.getLength());
    }

    private String formataValor(String valor) throws NumberFormatException {
        double value = Double.parseDouble(valor);
        return String.format(Locale.getDefault(), "%.2f", value);
    }




}
