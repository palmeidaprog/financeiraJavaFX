package com.github.palmeidaprog.financeira.clientes;

/*
 * UNICAP - Univesidade Catolica de Pernambuco
 * @author Aluno: Paulo R. Almeida Filho
 * http://www.github.com/palmeidaprog/financeira
 * @email pauloalmeidaf@gmail.com
 * @email palmeidaprogramming@gmail.com
 * Professor: Antonio Canvalcanti
 */

import com.github.palmeidaprog.financeira.interfaces.observador.Observado;
import com.github.palmeidaprog.financeira.interfaces.observador.Observador;

import java.io.Serializable;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Cadastro extends Observado implements Serializable,
        Observador {
    private Credito credito = new Credito();
    private RendaController rendas = new RendaController();
    private BemController bens = new BemController();

    //construtor de deserialzacao
    public Cadastro(Credito credito, RendaController rendas, BemController
            bens) {
        observers();
        this.credito = credito;
        this.rendas = rendas;
        this.bens = bens;
    }

    public Cadastro() {
        observers();
    }

    public Cadastro(Renda renda) {
        this();
        rendas.inserir(renda);
    }

    public Cadastro(Bem bem) {
        this();
        bens.inserir(bem);
    }

    public Cadastro(List<Bem> bens) {
        this();
        this.bens.inserir(bens);
    }

    public Cadastro(Bem bem, Renda renda) {
        this(bem);
        rendas.inserir(renda);
    }

    public Cadastro(List<Bem> bens, Renda renda) {
        this(bens);
        rendas.inserir(renda);
    }

    private void observers() {
        this.credito.adicionaObservador(this);
        this.rendas.adicionaObservador(this);
        this.bens.adicionaObservador(this);
    }

    public BemController getBens() {
        return bens;
    }

    public Credito getCredito() {
        return credito;
    }

    public RendaController getRendas() {
        return rendas;
    }

    //--Observer implementação------------------------------------------------

    @Override
    public void atualizar(EventoObservado ev) {
        notifyChange(arg);
    }

    //--Object override-------------------------------------------------------

    @Override
    public String toString() {
        return "Cadastro{" +
                "credito=" + credito +
                ", rendas=" + rendas +
                ", bens=" + bens +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if(!(o instanceof Cadastro)) {
            return false;
        } else {
            Cadastro cadastro = (Cadastro) o;
            return credito.equals(cadastro.credito) &&
                    rendas.equals(cadastro.rendas) &&
                    bens.equals(cadastro.bens);
        }
    }

    @Override
    public int hashCode() {
        return credito.hashCode() + rendas.hashCode() + bens.hashCode();
    }
}
