package com.github.palmeidaprog.financeira.operacoes;

/*
 * UNICAP - Univesidade Catolica de Pernambuco
 * @author Aluno: Paulo R. Almeida Filho
 * http://www.github.com/palmeidaprog/financeira
 * @email pauloalmeidaf@gmail.com
 * @email palmeidaprogramming@gmail.com
 * Professor: Antonio Canvalcanti
 */

import com.github.palmeidaprog.financeira.clientes.Bem;
import com.github.palmeidaprog.financeira.interfaces.observador.EventoObs;
import com.github.palmeidaprog.financeira.interfaces.observador.Observador;
import java.util.Objects;

public class Financiamento extends OperacaoCredito implements Observador {
    private Bem garantia;

    public Financiamento(double valorNominal, int numeroDeParcelas, double
            jurosAoMes, Parcela primeiraParcela, Bem garantia) {
        super(valorNominal, numeroDeParcelas, jurosAoMes, primeiraParcela);
        this.garantia = garantia;
        this.garantia.adicionaObservador(this);

    }

    public Financiamento(double valorNominal, int numeroDeParcelas, double
            jurosAoMes, Parcela primeiraParcela, String descricao, Bem
            garantia) {
        super(valorNominal, numeroDeParcelas, jurosAoMes, primeiraParcela,
                descricao);
        this.garantia = garantia;
        this.garantia.adicionaObservador(this);
    }

    public Bem getGarantia() {
        return garantia;
    }

    public void setGarantia(Bem garantia) {
        this.garantia.deletaObservador(this);
        this.garantia = garantia;
        this.garantia.adicionaObservador(this);
    }

    //--Observador------------------------------------------------------------

    @Override
    public void atualizar(EventoObs ev) {
        notificarEvento(ev);
    }

    //--Object override-------------------------------------------------------

    @Override
    public String toString() {
        return "Financiamento{" +
                "garantia=" + garantia +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(!(o instanceof Financiamento)) {
            return false;
        } else {
            Financiamento that = (Financiamento) o;
            return super.equals(o) && garantia.equals(that.garantia);
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode() + Objects.hash(garantia);
    }
}
