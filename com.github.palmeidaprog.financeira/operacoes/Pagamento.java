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

public class Pagamento {
    private final Date dataDePagto;
    private final double valor;
    private final double desconto;

    public Pagamento(Date dataDePagto, double valor) {
        this.dataDePagto = dataDePagto;
        this.valor = valor;
        this.desconto = 0;
    }

    public Pagamento(Date dataDePagto, double valor, double desconto) {
        this.dataDePagto = dataDePagto;
        this.valor = valor;
        this.desconto = desconto;
    }

    public Date getDataDePagto() {
        return dataDePagto;
    }

    public double getValor() {
        return valor;
    }

    public double getDesconto() {
        return desconto;
    }

}
