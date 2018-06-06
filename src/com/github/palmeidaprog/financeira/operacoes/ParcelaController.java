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
import java.util.*;

public class ParcelaController extends Observado implements
        Serializable, Observador {
    private List<Parcela> parcelas;
    private List<Parcela> pagas;
    private int numeroDeParcelas;
    private double valorParcela;
    private int parcelasRestantes;

    // deserializacao
    public ParcelaController(List<Parcela> parcelas, List<Parcela> pagas,
                             int numeroDeParcelas, double valorParcela,
                             int parcelasRestantes) {
        this.parcelas = parcelas;
        this.pagas = pagas;
        observarElementos(this, parcelas);
        observarElementos(this, pagas);
        this.numeroDeParcelas = numeroDeParcelas;
        this.valorParcela = valorParcela;
        this.parcelasRestantes = parcelasRestantes;
    }

    public ParcelaController(int numeroDeParcelas, Parcela primeiraParcela) {
        parcelas = new ArrayList<>(numeroDeParcelas);
        pagas = new ArrayList<>();
        this.numeroDeParcelas = numeroDeParcelas;
        numeroDeParcelas = parcelasRestantes;
        valorParcela = primeiraParcela.getValor();
        geraParcelas(numeroDeParcelas, primeiraParcela);
    }

    private void geraParcelas(int numeroDeParcelas, Parcela
            primeiraParcela) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(primeiraParcela.getVencimento());
        primeiraParcela.adicionaObservador(this);
        parcelas.add(0, primeiraParcela);
        for(int i = 1; i < numeroDeParcelas; i++) {
            Parcela p = parcelas.get(i - 1).proximaParcela();
            p.adicionaObservador(this);
            parcelas.add(i, p);
        }
    }

    public Parcela getParcela(int numero) {
        return get(numero - 1);
    }

    public Parcela get(int index) {
        return parcelas.get(index);
    }

    public Parcela proximoVencimento() {
        return parcelas.get(0);
    }

    //--Observador method-------------------------------------------------------

    @Override
    public void atualizar(EventoObs ev) {
        if(arg instanceof Pagamento && o instanceof Parcela) {
            parcelas.remove(o);
            pagas.add((Parcela) o);
        }
        notificarEvento(parcelas);
    }

    //--Object override-------------------------------------------------------

    @Override
    public String toString() {
        return "ParcelaController{" +
                "parcelas=" + parcelas +
                ", numeroDeParcelas=" + numeroDeParcelas +
                ", valorParcela=" + valorParcela +
                ", parcelasRestantes=" + parcelasRestantes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(!(o instanceof ParcelaController)) {
            return false;
        } else {
            ParcelaController that = (ParcelaController) o;
            return numeroDeParcelas == that.numeroDeParcelas &&
                    that.valorParcela == valorParcela &&
                    parcelasRestantes == that.parcelasRestantes &&
                    comparaList(parcelas, that.parcelas) &&
                    comparaList(pagas, that.pagas);
        }
    }

    @Override
    public int hashCode() {
        int soma = 0;
        for(Parcela b : parcelas) {
            soma += b.hashCode();
        }

        for(Parcela b : pagas) {
            soma += b.hashCode();
        }

        return soma + numeroDeParcelas + Double.hashCode(valorParcela) +
                parcelasRestantes;
    }

    // suporte para equals()
    private <T> boolean comparaList(Collection<T> b, Collection<T> l) {
        Iterator bi = b.iterator();
        Iterator li = l.iterator();

        if(b.size() != l.size()) {
            return false;
        }
        while(bi.hasNext()) {
            Object bo = bi.next();
            Object lo = li.next();

            if(bo instanceof Parcela) {
                Parcela parc = (Parcela) bo;
                if(!parc.equals(lo)) {
                    return false;
                }
            }
        }
        return true;
    }
}
