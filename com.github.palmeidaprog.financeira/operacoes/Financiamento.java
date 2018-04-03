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

public class Financiamento extends OperacaoCredito {
    private Bem garantia;

    public Financiamento(double valorNominal, int numeroDeParcelas, double
            jurosAoMes, Parcela primeiraParcela, Bem garantia) {
        super(valorNominal, numeroDeParcelas, jurosAoMes, primeiraParcela);
        this.garantia = garantia;
    }

    public Financiamento(double valorNominal, int numeroDeParcelas, double
            jurosAoMes, Parcela primeiraParcela, String descricao, Bem
            garantia) {
        super(valorNominal, numeroDeParcelas, jurosAoMes, primeiraParcela,
                descricao);
        this.garantia = garantia;
    }

    public Bem getGarantia() {
        return garantia;
    }

    public void setGarantia(Bem garantia) {
        this.garantia = garantia;
    }
}
