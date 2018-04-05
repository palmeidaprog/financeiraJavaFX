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

public class Pendencia implements Serializable {
    private double valor;
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
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String formatado() {
        return String.format(Locale.getDefault(), "%.2f", valor) + " "
                + descricao;
    }

    @Override
    public String toString() {
        return "Pendencia{" +
                "valor=" + valor +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
