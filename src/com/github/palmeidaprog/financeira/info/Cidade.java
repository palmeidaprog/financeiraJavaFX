package com.github.palmeidaprog.financeira.info;

/*
 * UNICAP - Univesidade Catolica de Pernambuco
 * @author Aluno: Paulo R. Almeida Filho
 * http://www.github.com/palmeidaprog/financeira
 * @email pauloalmeidaf@gmail.com
 * @email palmeidaprogramming@gmail.com
 * Professor: Antonio Canvalcanti
 */


import com.github.palmeidaprog.financeira.interfaces.observador.Observado;
import com.github.palmeidaprog.financeira.interfaces.observador.TipoEvento;

import java.io.Serializable;

public class Cidade extends Observado implements Serializable {
    private String nome;

    public Cidade(String nome) {
        this.nome = nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
        notificarEvento(this.nome, TipoEvento.EDITADO);
    }

    public String getNome() {
        return nome;
    }

    public String formatado() {
        return nome;
    }

    //--Object override-------------------------------------------------------

    @Override
    public String toString() {
        return "Cidade{" +
                "nome='" + nome + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(!(o instanceof Cidade)) {
            return false;
        } else {
            Cidade cidade = (Cidade) o;
            return nome.equals(cidade.nome);
        }
    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }
}

