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

import java.util.ArrayList;
import java.util.List;

public class TelefoneController {
    private final List<Telefone> telefones;
    private Telefone principal;

    public TelefoneController() {
        telefones = new ArrayList<>();
    }

    public TelefoneController(Telefone telefone) {
        this();
        telefones.add(telefone);
    }

    public TelefoneController(Telefone telefone, boolean isPrincipal) {
        this(telefone);
        this.principal = telefone;
    }

    public void inserir(Telefone telefone) {
        telefones.add(telefone);
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
}
