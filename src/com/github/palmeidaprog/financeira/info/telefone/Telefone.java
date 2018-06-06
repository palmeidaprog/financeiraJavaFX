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

import com.github.palmeidaprog.financeira.info.Pais;
import com.github.palmeidaprog.financeira.interfaces.observador.Observado;
import com.github.palmeidaprog.financeira.interfaces.observador.TipoEvento;

import java.io.Serializable;

public class Telefone extends Observado implements Serializable {
    private CodigoArea codigoArea;
    private NumeroTelefone numero;
    private TipoTelefone tipo;
    private Pais pais;

    // deserializacao
    public Telefone(CodigoArea codigoArea, NumeroTelefone numero, Pais pais,
                    TipoTelefone tipo) {
        this.codigoArea = codigoArea;
        this.numero = numero;
        this.pais = pais;
        this.tipo = tipo;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
        notificarEvento(this.pais, TipoEvento.EDITADO);
    }

    public CodigoArea getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(CodigoArea codigoArea) {
        this.codigoArea = codigoArea;
        notificarEvento(this.codigoArea, TipoEvento.EDITADO);
    }

    public NumeroTelefone getNumero() {
        return numero;
    }

    public void setNumero(NumeroTelefone numero) {
        this.numero = numero;
        notificarEvento(this.numero, TipoEvento.EDITADO);
    }

    public void setTipo(TipoTelefone tipo) {
        this.tipo = tipo;
        notificarEvento(this.tipo, TipoEvento.EDITADO);
    }

    public TipoTelefone getTipo() {
        return tipo;
    }

    public String formatado() {
        return "+" + pais.getCodigo() + "-" + codigoArea + "-" + numero;
    }

    //--Object override-------------------------------------------------------

    @Override
    public String toString() {
        return "Telefone{" +
                "codigoArea=" + codigoArea +
                ", numero=" + numero +
                ", tipo=" + tipo +
                ", pais=" + pais +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(!(o instanceof Telefone)) {
            return false;
        } else {
            Telefone telefone = (Telefone) o;
            return codigoArea.equals(telefone.codigoArea) &&
                    numero.equals(telefone.numero) &&
                    tipo == telefone.tipo &&
                    pais == telefone.pais;
        }
    }

    @Override
    public int hashCode() {
        return codigoArea.hashCode() + numero.hashCode() + tipo.hashCode() +
                pais.hashCode();
    }
}
