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
import java.util.Locale;

public class Renda implements Serializable {
    private double valor;
    private String descricao;
    transient private String valFormatado;

    public Renda(double valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
        valFormatado = valorFormatado();
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
        valFormatado = valorFormatado();
    }

    private String valorFormatado() {
        return String.format(Locale.getDefault(), "%.2f", valor);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String formatado() {
        return valorFormatado() + " - " + descricao + ".";
    }

    @Override
    public String toString() {
        return "Renda{" +
                "valor=" + valor +
                ", descricao='" + descricao + '\'' +
                ", valFormatado='" + valFormatado + '\'' +
                '}';
    }
}
