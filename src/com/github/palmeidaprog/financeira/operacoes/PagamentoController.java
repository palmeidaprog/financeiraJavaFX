package com.github.palmeidaprog.financeira.operacoes;

/*
 * UNICAP - Univesidade Catolica de Pernambuco
 * @author Aluno: Paulo R. Almeida Filho
 * http://www.github.com/palmeidaprog/financeira
 * @email pauloalmeidaf@gmail.com
 * @email palmeidaprogramming@gmail.com
 * Professor: Antonio Canvalcanti
 */

import com.github.palmeidaprog.financeira.exception.ProcuraSemResultadoException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class PagamentoController {
    private List<Pagamento> pagamentos = new ArrayList<>();

    public void inserir(Pagamento pagamento) {
        pagamentos.add(pagamento);
    }

    public void inserir(Collection<Pagamento> pagamentos) {
        this.pagamentos.addAll(pagamentos);
    }

    public void remover(Pagamento pagamento) {
        pagamentos.remove(pagamento);
    }

    public void remover(int index) {
        pagamentos.remove(index);
    }

    public Pagamento get(int index) {
        return pagamentos.get(index);
    }

    public List<Pagamento> procurar(Date data) throws
            ProcuraSemResultadoException {
        List<Pagamento> resultado = new ArrayList<>();
        for(Pagamento p : pagamentos) {
            if(p.getDataDePagto().equals(data)) {
                resultado.add(p);
            }
        }
        if(resultado.isEmpty()) {
            throw new ProcuraSemResultadoException("Não existem pagamentos " +
                    "na data " + data + ".");
        }
        return resultado;
    }

    public List<Pagamento> procurar(Date inicio, Date fim) throws
            ProcuraSemResultadoException {
        List<Pagamento> resultado = new ArrayList<>();
        for(Pagamento p : pagamentos) {
            if(p.getDataDePagto().equals(inicio) || p.getDataDePagto()
                    .equals(fim) || (p.getDataDePagto().after(inicio) &&
                    p.getDataDePagto().before(fim))) {
                resultado.add(p);
            }
        }
        if(resultado.isEmpty()) {
            throw new ProcuraSemResultadoException("Não existem pagamentos " +
                    "no intervalo de " + inicio + " a " + fim +
                    ".");
        }
        return resultado;
    }

    public int indexOf(Pagamento pagamento) throws
            ProcuraSemResultadoException {
        for(int i = 0; i < pagamentos.size(); i++) {
            if(pagamentos.get(i).equals(pagamento)) {
                return i;
            }
        }
        throw new ProcuraSemResultadoException("Pagamento " + pagamento +
                " não encontrado.");
    }

    public int numeroDePagamentos() {
        return pagamentos.size();
    }

    public int totalPago() {
        int soma = 0;
        for(Pagamento p : pagamentos) {
            soma += p.getValor();
        }
        return soma;
    }
}
