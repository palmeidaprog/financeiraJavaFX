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

import com.github.palmeidaprog.financeira.exception.DadoVazioException;

public class Estado {
    private String nome;
    private String sigla;

    public Estado(String nome, String sigla) throws DadoVazioException {
        atributoVazio(nome, "nome");
        atributoVazio(sigla, "sigla");
        this.nome = nome;
        this.sigla = sigla;
    }

    private void atributoVazio(String atributo, String atrNome) throws DadoVazioException {
        if(atributo.trim().isEmpty()) {
            throw new DadoVazioException("Atributo " + atrNome + " vazio");
        }
    }

    public String getSigla() {
        return sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String toString() {
        return getNome() + " (" + getSigla() + ")";
     }
}
