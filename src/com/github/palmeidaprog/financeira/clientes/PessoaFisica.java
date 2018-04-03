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

public class PessoaFisica extends Cliente {
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

    public PessoaFisica(Endereco endereco, Telefone telefone, String
            comentario, Cadastro cadastro, String primeiroNome, String
            nomeDoMeio, String sobrenome, Cpf cpf) {
        super(endereco, telefone, cadastro, comentario);
        construct(primeiroNome, nomeDoMeio, sobrenome);
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
    }

    public String getNomeDoMeio() {
        return nomeDoMeio;
    }

    public void setNomeDoMeio(String nomeDoMeio) {
        this.nomeDoMeio = nomeDoMeio;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Cpf getCpf() {
        return cpf;
    }
}
