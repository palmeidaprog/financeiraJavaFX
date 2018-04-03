package com.github.palmeidaprog.financeira.clientes;

/*
 * UNICAP - Univesidade Catolica de Pernambuco
 * @author Aluno: Paulo R. Almeida Filho
 * http://www.github.com/palmeidaprog/financeira
 * @email pauloalmeidaf@gmail.com
 * @email palmeidaprogramming@gmail.com
 * Professor: Antonio Canvalcanti
 */

public class Credito {
    private double financiamento;
    private double pessoal;

    public Credito() {
        financiamento = 0;
        pessoal = 0;
    }

    public Credito(double financiamento, double pessoal) {
        this.financiamento = financiamento;
        this.pessoal = pessoal;
    }

    public double getFinanciamento() {
        return financiamento;
    }

    public void setFinanciamento(double financiamento) {
        this.financiamento = financiamento;
    }

    public double getPessoal() {
        return pessoal;
    }

    public void setPessoal(double pessoal) {
        this.pessoal = pessoal;
    }
}
