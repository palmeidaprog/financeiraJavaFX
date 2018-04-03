package com.github.palmeidaprog.financeira.info;

/*
 * UNICAP - Univesidade Catolica de Pernambuco
 * @author Aluno: Paulo R. Almeida Filho
 * http://www.github.com/palmeidaprog/financeira
 * @email pauloalmeidaf@gmail.com
 * @email palmeidaprogramming@gmail.com
 * Professor: Antonio Canvalcanti
 */

import com.github.palmeidaprog.financeira.exception.DadoVazioException;

public class Cep {
    private String numero;

    public Cep(String numero) throws DadoVazioException {
        if(numero.trim().isEmpty()) {
            throw new DadoVazioException("Atributo numero vazio");
        }
        this.numero = numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        return numero;
    }
}
