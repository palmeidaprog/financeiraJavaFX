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

import com.github.palmeidaprog.financeira.info.Endereco;
import com.github.palmeidaprog.financeira.info.telefone.Telefone;
import com.github.palmeidaprog.financeira.info.telefone.TelefoneController;

import java.io.Serializable;

public class Imovel extends Bem implements Serializable {
    private String descricao;
    private Endereco endereco;
    private final TelefoneController telefones;

    // deserialização
    public Imovel(double valor, String descricao, PendenciaController
            pendencias, String descricao1, Endereco endereco,
                  TelefoneController telefones) {
        super(valor, descricao, pendencias);
        this.descricao = descricao1;
        this.endereco = endereco;
        this.telefones = telefones;
    }

    public Imovel(double valor, String descricao, Endereco endereco, TelefoneController telefones) {
        super(valor);
        this.descricao = descricao;
        this.endereco = endereco;
        this.telefones = telefones;
    }

    public Imovel(Endereco endereco, double valor) {
        super(valor);
        this.endereco = endereco;
        telefones = new TelefoneController();
    }

    public Imovel(Endereco endereco, double valor, String descricao) {
        this(endereco, valor);
        this.descricao = descricao;
    }

    public Imovel(Endereco endereco , Telefone telefone, double valor) {
        this(endereco, valor);
        telefones.inserir(telefone);
    }

    public Imovel(Endereco endereco , Telefone telefone, double valor, String
            descricao) {
        this(endereco, valor, descricao);
        telefones.inserir(telefone);
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public TelefoneController getTelefones() {
        return telefones;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Imovel{" +
                "descricao='" + descricao + '\'' +
                ", endereco=" + endereco +
                ", telefones=" + telefones +
                '}';
    }
}
