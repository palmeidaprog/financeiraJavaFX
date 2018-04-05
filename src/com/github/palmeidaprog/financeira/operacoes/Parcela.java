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
import java.util.Calendar;
import java.util.Date;

public class Parcela extends Debito implements Serializable {
    private final int numeroDaParcela;

    // deserializacao
    public Parcela(double valor, Date vencimento, int numeroDaParcela) {
        super(valor, vencimento);
        this.numeroDaParcela = numeroDaParcela;
    }

    public Parcela(double valor, Date vencimento, double multa, double
            jurosPorDia, int numeroDaParcela) {
        super(valor, vencimento, multa, jurosPorDia);
        this.numeroDaParcela = numeroDaParcela;
    }

    public int getNumeroDaParcela() {
        return numeroDaParcela;
    }

    public Parcela proximaParcela() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.getVencimento());
        calendar.add(Calendar.MONTH, 1);
        return new Parcela(this.getValor(), calendar.getTime(), this
                .getMulta(), this.getJurosPorDia(), this.getNumeroDaParcela()
                 + 1);
    }

    @Override
    public String toString() {
        return "Parcela{" +
                "numeroDaParcela=" + numeroDaParcela +
                '}';
    }
}
