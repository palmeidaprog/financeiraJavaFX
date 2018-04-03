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

public class OperacaoCredito {
    private double valorNominal;
    private double jurosAoMes;
    private String descricao;
    private Cliente avalista;
    private final ParcelaController parcelas;
    private final PagamentoController pagamentos = new PagamentoController();

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
}
