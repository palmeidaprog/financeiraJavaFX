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
import com.github.palmeidaprog.financeira.interfaces.observador.TipoEvento;

import java.io.Serializable;

public class Estado extends Observado implements Serializable {
    private String nome;
    private String sigla;

    public Estado(String sigla) {
        this.sigla = sigla;
    }

    public Estado(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla;
    }

    public String getSigla() {
        return sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
        notificarEvento(this.sigla, TipoEvento.EDITADO);
    }

    public void setNome(String nome) {
        this.nome = nome;
        notificarEvento(this.nome, TipoEvento.EDITADO);
    }

    public boolean equals(Estado estado) {
        return (estado != null && (this.sigla.equals(estado.getSigla()) ||
                this.nome.equals(estado.getNome())));
    }

    public String formatado() {
        return (nome == null) ? getSigla() : getNome() + " (" + getSigla()
                + ")";
     }

     //--Object override------------------------------------------------------

    @Override
    public String toString() {
        return "Estado{" +
                "nome='" + nome + '\'' +
                ", sigla='" + sigla + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(!(o instanceof Estado)) {
            return false;
        } else {
            Estado estado = (Estado) o;
            return nome.equals(estado.nome) &&
                    sigla.equals(estado.sigla);
        }
    }

    @Override
    public int hashCode() {
        return nome.hashCode() + sigla.hashCode();
    }
}
