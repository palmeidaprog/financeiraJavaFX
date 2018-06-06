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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.io.Serializable;
import java.util.Locale;

public class Pendencia extends Observado implements Serializable,
        ValorDescrito {
    private double valor;
    private StringProperty valorP = new SimpleStringProperty();
    private StringProperty descricaoP = new SimpleStringProperty();;
    private String descricao;

    // deserialização
    public Pendencia(double valor, String descricao) {
        this.valor = valor;
        valorP.setValue(valorFormatado(valor));
        this.descricao = descricao;
        descricaoP.setValue(descricao);
    }

    @Override
    public double getValor() {
        return valor;
    }

    @Override
    public void setValor(double valor) {
        this.valor = valor;
        valorP.setValue(valorFormatado(valor));
        notifyChange(this.valor);
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
        descricaoP.setValue(descricao);
        notifyChange(this.descricao);
    }

    @Override
    public String valorFormatado(double valor) {
        return String.format(Locale.getDefault(), "%.2f", valor);
    }

    private String formatado() {
        return valorFormatado(valor) + " " + descricao;
    }

    //--Object override-------------------------------------------------------

    @Override
    public String toString() {
        return "Pendencia{" +
                "valor=" + valor +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if(!(o instanceof Pendencia)) {
            return false;
        } else {
            Pendencia pendencia = (Pendencia) o;
            return pendencia.valor == valor &&
                    valorP.getValue().equals(pendencia.valorP.getValue()) &&
                    descricaoP.getValue().equals(pendencia.descricaoP
                            .getValue()) &&
                    descricao.equals(pendencia.descricao);
        }
    }

    @Override
    public int hashCode() {
        return Double.hashCode(valor) + valorP.getValue().hashCode() +
                descricaoP.getValue().hashCode() + descricao.hashCode();
    }
}
