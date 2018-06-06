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

import com.github.palmeidaprog.financeira.interfaces.observador.EventoObs;
import com.github.palmeidaprog.financeira.interfaces.observador.Observado;
import com.github.palmeidaprog.financeira.interfaces.observador.Observador;
import com.github.palmeidaprog.financeira.interfaces.observador.TipoEvento;

import java.io.Serializable;



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
        this.estado.adicionaObservador(this);
        this.cidade = cidade;
        this.cidade.adicionaObservador(this);
        this.bairro = bairro;
        this.bairro.adicionaObservador(this);
        this.cep = cep;
        this.cep.adicionaObservador(this);
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
        this.estado.adicionaObservador(this);
        this.cidade = cidade;
        this.cidade.adicionaObservador(this);
        this.bairro = bairro;
        this.bairro.adicionaObservador(this);
        this.cep = cep;
        this.cep.adicionaObservador(this);
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
        notificarEvento(this.rua, TipoEvento.EDITADO);
    }

    public String getNumero() {
        return numero;

    }

    public void setNumero(String numero) {
        this.numero = numero;
        notificarEvento(this.numero, TipoEvento.EDITADO);
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
        notificarEvento(this.pais, TipoEvento.EDITADO);
    }

    public Estado getEstado() {
        return estado;

    }

    public void setEstado(Estado estado) {
        this.estado = estado;
        this.estado.adicionaObservador(this);
        notificarEvento(this.estado, TipoEvento.EDITADO);
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
        this.cidade.adicionaObservador(this);
        notificarEvento(this.cidade, TipoEvento.EDITADO);
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
        this.bairro.adicionaObservador(this);
        notificarEvento(this.bairro, TipoEvento.EDITADO);
    }

    public Cep getCep() {
        return cep;
    }

    public void setCep(Cep cep) {
        this.cep = cep;
        this.cep.adicionaObservador(this);
        notificarEvento(this.cep, TipoEvento.EDITADO);
    }

    public TipoEndereco getTipo() {
        return tipo;
    }

    public void setTipo(TipoEndereco tipo) {
        this.tipo = tipo;
        notificarEvento(this.tipo, TipoEvento.EDITADO);
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
        notificarEvento(this.complemento, TipoEvento.EDITADO);
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
        notificarEvento(this.referencia, TipoEvento.EDITADO);
    }

    //--Observador method-------------------------------------------------------

    @Override
    public void atualizar(EventoObs ev) {
        notificarEvento(ev);
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
