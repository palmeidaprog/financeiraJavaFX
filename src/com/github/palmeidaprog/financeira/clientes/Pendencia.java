package com.github.palmeidaprog.financeira.clientes;

/*
 * UNICAP - Univesidade Catolica de Pernambuco
 * @author Aluno: Paulo R. Almeida Filho
 * http://www.github.com/palmeidaprog/financeira
 * @email pauloalmeidaf@gmail.com
 * @email palmeidaprogramming@gmail.com
 * Professor: Antonio Canvalcanti
 */

import javafx.beans.InvalidationListener;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.io.Serializable;
import java.util.Locale;

public class Pendencia implements Serializable, ValorDescrito {
    private double valor;
    private StringProperty valorP;
    private StringProperty descricaoP;
    private String descricao;

    // deserialização
    public Pendencia(double valor, String descricao) {
        this.valor = valor;
        valorP.set(valorFormatado(valor));
        this.descricao = descricao;
        descricaoP.set(descricao);
    }

    @Override
    public double getValor() {
        return valor;
    }

    @Override
    public void setValor(double valor) {
        this.valor = valor;
        valorP.set(valorFormatado(valor));
    }

    @Override
    public StringProperty valorPProperty() {
        return valorP;
    }

    @Override
    public StringProperty descricaoPProperty() {
        return descricaoP;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public void setDescricao(String descricao) {
        this.descricao = descricao;
        descricaoP.set(descricao);
    }

    @Override
    public String valorFormatado(double valor) {
        return String.format(Locale.getDefault(), "%.2f", valor);
    }

    private String formatado() {
        return valorFormatado(valor) + " " + descricao;
    }

    @Override
    public String toString() {
        return "Pendencia{" +
                "valor=" + valor +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
