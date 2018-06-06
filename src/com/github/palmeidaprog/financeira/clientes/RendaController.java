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
import com.github.palmeidaprog.financeira.interfaces.observador.EventoObs;
import com.github.palmeidaprog.financeira.interfaces.observador.Observado;
import com.github.palmeidaprog.financeira.interfaces.observador.Observador;
import com.github.palmeidaprog.financeira.interfaces.observador.TipoEvento;

import java.io.Serializable;
import java.util.*;

public class RendaController extends Observado implements
        Serializable, Observador {
    private List<Renda> rendas = new ArrayList<>();

    //deserializacao
    public RendaController(List<Renda> rendas) {
        this.rendas = rendas;
        observarElementos(this, this.rendas);
    }

    public RendaController(Renda renda) {
        this();
        rendas.add(renda);
        renda.adicionaObservador(this);
    }

    public RendaController() { }

    public void inserir(Renda renda) {
        renda.adicionaObservador(this);
        rendas.add(renda);
        sort(rendas);
        notificarEvento(renda, TipoEvento.ADICIONADO, this.rendas);
    }

    public void inserir(Collection<? extends Renda> c) {
        for(Renda r : c) {
            inserir(r);
        }
    }

    public void remover(Renda renda) {
        rendas.remove(renda);
        renda.deletaObservador(this);
        notificarEvento(renda, TipoEvento.REMOVIDO, this.rendas);
    }

    public void remover(int index) {
        remover(get(index));
    }

    public Renda get(int index) {
        return rendas.get(index);
    }

    public List<Renda> getAll() {
        return rendas;
    }

    /* @return  List<Renda> contendo as rendas cuja descrição contem o
         *          parametro descrição     */
    public List<Renda> procurar(String descricao) throws
            ProcuraSemResultadoException {
        List<Renda> resultado = new ArrayList<>();
        for(Renda r : rendas) {
            if(r.getDescricao().toLowerCase().contains(descricao.
                    toLowerCase())) {
                resultado.add(r);
            }
        }

        if(resultado.isEmpty()) {
            throw new ProcuraSemResultadoException("Não existe rendas com a" +
                " descricao \"" + descricao + "\".");
        }
        sort(resultado);
        return resultado;
    }

    public List<Renda> procurar(double valor) throws
            ProcuraSemResultadoException {
        List<Renda> resultado = new ArrayList<>();
        for(Renda r : rendas) {
            if(r.getValor() == valor) {
                resultado.add(r);
            }
        }

        if(resultado.isEmpty()) {
            throw new ProcuraSemResultadoException("");
        }
        sort(resultado);
        return resultado;
    }

    /* Contem Rendas dentro de um intervalor [minimo, maximo] inclusivo
     * @oaram   minimo  valor minimo para renda
     * @param   maximo  valor maximo para renda
     * @return          retorna list que contem Renda dentro do intervalo
     *                  descrito por [minimo, maximo]*/
    public List<Renda> procurar(double minimo, double maximo) throws
            ProcuraSemResultadoException {
        List<Renda> resultado = new ArrayList<>();
        for(Renda r : rendas) {
            if(r.getValor() >= minimo && r.getValor() <= maximo) {
                resultado.add(r);
            }
        }
        if(resultado.isEmpty()) {
            throw new ProcuraSemResultadoException("Não existem rendas no " +
                "intervalo de " + formataValor(minimo) + " a " +
                    formataValor(maximo) + ".");
        }
        sort(resultado);
        return resultado;
    }

    // retorna Renda Total
    public double total() {
        double soma = 0;

        for(Renda r : rendas) {
            soma += r.getValor();
        }
        return soma;
    }

    private String formataValor(double valor) {
        return String.format(Locale.getDefault(), "%.2f", valor);
    }

    // organiza por ordem de valor
    private void sort(List<Renda> list) {
        Collections.sort(list, new Comparator<Renda>() {
            @Override
            public int compare(Renda renda1, Renda renda2) {
                return (int) (renda1.getValor() - renda2.getValor());
            }
        });
    }

    public String formatado() {
        return formataValor(total());
    }

    //--Observador method-------------------------------------------------------

    @Override
    public void atualizar(EventoObs ev) {
        notificarEvento(ev);
    }

    //--Object override-------------------------------------------------------

    @Override
    public String toString() {
        return "RendaController{" +
                "rendas=" + rendas +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(!(o instanceof RendaController)) {
            return false;
        } else {
            RendaController that = (RendaController) o;
            return comparaList(rendas, that.rendas);
        }
    }

    @Override
    public int hashCode() {
        int soma = 0;
        for(Renda b : rendas) {
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

            if(bo instanceof Renda) {
                Renda parc = (Renda) bo;
                if(!parc.equals(lo)) {
                    return false;
                }
            }
        }
        return true;
    }
}
