package com.github.palmeidaprog.financeira.operacoes;

/*
 * UNICAP - Univesidade Catolica de Pernambuco
 * @author Aluno: Paulo R. Almeida Filho
 * http://www.github.com/palmeidaprog/financeira
 * @email pauloalmeidaf@gmail.com
 * @email palmeidaprogramming@gmail.com
 * Professor: Antonio Canvalcanti
 */

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ParcelaController {
    private final List<Parcela> parcelas;
    private int numeroDeParcelas;
    private double valorParcela;
    private int parcelasRestantes;

    public ParcelaController(int numeroDeParcelas, Parcela primeiraParcela) {
        parcelas = new ArrayList<>(numeroDeParcelas);
        this.numeroDeParcelas = numeroDeParcelas;
        numeroDeParcelas = parcelasRestantes;
        valorParcela = primeiraParcela.getValor();
        geraParcelas(numeroDeParcelas, primeiraParcela);
    }

    private void geraParcelas(int numeroDeParcelas, Parcela
            primeiraParcela) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(primeiraParcela.getVencimento());
        parcelas.set(0, primeiraParcela);
        for(int i = 1; i < numeroDeParcelas; i++) {
            parcelas.set(i, parcelas.get(i - 1).proximaParcela());
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


}
