package com.github.palmeidaprog.financeira.info.telefone;

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

public enum TipoTelefone implements Serializable {
    RESIDENCIAL("Residencial"),
    COMERCIAL("Comercial"),
    CELULAR("Celular"),
    OUTRO("Outro");

    private String tipo;

    TipoTelefone(String tipo) {
        this.tipo = tipo;
    }

    public String formatado() {
        return tipo;
    }

    @Override
    public String toString() {
        return "TipoTelefone{" +
                "tipo='" + tipo + '\'' +
                '}';
    }
}
