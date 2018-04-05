package com.github.palmeidaprog.financeira.info;

/*
 * UNICAP - Univesidade Catolica de Pernambuco
 * @author Aluno: Paulo R. Almeida Filho
 * http://www.github.com/palmeidaprog/financeira
 * @email pauloalmeidaf@gmail.com
 * @email palmeidaprogramming@gmail.com
 * Professor: Antonio Canvalcanti
 */

import java.io.Serializable;

public class Cep implements Serializable {
    private String numero;

    // deserializaco
    public Cep(String numero) {
        this.numero = numero;
    }

    public void setNumero(String numero) {
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
        return "Cep{" +
                "numero='" + numero + '\'' +
                '}';
    }
}
