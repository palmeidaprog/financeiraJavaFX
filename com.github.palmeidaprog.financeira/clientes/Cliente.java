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

public class Cliente {
    private EnderecoController enderecos;
    private TelefoneController telefones;
    private String comentario;
    private final Cadastro cadastro;

    public Cliente(Endereco endereco, Telefone telefone, Cadastro cadastro) {
        enderecos = new EnderecoController(endereco);
        telefones = new TelefoneController(telefone);
        this.cadastro = cadastro;
    }

    public Cliente(Endereco endereco, Telefone telefone, Cadastro cadastro,
                   String comentario) {
        this(endereco, telefone, cadastro);
        this.comentario = comentario;
    }

    public EnderecoController getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(EnderecoController enderecos) {
        this.enderecos = enderecos;
    }

    public TelefoneController getTelefones() {
        return telefones;
    }

    public void setTelefones(TelefoneController telefones) {
        this.telefones = telefones;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
