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

public class CodigoArea implements Serializable {
    private final String codigo;

    // deserializacao
    public CodigoArea(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String formatado() {
        return codigo;
    }

    @Override
    public String toString() {
        return "CodigoArea{" +
                "codigo='" + codigo + '\'' +
                '}';
    }
}
