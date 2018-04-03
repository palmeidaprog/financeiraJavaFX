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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class BemController {
    public List<Bem> bens;

    public BemController() {
        bens = new ArrayList<>();
    }

    public BemController(Bem bem) {
        this();
        inserir(bem);
    }

    public BemController(List<Bem> bens) {
        this();
        inserir(bens);
    }


    public void inserir(Bem bem) {
        bens.add(bem);
    }

    public void inserir(List<Bem> bens) {
        this.bens.addAll(bens);
    }

    public void remover(Bem bem) {
        bens.remove(bem);
    }

    public void remover(int index) {
        bens.remove(index);
    }

    public Bem get(int index) {
        return bens.get(index);
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
}
