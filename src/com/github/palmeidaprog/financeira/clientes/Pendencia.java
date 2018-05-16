package com.github.palmeidaprog.financeira.clientes;

/*
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

public class Pendencia implements Serializable {
    private double valor;
    private StringProperty valorP;
    private StringProperty descricaoP;
    private String descricao;

    // deserialização
    public Pendencia(double valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
        valorP.set(valorFormatado(valor));
    }

    public StringProperty valorPProperty() {
        return valorP;
    }

    public StringProperty descricaoPProperty() {
        return descricaoP;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
        descricaoP.set(descricao);
    }

    private String valorFormatado(double valor) {
        return String.format(Locale.getDefault(), "%.2f", valor);
    }

    public String formatado() {
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
