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

public class NumeroTelefone implements Serializable {
    private final String numero;

    // deserializacap
    public NumeroTelefone(String numero) {
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    public String formatado() {
        return numero;
    }

    @Override
    public String toString() {
        return "NumeroTelefone{" +
                "numero='" + numero + '\'' +
                '}';
    }
}
