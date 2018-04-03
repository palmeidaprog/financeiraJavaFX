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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class RendaController {
    private List<Renda> rendas;


    public RendaController(Renda renda) {
        this();
        rendas.add(renda);
    }

    public RendaController() {
        rendas = new ArrayList<>();
    }

    public void inserir(Renda renda) {
        rendas.add(renda);
        sort(rendas);
    }

    public void remover(Renda renda) {
        rendas.remove(renda);
    }

    public void remover(int index) {
        rendas.remove(index);
    }

    public Renda get(int index) {
        return rendas.get(index);
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

    @Override
    public String toString() {
        return formataValor(total());
    }
}
