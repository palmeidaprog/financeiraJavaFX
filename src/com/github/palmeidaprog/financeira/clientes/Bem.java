package com.github.palmeidaprog.financeira.clientes;

/*
 * Financeira App
 * UNICAP - Univesidade Catolica de Pernambuco
 * @author Aluno: Paulo R. Almeida Filho
 * http://www.github.com/palmeidaprog/financeira
 * @email pauloalmeidaf@gmail.com
 * @email palmeidaprogramming@gmail.com
 * Professor: Antonio Canvalcanti
 */

public class Bem {
    private double valor;
    private String descricao;
    private final PendenciaController pendencias;

    public Bem(double valor) {
        this.valor = valor;
        pendencias = new PendenciaController();
    }

    public PendenciaController getPendencias() {
        return pendencias;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double valorLiquido() {
        return Math.max(0, getValor() - pendencias.total());
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
