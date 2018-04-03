package com.github.palmeidaprog.financeira.clientes;

/*
 * UNICAP - Univesidade Catolica de Pernambuco
 * @author Aluno: Paulo R. Almeida Filho
 * http://www.github.com/palmeidaprog/financeira
 * @email pauloalmeidaf@gmail.com
 * @email palmeidaprogramming@gmail.com
 * Professor: Antonio Canvalcanti
 */

import java.util.List;

public class Cadastro {
    private final Credito credito = new Credito();;
    private final RendaController rendas = new RendaController();;
    private final BemController bens = new BemController();

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

}
