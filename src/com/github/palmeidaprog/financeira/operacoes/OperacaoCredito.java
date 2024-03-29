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
import com.github.palmeidaprog.financeira.interfaces.observador.EventoObs;
import com.github.palmeidaprog.financeira.interfaces.observador.Observado;
import com.github.palmeidaprog.financeira.interfaces.observador.Observador;
import com.github.palmeidaprog.financeira.interfaces.observador.TipoEvento;

import java.io.Serializable;
import java.util.*;

public class OperacaoCredito extends Observado implements
        Serializable, Observador {
    private double valorNominal;
    private double jurosAoMes;
    private String descricao;
    private Cliente avalista;
    private final ParcelaController parcelas;
    private final PagamentoController pagamentos;

    // deserializacao
    public OperacaoCredito(double valorNominal, double jurosAoMes, String
            descricao, Cliente avalista, ParcelaController parcelas,
           PagamentoController pagamentos) {
        this.valorNominal = valorNominal;
        this.jurosAoMes = jurosAoMes;
        this.descricao = descricao;
        this.avalista = avalista;
        this.avalista.adicionaObservador(this);
        this.parcelas = parcelas;
        this.parcelas.adicionaObservador(this);
        this.pagamentos = pagamentos;
        this.pagamentos.adicionaObservador(this);
    }

    public OperacaoCredito(double valorNominal, int numeroDeParcelas, double
            jurosAoMes, Parcela primeiraParcela) {
        this.valorNominal = valorNominal;
        this.jurosAoMes = jurosAoMes;
        parcelas = new ParcelaController(numeroDeParcelas, primeiraParcela);
        parcelas.adicionaObservador(this);
        pagamentos = new PagamentoController();
        pagamentos.adicionaObservador(this);
    }

    public OperacaoCredito(double valorNominal, int numeroDeParcelas, double
            jurosAoMes, Parcela primeiraParcela, String descricao) {
        this(valorNominal, numeroDeParcelas, jurosAoMes, primeiraParcela);
        this.descricao = descricao;
    }

    public static double calculaParcela(double valorNominal, int
            numeroDeParcelas, double jurosAoMes, Date vencimento) {
        double i = jurosAoMes / 100.0;
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
        notificarEvento(this.valorNominal, TipoEvento.EDITADO);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
        notificarEvento(this.descricao, TipoEvento.EDITADO);
    }

    public double getJurosAoMes() {
        return jurosAoMes;
    }

    public void setJurosAoMes(double jurosAoMes) {
        this.jurosAoMes = jurosAoMes;
        notificarEvento(this.jurosAoMes, TipoEvento.EDITADO);
    }

    public Cliente getAvalista() {
        return avalista;
    }

    public void setAvalista(Cliente avalista) {
        this.avalista.deletaObservador(this);
        this.avalista = avalista;
        this.avalista.adicionaObservador(this);
        notificarEvento(this.avalista, TipoEvento.EDITADO);
    }

    //--Observador method-------------------------------------------------------

    @Override
    public void atualizar(EventoObs ev) {
        notificarEvento(ev);
    }

    //--Object override-------------------------------------------------------

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

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(!(o instanceof OperacaoCredito)) {
            return false;
        } else {
            OperacaoCredito op = (OperacaoCredito) o;
            return op.valorNominal == valorNominal &&
                    op.jurosAoMes == jurosAoMes &&
                    descricao.equals(op.descricao) &&
                    avalista.equals(op.avalista) &&
                    parcelas.equals(op.parcelas) &&
                    pagamentos.equals(op.pagamentos);
        }
    }

    @Override
    public int hashCode() {
        return Double.hashCode(valorNominal) + Double.hashCode(jurosAoMes) +
                descricao.hashCode() + avalista.hashCode() +
                parcelas.hashCode() + pagamentos.hashCode();
    }
}
