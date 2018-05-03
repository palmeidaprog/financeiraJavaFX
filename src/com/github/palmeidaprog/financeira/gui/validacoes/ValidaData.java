package com.github.palmeidaprog.financeira.gui.validacoes;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ValidaData {
    private final TextField textField;

    // TODO: (TJ) View ou outra coisa / Code Smell?
    public ValidaData(TextField textField) {
        this.textField = textField;
        eventoDataEntrada();
    }

    // formata data como deve ser mostrada nos textField que contem datas
    private String dataEntrada(String data) {
        int length = data.length();

        if(length == 2 || length == 5) {
            if(data.charAt(length -1) == '/') {
                data = data.substring(0, length - 1);
            } else {
                data += "/";
            }
        } else if (length == 4 && data.charAt(length -1) == '/') {
            data = data.substring(0, length - 1);
        } else if(length <= 2) {
            data = data.replace("/", "");
        }
        return data;
    }
    

    private void eventoDataEntrada() {
        // tecla pressionada
        textField.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent ke) {
                String string = textField.getText();

                if(!string.isEmpty() && string.charAt(string.length() - 1)
                        == '/' && ke.getCode() == KeyCode.BACK_SPACE) {
                    updateField(string.substring(0, string.length() - 1),
                            false);
                }
            }
        });

        // tecla digitada
        textField.setOnKeyTyped(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent ke) {
                String string = textField.getText();

                if(string.length() >= 10) {
                    string = string.substring(0, string.length() - 1);
                }
                updateField(string, false);
                updateField(dataEntrada(string), true);
            }
        });
    }

    private void updateField(String text, boolean temBarra) {
        int pos = textField.getCaretPosition();

        if(temBarra) {
            ++pos;
        }
        textField.setText(text);
        textField.positionCaret(pos);
    }
}
