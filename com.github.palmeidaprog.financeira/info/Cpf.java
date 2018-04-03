package com.github.palmeidaprog.financeira.info;

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

public class Cpf extends InscricaoFiscal {
    private final String CPF;

    // TODO: implementar construtor
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

        if(CPF.length() == 11) {
            throw new InscricaoInvalidaException("CPF: " + this);
        }
        // verifica se todos digitos sao iguais

        for(String i : cpfInvalidos) {
            if(i.equals(CPF)) {
                throw new InscricaoInvalidaException("CPF: " + this);
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
                throw new InscricaoInvalidaException("CPF: " + this);
            }
            --multiplicador;
        }
        primeiroDigito = restoCPF(primeiroDigito);
        segundoDigito = restoCPF(segundoDigito);

        if(primeiroDigito != (CPF.charAt(9) - '0') || segundoDigito != (CPF.charAt(10) - '0')) {
            throw new InscricaoInvalidaException("CPF: " + this);
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

    @Override
    public String toString() {
        return CPF.substring(0, 2) + "." + CPF.substring(3, 5) + "." + CPF.substring(6, 8) + "-" +
            CPF.substring(9, 10);
    }
}
