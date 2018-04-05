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

import com.github.palmeidaprog.financeira.exception.DadoVazioException;

import java.io.Serializable;

public class Bairro implements Serializable {
    private String nome;

    public Bairro(String nome) {
        this.nome = nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public String formatado() {
        return nome;
    }

    @Override
    public String toString() {
        return "Bairro{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
