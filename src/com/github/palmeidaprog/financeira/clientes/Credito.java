package com.github.palmeidaprog.financeira.clientes;

/*
 * UNICAP - Univesidade Catolica de Pernambuco
 * @author Aluno: Paulo R. Almeida Filho
 * http://www.github.com/palmeidaprog/financeira
 * @email pauloalmeidaf@gmail.com
 * @email palmeidaprogramming@gmail.com
 * Professor: Antonio Canvalcanti
 */

import com.github.palmeidaprog.financeira.interfaces.ObservableSerializable;
import java.io.Serializable;

public class Credito extends ObservableSerializable implements Serializable {
    private double financiamento;
    private double pessoal;

    public Credito() {
        financiamento = 0;
        pessoal = 0;
    }

    // deserialização
    public Credito(double financiamento, double pessoal) {
        this.financiamento = financiamento;
        this.pessoal = pessoal;
    }

    public double getFinanciamento() {
        return financiamento;
    }

    public void setFinanciamento(double financiamento) {
        this.financiamento = financiamento;
        notifyChange(this);
    }

    public double getPessoal() {
        return pessoal;
    }

    public void setPessoal(double pessoal) {
        this.pessoal = pessoal;
        notifyChange(this);
    }

    //--Object override-------------------------------------------------------

    @Override
    public String toString() {
        return "Credito{" +
                "financiamento=" + financiamento +
                ", pessoal=" + pessoal +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(!(o instanceof Credito)) {
            return false;
        } else {
            Credito credito = (Credito) o;
            return credito.financiamento == financiamento &&
                    credito.pessoal == pessoal;
        }
    }

    @Override
    public int hashCode() {
        return Double.hashCode(financiamento) + Double.hashCode(pessoal);
    }
}
