package com.github.palmeidaprog.financeira.info;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import com.github.palmeidaprog.financeira.exception.InscricaoInvalidaException;

/*
 * Financeira App
 * UNICAP - Univesidade Catolica de Pernambuco
 * @author Aluno: Paulo R. Almeida Filho
 * http://www.github.com/palmeidaprog/financeira
 * @email pauloalmeidaf@gmail.com
 * @email palmeidaprogramming@gmail.com
 * Professor: Antonio Canvalcanti
 */

public class Cpf extends InscricaoFiscal implements Serializable {
    private final String CPF;

    // construtor para procura com CPF
    public Cpf(String cpf) throws InscricaoInvalidaException {
        super();
        this.CPF = cpf;
        valida();
    }

    public Cpf(String cpf, String orgaoExpedidor, Estado estado) throws
            InscricaoInvalidaException {
        super(orgaoExpedidor, estado);
        this.CPF = cpf;
        valida();
    }

    private void valida() throws InscricaoInvalidaException {
        int primeiroDigito = 0, segundoDigito = 0, multiplicador = 11;
        List<String> cpfInvalidos = Arrays.asList("00000000000", "11111111111", "22222222222",
                "33333333333", "44444444444", "55555555555", "66666666666",
                "77777777777", "88888888888", "99999999999");

        if(CPF.length() != 11) {
            throw new InscricaoInvalidaException("CPF Invalido: " + this
                    .formatado());
        }
        // verifica se todos digitos sao iguais

        for(String i : cpfInvalidos) {
            if(i.equals(CPF)) {
                throw new InscricaoInvalidaException("CPF Invalido: " + this
                    .formatado());
            }
        }

        for(int i = 0; i < CPF.length(); i++) {
            if(i <= 8) {
                primeiroDigito += (CPF.charAt(i) - '0') * (multiplicador - 1);
            }

            if(i <= 9) {
                segundoDigito += (CPF.charAt(i) - '0') * multiplicador;
            }

            if(CPF.charAt(i) < 48 || CPF.charAt(i) > 57) { // contem nao numeros
                throw new InscricaoInvalidaException("CPF Invalido: " + this
                    .formatado());
            }
            --multiplicador;
        }
        primeiroDigito = restoCPF(primeiroDigito);
        segundoDigito = restoCPF(segundoDigito);

        if(primeiroDigito != (CPF.charAt(9) - '0') || segundoDigito != (CPF
                .charAt(10) - '0')) {
            throw new InscricaoInvalidaException("CPF Invalido: " + this
                    .formatado());
        }
    }

    // função suporte de validaCPF()
    private int restoCPF(int x) {
        x = x * 10 % 11;
        return (x == 10) ? 0 : x;
    }

    public String getCPF() {
        return CPF;
    }


    public boolean equals(Cpf cpf) {
        return (cpf != null && this.CPF.equals(cpf.getCPF()));
    }

    public String formatado() {
        if(CPF.length() != 11) {
            return CPF;
        } else {
            return CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." + CPF
                    .substring(6, 9) + "-" + CPF.substring(9, 11);
        }
    }

    @Override
    public String toString() {
        return "Cpf{" +
                "CPF='" + CPF + '\'' +
                '}';
    }
}
