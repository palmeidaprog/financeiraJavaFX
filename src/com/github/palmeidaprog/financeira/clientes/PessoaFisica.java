package com.github.palmeidaprog.financeira.clientes;

/*
 * UNICAP - Univesidade Catolica de Pernambuco
 * @author Aluno: Paulo R. Almeida Filho
 * http://www.github.com/palmeidaprog/financeira
 * @email pauloalmeidaf@gmail.com
 * @email palmeidaprogramming@gmail.com
 * Professor: Antonio Canvalcanti
 */

import com.github.palmeidaprog.financeira.info.Cpf;
import com.github.palmeidaprog.financeira.info.Endereco;
import com.github.palmeidaprog.financeira.info.telefone.Telefone;

import java.io.Serializable;
import java.util.Objects;

public class PessoaFisica extends Cliente implements Serializable {
    private String primeiroNome;
    private String nomeDoMeio;
    private String sobrenome;
    private final Cpf cpf;

    public PessoaFisica(Endereco endereco, Telefone telefone, Cadastro
            cadastro, String primeiroNome, String nomeDoMeio, String sobrenome
            , Cpf cpf) {
        super(endereco, telefone, cadastro);
        construct(primeiroNome, nomeDoMeio, sobrenome);
        this.cpf = cpf;
    }

    // deserializaçao
    public PessoaFisica(Endereco endereco, Telefone telefone, String
            comentario, Cadastro cadastro, String primeiroNome, String
            nomeDoMeio, String sobrenome, Cpf cpf) {
        super(endereco, telefone, cadastro, comentario);
        construct(primeiroNome, nomeDoMeio, sobrenome);
        this.cpf = cpf;
    }

    // deserializaçao
    public PessoaFisica(Endereco endereco, Telefone telefone, String
            comentario, Cadastro cadastro, String primeiroNome, String
            sobrenome, Cpf cpf) {
        super(endereco, telefone, cadastro, comentario);
        this.primeiroNome = primeiroNome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
    }

    private void construct(String primeiroNome, String nomeDoMeio, String
            sobrenome) {
        this.primeiroNome = primeiroNome;
        this.nomeDoMeio = nomeDoMeio;
        this.sobrenome = sobrenome;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
        notificarEvento(this.primeiroNome);
    }

    public String getNomeDoMeio() {
        return nomeDoMeio;
    }

    public void setNomeDoMeio(String nomeDoMeio) {
        this.nomeDoMeio = nomeDoMeio;
        notificarEvento(this.nomeDoMeio);
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
        notificarEvento(this.sobrenome);
    }

    public Cpf getCpf() {
        return cpf;
    }

    //--Object override-------------------------------------------------------

    @Override
    public String toString() {
        return "PessoaFisica{" +
                "primeiroNome='" + primeiroNome + '\'' +
                ", nomeDoMeio='" + nomeDoMeio + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", cpf=" + cpf +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(!(o instanceof PessoaFisica)) {
            return false;
        } else {
            PessoaFisica that = (PessoaFisica) o;
            return super.equals(o) &&
                    primeiroNome.equals(that.primeiroNome) &&
                    nomeDoMeio.equals(that.nomeDoMeio) &&
                    sobrenome.equals(that.sobrenome) &&
                    cpf.equals(that.cpf);
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode() + primeiroNome.hashCode() +
                nomeDoMeio.hashCode() + sobrenome.hashCode() + cpf.hashCode();
    }
}
