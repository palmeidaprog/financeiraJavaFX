package com.github.palmeidaprog.financeira.info.telefone;

/*
 * UNICAP - Univesidade Catolica de Pernambuco
 * @author Aluno: Paulo R. Almeida Filho
 * http://www.github.com/palmeidaprog/financeira
 * @email pauloalmeidaf@gmail.com
 * @email palmeidaprogramming@gmail.com
 * Professor: Antonio Canvalcanti
 */


import com.github.palmeidaprog.financeira.exception.ImpossivelRemoverException;
import com.github.palmeidaprog.financeira.exception.ProcuraSemResultadoException;
import com.github.palmeidaprog.financeira.interfaces.ObservableSerializable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.*;

public class TelefoneController extends ObservableSerializable implements
        Serializable, Observer {
    private List<Telefone> telefonesL;
    private ObservableList<Telefone> telefones;
    private Telefone principal;

    //--Construtores----------------------------------------------------------

    // deserializacao
    public TelefoneController(List<Telefone> telefones, Telefone principal) {
        this.telefonesL = telefones;
        this.principal = principal;
        addObserversToElements(this, telefonesL);
        principal.addObserver(this);
    }

    public TelefoneController() {
        telefonesL = new ArrayList<>();
        telefones = FXCollections.observableList(telefonesL);
    }

    public TelefoneController(Telefone telefone) {
        this();
        principal = telefone;
        telefone.addObserver(this);
        telefones.add(telefone);
    }

    //--Evento observablelist-------------------------------------------------

    private void evento() {
        telefones.addListener(new ListChangeListener<Telefone>() {
            @Override
            public void onChanged(Change<? extends Telefone> c) {
                while(c.next()) {
                    telefones.addAll(c.getAddedSubList());
                    telefones.removeAll(c.getRemoved());
                }
            }
        });
    }

    //------------------------------------------------------------------------

    public void inserir(Telefone telefone) {
        telefones.add(telefone);
    }

    public void inserir(Telefone telefone, boolean isPrincipal) {
        telefones.add(telefone);
        if(isPrincipal) {
            principal = telefone;
        }
    }

    public void remover(Telefone telefone) throws ImpossivelRemoverException {
        if(isRemovivel(telefone)) {
            telefones.remove(telefone);
        }
    }

    public void remover(int index) throws ImpossivelRemoverException {
        remover(get(index));
    }

    public Telefone get(int index) {
        Telefone telefone = telefones.get(index);
        setPrincipal(telefone);
        return telefone;
    }

    public boolean isPrincipal(Telefone telefone) {
        return telefone.equals(principal);
    }

    public Telefone getPrincipal() {
        return principal;
    }

    public void setPrincipal(Telefone principal) {
        this.principal = principal;
    }

    /*
    * @param    codigo  Contem o codigo de Area a ser procurado
    * @return           List<Telefone> Telefones codigo de Area
    */
    public List<Telefone> procurar(CodigoArea codigo) throws
            ProcuraSemResultadoException {
        List<Telefone> resultado = new ArrayList<>();
        for(Telefone p : telefones) {
            if(codigo.getCodigo().equals(p.getCodigoArea().getCodigo())) {
                resultado.add(p);
            }
        }
        if(resultado.isEmpty()) {
            throw new ProcuraSemResultadoException("Não existe número com " +
                    "código de area " + codigo + ".");
        }
        return resultado;
    }

    public Telefone procurar(NumeroTelefone numero) throws
            ProcuraSemResultadoException {
        for(Telefone t: telefones) {
            if(numero.equals(t.getNumero().getNumero())) {
                return t;
            }
        }
        throw new ProcuraSemResultadoException("Nao existe número com " +
                numero + ".");
    }

    public List<Telefone> procurar(TipoTelefone tipo) throws
            ProcuraSemResultadoException {
        List<Telefone> resultado = new ArrayList<>();

        for(Telefone t : telefones) {
            if(t.getTipo().equals(tipo)) {
                resultado.add(t);
            }
        }
        if(resultado.isEmpty()) {
            throw new ProcuraSemResultadoException("Nao existe números com " +
                    tipo + ".");
        }
        return resultado;
    }


    public int indexOf(Telefone telefone) throws ProcuraSemResultadoException
            {
        for(int i = 0; i < telefones.size(); i++) {
            if (telefone.equals(telefones.get(i))) {
                return i;
            }
        }
        throw new ProcuraSemResultadoException("Telefone " + telefone + " não"
                + " incluso.");
    }

    private boolean isRemovivel(Telefone telefone) throws
            ImpossivelRemoverException {
        if(telefones.size() == 1) {
            throw new ImpossivelRemoverException("Não é possivel remover o "
                    + "último Telefone. Adicione um novo antes de removê-lo.");
        } else if(telefone.equals(principal)) {
            throw new ImpossivelRemoverException("Defina um novo principal "
                    + "Telefone antes de remover Endereco " + telefone + ".");
        }
        return true;
    }

    //--Object override-------------------------------------------------------

    @Override
    public String toString() {
        return "TelefoneController{" +
                "telefones=" + telefones +
                ", principal=" + principal +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(!(o instanceof TelefoneController)) {
            return false;
        } else {
            TelefoneController that = (TelefoneController) o;
            return comparaList(telefones, that.telefones) &&
                    principal.equals(that.principal);
        }
    }

    @Override
    public int hashCode() {
        int soma = 0;
        for(Telefone b : telefones) {
            soma += b.hashCode();
        }
        return soma + principal.hashCode();
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

            if(bo instanceof Telefone) {
                Telefone tel = (Telefone) bo;
                if(!tel.equals(lo)) {
                    return false;
                }
            }
        }
        return true;
    }
}

