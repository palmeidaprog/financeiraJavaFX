package com.github.palmeidaprog.financeira.gui.validacoes;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.util.Locale;

public class ValidaMoeda {
    private final TextField textField;

    // TODO: (TJ) View ou outra coisa / Code Smell?
    public ValidaMoeda(TextField textField) {
        this.textField = textField;
        eventos();
    }

    private void eventos() {


        textField.setOnKeyTyped(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                String str = textField.getText();

                try {
                    str = formataValor(str);
                    updateField(str);
                } catch(NumberFormatException e) {
                    e.printStackTrace();
                }

                if(muitosPontos() == 2) {

                    str.replaceFirst(".",  "");
                }


            }
        });
    }

    private int muitosPontos() {
        String valor = textField.getText();
        int count = 0;

        for(int i = valor.length(); i >= 0; i--) {
            if(valor.charAt(i) == '.') {
                if(++count >= 2) {
                    return count;
                }
            }
        }
        return count;
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
