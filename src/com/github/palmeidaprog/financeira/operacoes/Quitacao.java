package com.github.palmeidaprog.financeira.operacoes;

/*
 * UNICAP - Univesidade Catolica de Pernambuco
 * @author Aluno: Paulo R. Almeida Filho
 * http://www.github.com/palmeidaprog/financeira
 * @email pauloalmeidaf@gmail.com
 * @email palmeidaprogramming@gmail.com
 * Professor: Antonio Canvalcanti
 */

import java.io.Serializable;
import java.util.Date;

public class Quitacao extends Debito implements Serializable {
    private double total;
    private double desconto;

    public Quitacao(double valor, Date vencimento, double multa, double
            jurosPorDia, Pagamento pagamento, double total, double desconto) {
        super(valor, vencimento, multa, jurosPorDia, pagamento);
        this.total = total;
        this.desconto = desconto;
    }

    // deserializacao
    public Quitacao(double valor, Date vencimento, double total, double
            desconto) {
        super(valor, vencimento);
        construct(total, desconto);
    }

    public Quitacao(double valor, Date vencimento, double multa, double
            jurosPorDia, double total, double desconto) {
        super(valor, vencimento, multa, jurosPorDia);
        construct(total, desconto);
    }

    private void construct(double total, double desconto) {
        this.total = total;
        this.desconto = desconto;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }


    @Override
    public String toString() {
        return "Quitacao{" +
                "total=" + total +
                ", desconto=" + desconto +
                '}';
    }
}
