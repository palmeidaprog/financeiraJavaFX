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

import com.github.palmeidaprog.financeira.interfaces.observador.Observado;

import java.io.Serializable;

public class Bairro extends Observado implements Serializable {
    private String nome;

    public Bairro(String nome) {
        this.nome = nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
        notifyChange(this);
    }

    public String getNome() {
        return nome;
    }

    public String formatado() {
        return nome;
    }

    //--Objects override------------------------------------------------------

    @Override
    public String toString() {
        return "Bairro{" +
                "nome='" + nome + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(!(o instanceof Bairro)) {
            return false;
        } else {
            Bairro bairro = (Bairro) o;
            return nome.equals(bairro.nome);
        }
    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }
}
