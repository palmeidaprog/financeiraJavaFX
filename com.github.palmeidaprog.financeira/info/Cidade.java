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

public class Cidade {
    private String nome;

    public Cidade(String nome) throws DadoVazioException {
        if(nome.trim().isEmpty()) {
            throw new DadoVazioException("Atributo nome vazio");
        }
        this.nome = nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}

