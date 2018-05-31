package com.github.palmeidaprog.financeira.clientes;

/*
 * UNICAP - Univesidade Catolica de Pernambuco
 * @author Aluno: Paulo R. Almeida Filho
 * http://www.github.com/palmeidaprog/financeira
 * @email pauloalmeidaf@gmail.com
 * @email palmeidaprogramming@gmail.com
 * Professor: Antonio Canvalcanti
 */

import com.github.palmeidaprog.financeira.clientes.adapter.TabelaValorDescrito;
import com.github.palmeidaprog.financeira.exception
        .ProcuraSemResultadoException;
import com.github.palmeidaprog.financeira.interfaces.ObservableSerializable;
import com.github.palmeidaprog.financeira.interfaces.ValorDescrito;
import com.github.palmeidaprog.financeira.interfaces.ValorDescritoController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.Serializable;
import java.util.*;

// ObservableSerializable
public class PendenciaController extends ValorDescritoController implements
        Serializable, Observer {
    private List<Pendencia> pendencias;

    // desserialização
    public PendenciaController(List<Pendencia> pendencias) {
        this.pendencias = pendencias;
        addObserversToElements(this, this.pendencias);
    }

    public PendenciaController() {
        pendencias = new ArrayList<>();
    }

    public ObservableList<Pendencia> getObservable() {
        return FXCollections.observableList(pendencias);
    }

    //--ValorDescritoControlller----------------------------------------------

    @Override
    public <T extends TabelaValorDescrito, V extends ValorDescrito> void
            addTabela(T o) {
        super.addObserver(o);
        @SuppressWarnings("unchecked")
        ObservableList<V> l = o.getLista();
        l = FXCollections.observableList(pendencias);
    }


    //------------------------------------------------------------------------

    public void inserir(Pendencia pendencia) {
        pendencias.add(pendencia);
        notifyChange(pendencias);
    }

    public void remover(Pendencia pendencia) {
        pendencias.remove(pendencia);
        notifyChange(pendencias);
    }

    public void remover(int index) {
        remover(get(index));
    }

    /* @param   descricao   Parte ou toda descricao
     * @returnn             List<Pendencia> pendencias que contem descricao*/
    public List<Pendencia> procurar(String descricao) throws
            ProcuraSemResultadoException {
        List<Pendencia> resultado = new ArrayList<>();
        for(Pendencia p : pendencias) {
            if(p.getDescricao().contains(descricao)) {
                resultado.add(p);
            }
        }
        if(resultado.isEmpty()) {
            throw new ProcuraSemResultadoException("Nao existem pendências " +
                    "com a descrição " + descricao + ".");
        }
        return resultado;
    }

    /* @param  valor    valor a ser procurado
     * @return          List com pendencias que contem o valor*/
    public List<Pendencia> procurar(double valor) throws
            ProcuraSemResultadoException {
        List<Pendencia> resultado = new ArrayList<>();
        for(Pendencia p : pendencias) {
            if(p.getValor() == valor) {
                resultado.add(p);
            }
        }

        if(resultado.isEmpty()) {
            throw new ProcuraSemResultadoException("Não existem pendencia com"
                + " " + formataValor(valor) + ".");
        }
        return resultado;
    }

    private String formataValor(double valor) {
        return String.format(Locale.getDefault(), "%.2f", valor);
    }

    public Pendencia get(int index) {
        return pendencias.get(index);
    }

    public int indexOf(Pendencia pendencia) throws
            ProcuraSemResultadoException {
        for(int i = 0; i < pendencias.size(); i++) {
            if(pendencia.equals(pendencias.get(i))) {
                return i;
            }
        }
        throw new ProcuraSemResultadoException("Não existe a Pendencia " +
                pendencia + ".");
     }

    public double total() {
        double retorno = 0;
        for(Pendencia p : pendencias) {
            retorno += p.getValor();
        }
        return retorno;
    }

    public String formatado() {
        return formataValor(total());
    }

    //--Observer method-------------------------------------------------------

    @Override
    public void update(Observable o, Object arg) {
        notifyChange(o);
    }


    //--Object OVerride-------------------------------------------------------

    @Override
    public String toString() {
        return "PendenciaController{" +
                "pendencias=" + pendencias +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof PendenciaController)) {
            return false;
        } else {
            PendenciaController b = (PendenciaController) o;
            return b.comparaList(this.pendencias, b.pendencias);
        }
    }

    @Override
    public int hashCode() {
        int soma = 0;
        for(Pendencia b : pendencias) {
            soma += b.hashCode();
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

            if(bo instanceof Pendencia) {
                Pendencia pend = (Pendencia) bo;
                if(pend.equals(lo)) {
                    continue;
                }
            }
            return false;
        }
        return true;
    }
}
