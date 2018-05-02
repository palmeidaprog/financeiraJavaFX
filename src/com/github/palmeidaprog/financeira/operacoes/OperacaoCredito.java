package com.github.palmeidaprog.financeira.operacoes;

/*
 * UNICAP - Univesidade Catolica de Pernambuco
 * @author Aluno: Paulo R. Almeida Filho
 * http://www.github.com/palmeidaprog/financeira
 * @email pauloalmeidaf@gmail.com
 * @email palmeidaprogramming@gmail.com
 * Professor: Antonio Canvalcanti
 */

import com.github.palmeidaprog.financeira.clientes.Cliente;

import java.io.Serializable;
import java.util.Date;

public class OperacaoCredito implements Serializable {
    private double valorNominal;
    private double jurosAoMes;
    private String descricao;
    private Cliente avalista;
    private final ParcelaController parcelas;
    private final PagamentoController pagamentos = new PagamentoController();

    // deserializacao
    public OperacaoCredito(double valorNominal, double jurosAoMes, String
            descricao, Cliente avalista, ParcelaController parcelas) {
        this.valorNominal = valorNominal;
        this.jurosAoMes = jurosAoMes;
        this.descricao = descricao;
        this.avalista = avalista;
        this.parcelas = parcelas;
    }

    public OperacaoCredito(double valorNominal, int numeroDeParcelas, double
            jurosAoMes, Parcela primeiraParcela) {
        this.valorNominal = valorNominal;
        this.jurosAoMes = jurosAoMes;
        parcelas = new ParcelaController(numeroDeParcelas, primeiraParcela);
    }

    public OperacaoCredito(double valorNominal, int numeroDeParcelas, double
            jurosAoMes, Parcela primeiraParcela, String descricao) {
        this(valorNominal, numeroDeParcelas, jurosAoMes, primeiraParcela);
        this.descricao = descricao;
    }

    public static double calculaParcela(double valorNominal, int
            numeroDeParcelas, double jurosAoMes, Date vencimento) {
        double i = jurosAoMes / 100.0;
//        return (valorNominal * jurosAoMes / 100) / (1 + Math.pow(1 +
//                jurosAoMes / 100, numeroDeParcelas));
        return valorNominal * (i / (1.0 - (1.0 / Math.pow(1.0 + i,
                numeroDeParcelas))));
    }

    public PagamentoController getPagamentos() {
        return pagamentos;
    }

    public ParcelaController getParcelas() {
        return parcelas;
    }

    public double getValorNominal() {
        return valorNominal;
    }

    public void setValorNominal(double valorNominal) {
        this.valorNominal = valorNominal;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getJurosAoMes() {
        return jurosAoMes;
    }

    public void setJurosAoMes(double jurosAoMes) {
        this.jurosAoMes = jurosAoMes;
    }

    public Cliente getAvalista() {
        return avalista;
    }

    public void setAvalista(Cliente avalista) {
        this.avalista = avalista;
    }

    @Override
    public String toString() {
        return "OperacaoCredito{" +
                "valorNominal=" + valorNominal +
                ", jurosAoMes=" + jurosAoMes +
                ", descricao='" + descricao + '\'' +
                ", avalista=" + avalista +
                ", parcelas=" + parcelas +
                ", pagamentos=" + pagamentos +
                '}';
    }
}
