package com.github.palmeidaprog.financeira.clientes;

/*
 * Financeira App
 * UNICAP - Univesidade Catolica de Pernambuco
 * @author Aluno: Paulo R. Almeida Filho
 * http://www.github.com/palmeidaprog/financeira
 * @email pauloalmeidaf@gmail.com
 * @email palmeidaprogramming@gmail.com
 * Professor: Antonio Canvalcanti
 */

import com.github.palmeidaprog.financeira.exception.ProcuraSemResultadoException;
import com.github.palmeidaprog.financeira.interfaces.observador.Observado;

import java.io.Serializable;
import java.util.*;

public class BemController extends Observado implements
        Serializable, Observer {
    private List<Bem> bens;

    //construtor de deserialização
    public BemController(List<Bem> bens) {
        this();
        inserir(bens);
    }

    public BemController() {
        bens = new ArrayList<>();
    }

    public BemController(Bem bem) {
        this();
        inserir(bem);
    }

    //--Observer interface----------------------------------------------------

    @Override
    public void atualizar(EventoObservado ev) {
        notifyChange(o);
    }

    //------------------------------------------------------------------------

    public void inserir(Bem bem) {
        bens.add(bem);
        notifyChange(bens);
    }

    public void inserir(List<Bem> bens) {
        for(Bem b : bens) {
            inserir(b);
        }
    }

    public void remover(Bem bem) {
        bens.remove(bem);
        notifyChange(this.bens);
    }

    public void remover(int index) {
        remover(get(index));
    }

    public Bem get(int index) {
        return bens.get(index);
    }

    public List<Bem> getAll() {
        return bens;
    }

    public double total() {
        double retorno = 0;

        for(Bem b : bens) {
            retorno += b.getValor();
        }
        return retorno;
    }

    public double totalLiquido() {
        double retorno = 0;

        for(Bem b : bens) {
            retorno += b.valorLiquido();
        }
        return retorno;
    }

    public double totalPendencias() {
        double retorno = 0;

        for(Bem b : bens) {
            retorno += b.getPendencias().total();
        }
        return retorno;
    }

    public List<Bem> procurar(double valor) throws
            ProcuraSemResultadoException {
        List<Bem> resultado = new ArrayList<>();
        for(Bem b : bens) {
            if(b.getValor() == valor) {
                resultado.add(b);
            }
        }
        if(resultado.isEmpty()) {
            throw new ProcuraSemResultadoException("Não existe bens no valor "
                    + "de " + formataValor(valor) + ".");
        }
        return resultado;
    }

    /* @return Retorna Bens que estao entre os valores minimo e maximo dos
    parametros */
    public List<Bem> procurar(double minimo, double maximo) throws
            ProcuraSemResultadoException {
        List<Bem> resultado = new ArrayList<>();
        for(Bem b : bens) {
            if(b.getValor() >= minimo && b.getValor() <= maximo) {
                resultado.add(b);
            }
        }
        if(resultado.isEmpty()) {
            throw new ProcuraSemResultadoException("Não existe bens no " +
                    "intervalo de valor de " + formataValor(minimo) + "a " +
                    formataValor(maximo) + ".");
        }
        return resultado;
    }

    //TODO: procura de descricao e placas

    public int indexOf(Bem bem) throws
            ProcuraSemResultadoException {
        for(int i = 0; i < bens.size(); i++) {
            if(bem.equals(bens.get(i))) {
                return i;
            }
        }
        throw new ProcuraSemResultadoException("Não existe o Bem " +
                bem + ".");
    }

    // Organiza Lista por ordem de valor do bem
    private void sort(List<Bem> list) {
        Collections.sort(list, new Comparator<Bem>() {
            @Override
            public int compare(Bem bem1, Bem bem2) {
                return (int) (bem1.getValor() - bem2.getValor());
            }
        });
    }

    private String formataValor(double valor) {
        return String.format(Locale.getDefault(), "%.2f", valor);
    }

    //--Object override-------------------------------------------------------

    @Override
    public String toString() {
        return "BemController{" +
                "bens=" + bens +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof BemController)) {
            return false;
        } else {
            BemController b = (BemController) o;
            return b.comparaList(this.bens, b.bens);
        }
    }

    @Override
    public int hashCode() {
        int soma = 0;
        for(Bem b : bens) {
            if(b instanceof Automovel) {
                Automovel auto = (Automovel) b;
                soma += auto.hashCode();
            } else if(b instanceof Imovel) {
                Imovel imov = (Imovel) b;
                soma += imov.hashCode();
            } else {
                soma += b.hashCode();
            }
        }
        return soma;
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

            if(bo instanceof Automovel) {
                Automovel auto = (Automovel) bo;
                if(!auto.equals(lo)) {
                    return false;
                }
            } else if(bo instanceof Imovel) {
                Imovel imov = (Imovel) bo;
                if(!imov.equals(lo)) {
                    return false;
                }
            } else {
                Bem bem = (Bem) bo;
                if(!bem.equals(lo)) {
                    return false;
                }
            }
        }
        return true;
    }

}
