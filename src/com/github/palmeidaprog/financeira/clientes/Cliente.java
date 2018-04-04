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
import java.io.Serializable;

public class Cliente implements Serializable {
    private final EnderecoController enderecos;
    private final TelefoneController telefones;
    private String comentario;
    private final Cadastro cadastro;

    protected Cliente(Endereco endereco, Telefone telefone, Cadastro cadastro) {
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

    public TelefoneController getTelefones() {
        return telefones;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

}
