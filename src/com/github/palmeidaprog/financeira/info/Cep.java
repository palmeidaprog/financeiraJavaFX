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

public class Cep extends Observado implements Serializable {
    private String numero;

    // deserializaco
    public Cep(String numero) {
        this.numero = numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
        notificarEvento(this.numero, TipoEvento.EDITADO);
    }

    public String getNumero() {
        return numero;
    }

    public String formatado() {
        return numero;
    }

    //--Objects override------------------------------------------------------

    @Override
    public String toString() {
        return "Cep{" +
                "numero='" + numero + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(!(o instanceof Cep)) {
            return false;
        } else {
            Cep cep = (Cep) o;
            return numero.equals(cep.numero);
        }
    }

    @Override
    public int hashCode() {
        return numero.hashCode();
    }
}
