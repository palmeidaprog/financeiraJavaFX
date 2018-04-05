package com.github.palmeidaprog.financeira.info;

/*
 * Financeira App
 * UNICAP - Univesidade Catolica de Pernambuco
 * @author Aluno: Paulo R. Almeida Filho
 * http://www.github.com/palmeidaprog/financeira
 * @email pauloalmeidaf@gmail.com
 * @email palmeidaprogramming@gmail.com
 * Professor: Antonio Canvalcanti
 */

import java.io.Serializable;

public enum TipoEndereco implements Serializable {
    RESIDENCIAL("Residencial"),
    COMERCIAL("Comercial"),
    OUTRO("Outro");

    private final String tipoEndereco;

    TipoEndereco(String tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }

    public String formatado() {
        return tipoEndereco;
    }

    @Override
    public String toString() {
        return "TipoEndereco{" +
                "tipoEndereco='" + tipoEndereco + '\'' +
                '}';
    }
}
