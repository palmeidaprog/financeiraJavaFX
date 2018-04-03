package com.github.palmeidaprog.financeira.operacoes;

/*
 * UNICAP - Univesidade Catolica de Pernambuco
 * @author Aluno: Paulo R. Almeida Filho
 * http://www.github.com/palmeidaprog/financeira
 * @email pauloalmeidaf@gmail.com
 * @email palmeidaprogramming@gmail.com
 * Professor: Antonio Canvalcanti
 */

import java.util.Date;

public class Debito {
    private final double valor;
    private Date vencimento;
    private double multa;
    private double jurosPorDia;
    private Pagamento pagamento;

    public Debito(double valor, Date vencimento) {
        this.valor = valor;
        this.vencimento = vencimento;
    }

    public Debito(double valor, Date vencimento, double multa, double
            jurosPorDia) {
        this(valor, vencimento);
        this.multa = multa;
        this.jurosPorDia = jurosPorDia;
    }

    public double getValor() {
        return valor;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }

    public double getJurosPorDia() {
        return jurosPorDia;
    }

    public void setJurosPorDia(double jurosPorDia) {
        this.jurosPorDia = jurosPorDia;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }
}
