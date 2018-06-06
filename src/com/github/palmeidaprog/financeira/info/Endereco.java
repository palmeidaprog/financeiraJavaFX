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
import java.util.Observable;
import java.util.Observer;

public class Endereco extends Observado implements Serializable,
        Observador {
    private String rua;
    private String numero;
    private Pais pais;
    private Estado estado;
    private Cidade cidade;
    private Bairro bairro;
    private Cep cep;
    private TipoEndereco tipo;
    //opcional
    private String complemento;
    private String referencia;
    // construtor basico
    public Endereco(String rua, String numero, Pais pais, Estado estado,
                    Cidade cidade, Bairro bairro, Cep cep, TipoEndereco
                    tipo) {
        this.rua = rua;
        this.numero = numero;
        this.pais = pais;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.cep = cep;
        this.tipo = tipo;
    }

    // deserializacao
    public Endereco(String rua, String numero, Pais pais, Estado estado,
                    Cidade cidade, Bairro bairro, Cep cep, TipoEndereco tipo,
                    String complemento, String referencia) {
        this.rua = rua;
        this.numero = numero;
        this.pais = pais;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.cep = cep;
        this.tipo = tipo;
        this.complemento = complemento;
        this.referencia = referencia;
    }

    // const c/ complemento
    public Endereco(String rua, String numero, Pais pais, Estado estado,
                    Cidade cidade, Bairro bairro, Cep cep, TipoEndereco tipo,
                    String complemento) {
        this(rua, numero, pais, estado, cidade, bairro, cep, tipo);
        this.complemento = complemento;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
        notifyChange(this.rua);
    }

    public String getNumero() {
        return numero;

    }

    public void setNumero(String numero) {
        this.numero = numero;
        notifyChange(this.numero);
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
        notifyChange(this.pais);
    }

    public Estado getEstado() {
        return estado;

    }

    public void setEstado(Estado estado) {
        this.estado = estado;
        notifyChange(this.estado);
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
        notifyChange(this.cidade);
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
        notifyChange(this.bairro);
    }

    public Cep getCep() {
        return cep;
    }

    public void setCep(Cep cep) {
        this.cep = cep;
        notifyChange(this.cep);
    }

    public TipoEndereco getTipo() {
        return tipo;
    }

    public void setTipo(TipoEndereco tipo) {
        this.tipo = tipo;
        notifyChange(this.tipo);
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
        notifyChange(this.complemento);
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
        notifyChange(this.referencia);
    }

    //--Observador method-------------------------------------------------------

    @Override
    public void atualizar(EventoObservado ev) {
        notifyChange(o);
    }

    //--Object override-------------------------------------------------------

    @Override
    public String toString() {
        return "Endereco{" +
                "rua='" + rua + '\'' +
                ", numero='" + numero + '\'' +
                ", pais=" + pais +
                ", estado=" + estado +
                ", cidade=" + cidade +
                ", bairro=" + bairro +
                ", cep=" + cep +
                ", tipo=" + tipo +
                ", complemento='" + complemento + '\'' +
                ", referencia='" + referencia + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(!(o instanceof Endereco)) {
            return false;
        } else {
            Endereco endereco = (Endereco) o;
            return rua.equals(endereco.rua) &&
                    numero.equals(endereco.numero) &&
                    pais == endereco.pais &&
                    estado.equals(endereco.estado) &&
                    cidade.equals(endereco.cidade) &&
                    bairro.equals(endereco.bairro) &&
                    cep.equals(endereco.cep) &&
                    tipo == endereco.tipo &&
                    complemento.equals(endereco.complemento) &&
                    referencia.equals(endereco.referencia);
        }
    }

    @Override
    public int hashCode() {
        return rua.hashCode() + numero.hashCode() + pais.hashCode()
                + estado.hashCode() + cidade.hashCode() + bairro.hashCode() +
                cep.hashCode() + tipo.hashCode() + complemento.hashCode() +
                referencia.hashCode();
    }
}
