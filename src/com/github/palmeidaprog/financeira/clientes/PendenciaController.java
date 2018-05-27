package com.github.palmeidaprog.financeira.clientes;

/*
 * UNICAP - Univesidade Catolica de Pernambuco
 * @author Aluno: Paulo R. Almeida Filho
 * http://www.github.com/palmeidaprog/financeira
 * @email pauloalmeidaf@gmail.com
 * @email palmeidaprogramming@gmail.com
 * Professor: Antonio Canvalcanti
 */

import com.github.palmeidaprog.financeira.exception.ProcuraSemResultadoException;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.*;

public class PendenciaController implements Serializable {
    private List<Pendencia> pendenciasL;
    private transient ObservableList<Pendencia> pendencias;

    // desserialização
    public PendenciaController(List<Pendencia> pendencias) {
        this.pendenciasL = pendencias;
        this.pendencias = FXCollections.observableList(pendenciasL);
        eventoLista();
    }

    public PendenciaController() {
        pendenciasL = new ArrayList<>();
        this.pendencias = FXCollections.observableList(pendenciasL);
        eventoLista();
    }

    //--Eventos---------------------------------------------------------------

    private void eventoLista() {
        pendencias.addListener(new ListChangeListener<Pendencia>() {
            @Override
            public void onChanged(Change<? extends Pendencia> c) {
                while(c.next()) {
                    pendenciasL.addAll(c.getAddedSubList());
                    pendenciasL.removeAll(c.getRemoved());
                }
            }
        });
    }

    //------------------------------------------------------------------------

    public void inserir(Pendencia pendencia) {
        pendencias.add(pendencia);
    }

    public void remover(Pendencia pendencia) {
        pendencias.remove(pendencia);
    }

    public void remover(int index) {
        pendencias.remove(index);
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
            return b.comparaList(this.pendenciasL, b.pendenciasL);
        }
    }

    @Override
    public int hashCode() {
        int soma = 0;
        for(Pendencia b : pendenciasL) {
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
