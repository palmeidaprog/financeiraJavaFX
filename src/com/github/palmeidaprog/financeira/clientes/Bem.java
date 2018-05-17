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

import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.util.Locale;

public class Bem implements Serializable, ValorDescrito {
    private double valor;
    private StringProperty valorP;
    private StringProperty descricaoP;
    private String descricao;
    transient private String valLiqFormatado;
    private final PendenciaController pendencias;

    // construtor de deserializacao
    public Bem(double valor, String descricao, PendenciaController pendencias) {
        this.valor = valor;
        valorP.set(valorFormatado(valorLiquido()));
        descricaoP.set(descricao);
        this.descricao = descricao;

        this.pendencias = pendencias;
        this.valLiqFormatado = valorFormatado(valorLiquido());
    }

    public Bem(double valor) {
        this.valor = valor;
        pendencias = new PendenciaController();
    }

    @Override
    public StringProperty descricaoPProperty() {
        return descricaoP;
    }

    @Override
    public StringProperty valorPProperty() {
        return valorP;
    }

    public PendenciaController getPendencias() {
        return pendencias;
    }

    // valor liquido
    @Override
    public double getValor() {
        return valor;
    }

    @Override
    public void setValor(double valor) {
        this.valor = valor;
        this.valLiqFormatado = valorFormatado(valorLiquido());
        valorP.set(valLiqFormatado);
    }

    public String valorFormatado(double valor) {
        return String.format(Locale.getDefault(), "%.2f", valor);
    }

    public double valorLiquido() {
        return Math.max(0, getValor() - pendencias.total());
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public void setDescricao(String descricao) {
        this.descricao = descricao;
        descricaoP.set(descricao);
    }

    @Override
    public String toString() {
        return "Bem{" +
                "valor=" + valor +
                ", descricao='" + descricao + '\'' +
                ", valLiqFormatado='" + valLiqFormatado + '\'' +
                ", pendencias=" + pendencias +
                '}';
    }
}
