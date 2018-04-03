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

public class Telefone {
    private CodigoArea codigoArea;
    private NumeroTelefone numero;
    private TipoTelefone tipo;
    private Pais pais;

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
    }

    public CodigoArea getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(CodigoArea codigoArea) {
        this.codigoArea = codigoArea;
    }

    public NumeroTelefone getNumero() {
        return numero;
    }

    public void setNumero(NumeroTelefone numero) {
        this.numero = numero;
    }

    public void setTipo(TipoTelefone tipo) {
        this.tipo = tipo;
    }

    public TipoTelefone getTipo() {
        return tipo;
    }


    @Override
    public String toString() {
        return "+" + pais.getCodigo() + "-" + codigoArea + "-" + numero;
    }
}
