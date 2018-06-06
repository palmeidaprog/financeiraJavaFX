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
import com.github.palmeidaprog.financeira.interfaces.ValorDescrito;
import com.github.palmeidaprog.financeira.interfaces.observador.TipoEvento;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.io.Serializable;
import java.util.Locale;


public class Renda extends Observado implements Serializable,
        ValorDescrito {
    private double valor;
    private String descricao;
    private StringProperty descricaoP = new SimpleStringProperty();
    private StringProperty valorP = new SimpleStringProperty();
    transient private String valFormatado;

    public Renda(double valor, String descricao) {
        this.valor = valor;
        valorP.setValue(valorFormatado(valor));
        this.descricao = descricao;
        descricaoP.setValue(descricao);
        valFormatado = valorFormatado();
    }

    @Override
    public double getValor() {
        return valor;
    }

    @Override
    public StringProperty valorPProperty() {
        return valorP;
    }

    @Override
    public StringProperty descricaoPProperty() {
        return descricaoP;
    }

    public void setValor(double valor) {
        this.valor = valor;
        valFormatado = valorFormatado();
        valorP.setValue(valorFormatado(valor));
        notificarEvento(this.valor, TipoEvento.EDITADO);
    }

    private String valorFormatado() {
        return valorFormatado(valor);
    }

    public String valorFormatado(double valor) {
        return String.format(Locale.getDefault(), "%.2f", valor);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
        descricaoP.setValue(descricao);
        notificarEvento(this.descricao, TipoEvento.EDITADO);
    }

    public String formatado() {
        return valorFormatado() + " - " + descricao + ".";
    }

    //--Object override-------------------------------------------------------

    @Override
    public String toString() {
        return "Renda{" +
                "valor=" + valor +
                ", descricao='" + descricao + '\'' +
                ", valFormatado='" + valFormatado + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(!(o instanceof Renda)) {
            return false;
        } else {
            Renda renda = (Renda) o;
            return renda.valor == valor &&
                    descricao.equals(renda.descricao) &&
                    descricaoP.getValue().equals(renda.descricaoP.getValue())
                    && valorP.getValue().equals(renda.valorP.getValue()) &&
                    valFormatado.equals(renda.valFormatado);
        }
    }

    @Override
    public int hashCode() {
        return Double.hashCode(valor) + descricao.hashCode() +
                descricaoP.getValue().hashCode() +
                valorP.getValue().hashCode() + valFormatado.hashCode();
    }
}
