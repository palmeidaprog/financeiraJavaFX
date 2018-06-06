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
import java.util.Date;

public class Pagamento implements Serializable {
    private final Date dataDePagto;
    private final double valor;
    private final double desconto;

    public Pagamento(Date dataDePagto, double valor) {
        this.dataDePagto = dataDePagto;
        this.valor = valor;
        this.desconto = 0;
    }

    // deserializacao
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

    //--Object override-------------------------------------------------------

    @Override
    public String toString() {
        return "Pagamento{" +
                "dataDePagto=" + dataDePagto +
                ", valor=" + valor +
                ", desconto=" + desconto +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(!(o instanceof Pagamento)) {
            return false;
        } else {
            Pagamento pagamento = (Pagamento) o;
            return pagamento.valor == valor &&
                    pagamento.desconto == desconto &&
                    dataDePagto.equals(pagamento.dataDePagto);
        }
    }

    @Override
    public int hashCode() {
        return dataDePagto.hashCode() + Double.hashCode(valor) +
                Double.hashCode(desconto);
    }
}
