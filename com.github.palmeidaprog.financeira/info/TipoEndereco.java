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

public enum TipoEndereco {
    RESIDENCIAL("Residencial"),
    COMERCIAL("Comercial"),
    OUTRO("Outro");

    private final String tipoEndereco;

    TipoEndereco(String tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }

    @Override
    public String toString() {
        return tipoEndereco;
    }
}
