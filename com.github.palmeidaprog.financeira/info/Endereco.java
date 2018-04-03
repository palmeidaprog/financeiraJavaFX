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

public class Endereco {
    private String rua;
    private String numero;
    private Pais pais;
    private Estado estado;
    private Cidade cidade;
    private Bairro bairro;
    private Cep cep;
    private TipoEndereco tipo;
    //opcional
    private String complemento;
    private String referencia;
    private boolean ehPrincipal; // se nao especificado = false
    // construtor basico
    public Endereco(String rua, String numero, Pais pais, Estado estado, Cidade cidade,
                        Bairro bairro, Cep cep, TipoEndereco tipo) throws DadoVazioException {
        atributoVazio(rua, "rua");
        atributoVazio(numero, "numero");
        this.rua = rua;
        this.numero = numero;
        this.pais = pais;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.cep = cep;
        this.tipo = tipo;
    }

    // const c/ complemento
    public Endereco(String rua, String numero, Pais pais, Estado estado, Cidade cidade,
                    Bairro bairro, Cep cep, TipoEndereco tipo, String complemento) throws
                    DadoVazioException {
        this(rua, numero, pais, estado, cidade, bairro, cep, tipo);
        atributoVazio(complemento, "complemento");
        this.complemento = complemento;
    }

    // constr. como endereço principal
    public Endereco(String rua, String numero, Pais pais, Estado estado, Cidade cidade,
                    Bairro bairro, Cep cep, TipoEndereco tipo, boolean ehPrincipal) throws
                    DadoVazioException {
        this(rua, numero, pais, estado, cidade, bairro, cep, tipo);
        this.ehPrincipal = ehPrincipal;
    }

    public Endereco(String rua, String numero, Pais pais, Estado estado, Cidade cidade,
                    Bairro bairro, Cep cep, TipoEndereco tipo, String complemento,
                    boolean ehPrincipal) throws DadoVazioException {
        this(rua, numero, pais, estado, cidade, bairro, cep, tipo, complemento);
        this.ehPrincipal = ehPrincipal;
    }

    private void atributoVazio(String atributo, String atrNome) throws DadoVazioException {
        if(atributo.trim().isEmpty()) {
            throw new DadoVazioException("Atributo " + atrNome + " vazio");
        }
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public Cep getCep() {
        return cep;
    }

    public void setCep(Cep cep) {
        this.cep = cep;
    }

    public TipoEndereco getTipo() {
        return tipo;
    }

    public void setTipo(TipoEndereco tipo) {
        this.tipo = tipo;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public boolean isEhPrincipal() {
        return ehPrincipal;
    }

    public void setEhPrincipal(boolean ehPrincipal) {
        this.ehPrincipal = ehPrincipal;
    }
}
