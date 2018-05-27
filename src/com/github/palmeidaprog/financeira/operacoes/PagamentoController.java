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
import com.github.palmeidaprog.financeira.info.telefone.Telefone;
import com.github.palmeidaprog.financeira.interfaces.ObservableSerializable;

import java.io.Serializable;
import java.util.*;

public class PagamentoController extends ObservableSerializable implements
        Serializable, Observer {
    private List<Pagamento> pagamentos = new ArrayList<>();

    public void inserir(Pagamento pagamento) {
        pagamentos.add(pagamento);
        notifyChange(pagamentos);
    }

    public void inserir(Collection<Pagamento> pagamentos) {
        for(Pagamento p : pagamentos) {
            inserir(p);
        }
    }

    public void remover(Collection<Pagamento> pagamentos) {
        for(Pagamento p : pagamentos) {
            remover(p);
        }
    }

    public void remover(Pagamento pagamento) {
        pagamentos.remove(pagamento);
        notifyChange(pagamentos);
    }

    public void remover(int index) {
        remover(get(index));
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

    //--Observer method-------------------------------------------------------

    @Override
    public void update(Observable o, Object arg) {
        notifyChange(o);
    }

    //--Object Override-------------------------------------------------------

    @Override
    public String toString() {
        return "PagamentoController{" +
                "pagamentos=" + pagamentos +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(!(o instanceof PagamentoController)) {
            return false;
        } else {
            PagamentoController that = (PagamentoController) o;
            return comparaList(pagamentos, that.pagamentos);
        }
    }

    @Override
    public int hashCode() {
        int soma = 0;
        for(Pagamento b : pagamentos) {
            soma += b.hashCode();
        }
        return soma;
    }

    private <T> boolean comparaList(Collection<T> b, Collection<T> l) {
        Iterator bi = b.iterator();
        Iterator li = l.iterator();

        if(b.size() != l.size()) {
            return false;
        }
        while(bi.hasNext()) {
            Object bo = bi.next();
            Object lo = li.next();

            if(bo instanceof Pagamento) {
                Pagamento tel = (Pagamento) bo;
                if(!tel.equals(lo)) {
                    return false;
                }
            }
        }
        return true;
    }
}
