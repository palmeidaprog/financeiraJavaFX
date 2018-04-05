package com.github.palmeidaprog.financeira.clientes;

/*
 * UNICAP - Univesidade Catolica de Pernambuco
 * @author Aluno: Paulo R. Almeida Filho
 * http://www.github.com/palmeidaprog/financeira
 * @email pauloalmeidaf@gmail.com
 * @email palmeidaprogramming@gmail.com
 * Professor: Antonio Canvalcanti
 */

import java.io.Serializable;
import java.util.List;

public class Cadastro implements Serializable {
    private Credito credito = new Credito();;
    private RendaController rendas = new RendaController();;
    private BemController bens = new BemController();

    //construtor de deserialzacao
    public Cadastro(Credito credito, RendaController rendas, BemController
            bens) {
        this.credito = credito;
        this.rendas = rendas;
        this.bens = bens;
    }

    public Cadastro() { }

    public Cadastro(Renda renda) {
        rendas.inserir(renda);
    }

    public Cadastro(Bem bem) {
        bens.inserir(bem);
    }

    public Cadastro(List<Bem> bens) {
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

    public BemController getBens() {
        return bens;
    }

    public Credito getCredito() {
        return credito;
    }

    public RendaController getRendas() {
        return rendas;
    }

    @Override
    public String toString() {
        return "Cadastro{" +
                "credito=" + credito +
                ", rendas=" + rendas +
                ", bens=" + bens +
                '}';
    }
}
