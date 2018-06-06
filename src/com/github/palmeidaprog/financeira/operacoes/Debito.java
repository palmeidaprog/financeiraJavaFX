package com.github.palmeidaprog.financeira.operacoes;

/*
 * UNICAP - Univesidade Catolica de Pernambuco
 * @author Aluno: Paulo R. Almeida Filho
 * http://www.github.com/palmeidaprog/financeira
 * @email pauloalmeidaf@gmail.com
 * @email palmeidaprogramming@gmail.com
 * Professor: Antonio Canvalcanti
 */

import com.github.palmeidaprog.financeira.interfaces.observador.Observado;

import java.io.Serializable;
import java.util.Date;
import java.util.Observable;


public class Debito extends Observado implements Serializable,
        Observador {
    private final double valor;
    private Date vencimento;
    private double multa;
    private double jurosPorDia;
    private Pagamento pagamento;

    // deserializacao
    public Debito(double valor, Date vencimento, double multa, double
            jurosPorDia, Pagamento pagamento) {
        this.valor = valor;
        this.vencimento = vencimento;
        this.multa = multa;
        this.jurosPorDia = jurosPorDia;
        this.pagamento = pagamento;
    }

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
        notificarEvento(this.vencimento);
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
        notificarEvento(this.multa);
    }

    public double getJurosPorDia() {
        return jurosPorDia;
    }

    public void setJurosPorDia(double jurosPorDia) {
        this.jurosPorDia = jurosPorDia;
        notificarEvento(this.jurosPorDia);
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
        notificarEvento(this.pagamento);
    }

    //--Observador methods------------------------------------------------------

    @Override
    public void atualizar(EventoObservado ev) {
        notificarEvento(o);
    }

    //--Object override-------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(!(o instanceof Debito)) {
            return false;
        } else {
            Debito debito = (Debito) o;
            return debito.valor == valor &&
                    debito.multa == multa &&
                    debito.jurosPorDia == jurosPorDia &&
                    vencimento == debito.vencimento &&
                    pagamento.equals(debito.pagamento);
        }
    }

    @Override
    public int hashCode() {
        return Double.hashCode(valor) + vencimento.hashCode() +
                Double.hashCode(multa) + Double.hashCode(jurosPorDia) +
                pagamento.hashCode();
    }

    @Override
    public String toString() {
        return "Debito{" +
                "valor=" + valor +
                ", vencimento=" + vencimento +
                ", multa=" + multa +
                ", jurosPorDia=" + jurosPorDia +
                ", pagamento=" + pagamento +
                '}';
    }
}
