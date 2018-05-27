package com.github.palmeidaprog.financeira.info;

/*
 * UNICAP - Univesidade Catolica de Pernambuco
 * @author Aluno: Paulo R. Almeida Filho
 * http://www.github.com/palmeidaprog/financeira
 * @email pauloalmeidaf@gmail.com
 * @email palmeidaprogramming@gmail.com
 * Professor: Antonio Canvalcanti
 */

import com.github.palmeidaprog.financeira.interfaces.ObservableSerializable;

import java.io.Serializable;
import java.util.Objects;

public class Cep extends ObservableSerializable implements Serializable {
    private String numero;

    // deserializaco
    public Cep(String numero) {
        this.numero = numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
        notifyChange(this);
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
