package com.github.palmeidaprog.financeira.clientes;

/*
 * UNICAP - Univesidade Catolica de Pernambuco
 * @author Aluno: Paulo R. Almeida Filho
 * http://www.github.com/palmeidaprog/financeira
 * @email pauloalmeidaf@gmail.com
 * @email palmeidaprogramming@gmail.com
 * Professor: Antonio Canvalcanti
 */

import com.github.palmeidaprog.financeira.info.Endereco;
import com.github.palmeidaprog.financeira.info.EnderecoController;
import com.github.palmeidaprog.financeira.info.telefone.Telefone;
import com.github.palmeidaprog.financeira.info.telefone.TelefoneController;
import com.github.palmeidaprog.financeira.interfaces.observador.Observado;

import java.io.Serializable;
import java.util.Observable;


public abstract class Cliente extends Observado implements
        Serializable, Observador {
    private final EnderecoController enderecos;
    private final TelefoneController telefones;
    private String comentario;
    private final Cadastro cadastro;

    protected Cliente(Endereco endereco, Telefone telefone, Cadastro
            cadastro) {
        enderecos = new EnderecoController(endereco);
        enderecos.adicionaObservador(this);
        telefones = new TelefoneController(telefone);
        telefones.adicionaObservador(this);
        this.cadastro = cadastro;
        this.cadastro.adicionaObservador(this);
    }

    // usado tambem para deserializacao
    public Cliente(Endereco endereco, Telefone telefone, Cadastro cadastro,
                   String comentario) {
        this(endereco, telefone, cadastro);
        this.comentario = comentario;
    }

    public EnderecoController getEnderecos() {
        return enderecos;
    }

    public TelefoneController getTelefones() {
        return telefones;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Cadastro getCadastro() {
        return cadastro;
    }

    //--Observador--------------------------------------------------------------

    @Override
    public void atualizar(EventoObservado ev) {
        notificarEvento(o);
    }

    //--Object override-------------------------------------------------------

    @Override
    public String toString() {
        return "Cliente{" +
                "enderecos=" + enderecos +
                ", telefones=" + telefones +
                ", comentario='" + comentario + '\'' +
                ", cadastro=" + cadastro +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(!(o instanceof Cliente)) {
            return false;
        } else {
            Cliente cliente = (Cliente) o;
            return enderecos.equals(cliente.enderecos) &&
                    telefones.equals(cliente.telefones) &&
                    comentario.equals(cliente.comentario) &&
                    cadastro.equals(cliente.cadastro);
        }
    }

    @Override
    public int hashCode() {
        return enderecos.hashCode() + telefones.hashCode() +
                comentario.hashCode() + cadastro.hashCode();
    }
}
