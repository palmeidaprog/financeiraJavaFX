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
import java.util.Objects;

public class Imovel extends Bem implements Serializable {
    private String descricao;
    private final Endereco endereco;
    private final TelefoneController telefones;

    // deserialização
    public Imovel(double valor, String descricao, PendenciaController
            pendencias, String descricao1, Endereco endereco,
                  TelefoneController telefones) {
        super(valor, descricao, pendencias);
        this.descricao = descricao1;
        this.endereco = endereco;
        this.endereco.adicionaObservador(this);
        this.telefones = telefones;
        this.telefones.adicionaObservador(this);
    }

    public Imovel(double valor, String descricao, Endereco endereco,
                  TelefoneController telefones) {
        super(valor);
        this.descricao = descricao;
        this.endereco = endereco;
        this.endereco.adicionaObservador(this);
        this.telefones = telefones;
        this.telefones.adicionaObservador(this);
    }

    public Imovel(Endereco endereco, double valor) {
        super(valor);
        this.endereco = endereco;
        this.endereco.adicionaObservador(this);
        telefones = new TelefoneController();
        telefones.adicionaObservador(this);
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
        notifyChange(descricao);
    }

    //--Object override-------------------------------------------------------

    @Override
    public String toString() {
        return "Imovel{" +
                "descricao='" + descricao + '\'' +
                ", endereco=" + endereco +
                ", telefones=" + telefones +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(!(o instanceof Imovel)) {
            return false;
        } else {
            Imovel imovel = (Imovel) o;
            return super.equals(o) && descricao.equals(imovel.descricao) &&
                    endereco.equals(imovel.endereco) &&
                    telefones.equals(imovel.telefones);
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode() + descricao.hashCode() + endereco.hashCode() +
                telefones.hashCode();
    }
}
