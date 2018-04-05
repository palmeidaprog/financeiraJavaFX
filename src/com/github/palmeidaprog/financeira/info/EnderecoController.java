package com.github.palmeidaprog.financeira.info;

/*
 * Financeira App
 * UNICAP - Univesidade Catolica de Pernambuco
 * @author Aluno: Paulo R. Almeida Filho
 * http://www.github.com/palmeidaprog/financeira
 * @email pauloalmeidaf@gmail.com
 * @email palmeidaprogramming@gmail.com
 * Professor: Antonio Canvalcanti
 */

import com.github.palmeidaprog.financeira.exception.ImpossivelRemoverException;
import com.github.palmeidaprog.financeira.exception.ProcuraSemResultadoException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EnderecoController implements Serializable {
    private final List<Endereco> enderecos;
    private Endereco principal;

    public EnderecoController(Endereco endereco) {
        enderecos = new ArrayList<>();
        principal = endereco;
        enderecos.add(endereco);
    }

    public EnderecoController(List<Endereco> enderecos, Endereco principal) {
        this.enderecos = enderecos;
        this.principal = principal;
    }

    public void inserir(Endereco endereco) {
        enderecos.add(endereco);
    }

    public void inserir(Endereco endereco, boolean isPrincipal) {
        enderecos.add(endereco);
        if(isPrincipal) {
            principal = endereco;
        }
    }

    public void remover(Endereco endereco) throws ImpossivelRemoverException {
        if(isRemovivel(endereco)) {
            enderecos.remove(endereco);
        }
    }

    public void remover(int index) throws ImpossivelRemoverException {
        Endereco endereco = get(index);
        remover(endereco);
    }

    public Endereco get(int index) {
        return enderecos.get(index);
    }

    public boolean isPrincipal(Endereco endereco) {
        return endereco.equals(principal);
    }

    public void setPrincipal(Endereco endereco) {
        principal = endereco;
    }

    public Endereco getPrincipal() {
        return principal;
    }

    private boolean isRemovivel(Endereco endereco) throws
            ImpossivelRemoverException {
        if(enderecos.size() == 1) {
            throw new ImpossivelRemoverException("Não é possivel remover o "
                    + "último Endereco. Adicione um novo antes de removê-lo.");
        } else if(endereco.equals(principal)) {
            throw new ImpossivelRemoverException("Defina um novo principal "
                    + "Endereco antes de remover Endereco " + endereco + ".");
        }
        return true;
    }

    public int indexOf(Endereco endereco) throws
            ProcuraSemResultadoException {
        for(int i = 0; i < enderecos.size(); i++) {
            if(endereco.equals(enderecos.get(i))) {
                return 1;
            }
        }
        throw new ProcuraSemResultadoException("Não existe endereco " +
            endereco + "na lista.");
    }

    public List<Endereco> procurar(String ruaOuComplemento) throws
            ProcuraSemResultadoException {
        List<Endereco> resultado = new ArrayList<>();
        for(Endereco e : enderecos) {
            if(e.getRua().equals(ruaOuComplemento) || e.getComplemento()
                    .equals(ruaOuComplemento)) {
                resultado.add(e);
            }
        }
        if(resultado.isEmpty()) {
            throw new ProcuraSemResultadoException("Não existe endereco com "
                    + ruaOuComplemento + ".");
        }
        return resultado;
    }

    public List<Endereco> procurar(Bairro bairro) throws
            ProcuraSemResultadoException {
        List<Endereco> resultado = new ArrayList<>();
        for(Endereco e : enderecos) {
            if(e.getBairro().equals(bairro)) {
                resultado.add(e);
            }
        }
        if(resultado.isEmpty()) {
            throw new ProcuraSemResultadoException("Não existe endereco com "
                    + bairro + ".");
        }
        return resultado;
    }

    public List<Endereco> procurar(Cidade cidade) throws
            ProcuraSemResultadoException {
        List<Endereco> resultado = new ArrayList<>();
        for(Endereco e : enderecos) {
            if(e.getCidade().equals(cidade)) {
                resultado.add(e);
            }
        }
        if(resultado.isEmpty()) {
            throw new ProcuraSemResultadoException("Não existe endereco com "
                    + cidade + ".");
        }
        return resultado;
    }

    public List<Endereco> procurar(Cep cep) throws
            ProcuraSemResultadoException {
        List<Endereco> resultado = new ArrayList<>();
        for(Endereco e : enderecos) {
            if(e.getCep().equals(cep)) {
                resultado.add(e);
            }
        }
        if(resultado.isEmpty()) {
            throw new ProcuraSemResultadoException("Não existe endereco com "
                    + cep + ".");
        }
        return resultado;
    }

    public List<Endereco> procurar(TipoEndereco tipo) throws
            ProcuraSemResultadoException {
        List<Endereco> resultado = new ArrayList<>();
        for(Endereco e : enderecos) {
            if(e.getTipo() == tipo) {
                resultado.add(e);
            }
        }
        if(resultado.isEmpty()) {
            throw new ProcuraSemResultadoException("Não existe endereco com "
                    + tipo + ".");
        }
        return resultado;
    }

    public List<Endereco> procurar(Pais pais) throws
            ProcuraSemResultadoException {
        List<Endereco> resultado = new ArrayList<>();
        for(Endereco e : enderecos) {
            if(e.getPais() == pais) {
                resultado.add(e);
            }
        }
        if(resultado.isEmpty()) {
            throw new ProcuraSemResultadoException("Não existe endereco com "
                    + pais + ".");
        }
        return resultado;
    }

    @Override
    public String toString() {
        return "EnderecoController{" +
                "enderecos=" + enderecos +
                ", principal=" + principal +
                '}';
    }
}
