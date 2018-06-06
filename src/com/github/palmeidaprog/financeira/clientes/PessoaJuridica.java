package com.github.palmeidaprog.financeira.clientes;

/*
 * UNICAP - Univesidade Catolica de Pernambuco
 * @author Aluno: Paulo R. Almeida Filho
 * http://www.github.com/palmeidaprog/financeira
 * @email pauloalmeidaf@gmail.com
 * @email palmeidaprogramming@gmail.com
 * Professor: Antonio Canvalcanti
 */

import com.github.palmeidaprog.financeira.info.Cnpj;
import com.github.palmeidaprog.financeira.info.Endereco;
import com.github.palmeidaprog.financeira.info.telefone.Telefone;
import com.github.palmeidaprog.financeira.interfaces.observador.TipoEvento;

import java.io.Serializable;
import java.util.Objects;

public class PessoaJuridica extends Cliente implements Serializable {
    private String razaoSocial;
    private String nomeFantasia;
    private final Cnpj cnpj;

    public PessoaJuridica(Endereco endereco, Telefone telefone, Cadastro
            cadastro, String razaoSocial, String nomeFantasia, Cnpj cnpj) {
        super(endereco, telefone, cadastro);
        construct(razaoSocial, nomeFantasia);
        this.cnpj = cnpj;
    }

    // deserializaçao
    public PessoaJuridica(Endereco endereco, Telefone telefone, Cadastro
            cadastro, String comentario, String razaoSocial, String
            nomeFantasia, Cnpj cnpj) {
        super(endereco, telefone, cadastro, comentario);
        construct(razaoSocial, nomeFantasia);
        this.cnpj = cnpj;
    }

    private void construct(String razaoSocial, String nomeFantasia) {
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
        notificarEvento(this.razaoSocial, TipoEvento.EDITADO);
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
        notificarEvento(this.nomeFantasia, TipoEvento.EDITADO);
    }

    public Cnpj getCnpj() {
        return cnpj;
    }

    //--Object override-------------------------------------------------------

    @Override
    public String toString() {
        return "PessoaJuridica{" +
                "razaoSocial='" + razaoSocial + '\'' +
                ", nomeFantasia='" + nomeFantasia + '\'' +
                ", cnpj=" + cnpj +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(!(o instanceof PessoaFisica)) {
            return false;
        } else {
            PessoaJuridica that = (PessoaJuridica) o;
            return super.equals(o) && razaoSocial.equals(that.razaoSocial) &&
                    nomeFantasia.equals(that.nomeFantasia) &&
                    cnpj.equals(that.cnpj);
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode() + razaoSocial.hashCode() +
                nomeFantasia.hashCode() + cnpj.hashCode();
    }
}
