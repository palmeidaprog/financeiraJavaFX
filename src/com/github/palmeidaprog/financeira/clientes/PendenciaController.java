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
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PendenciaController implements Serializable {
    private final List<Pendencia> pendencias;

    // desserialização
    public PendenciaController(List<Pendencia> pendencias) {
        this.pendencias = pendencias;
    }

    public PendenciaController() {
        pendencias = new ArrayList<>();
    }

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

    @Override
    public String toString() {
        return "PendenciaController{" +
                "pendencias=" + pendencias +
                '}';
    }
}
