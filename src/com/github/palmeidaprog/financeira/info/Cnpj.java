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

import com.github.palmeidaprog.financeira.exception.InscricaoInvalidaException;

public class Cnpj extends InscricaoFiscal {
    private final String CNPJ;

    // construtor par procura com CNPJ
    public Cnpj(String cnpj) throws InscricaoInvalidaException {
        super();
        this.CNPJ = cnpj;
        valida();
    }

    public Cnpj(String cnpj, String orgaoExpedidor, Estado estado) throws
            InscricaoInvalidaException {
        super(orgaoExpedidor, estado);
        CNPJ = cnpj;
        valida();
    }

    // TODO: validacao de CNPJ
    private void valida() throws InscricaoInvalidaException {
        if(CNPJ.equals("")) {
            throw new InscricaoInvalidaException("CNPJ: " + this);
        }
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public boolean equals(Cnpj cnpj) {
        return (cnpj != null && this.CNPJ.equals(cnpj.getCNPJ()));
    }

    @Override
    public String toString() {
        if(CNPJ.length() != 14) {
            return CNPJ;
        } else {
            return CNPJ.substring(0, 2) + "." + CNPJ.substring(2, 5) + "." +
                    CNPJ.substring(5, 8) + "/" + CNPJ.substring(8, 12) + "-" +
                    CNPJ.substring(12, 14);
        }
    }
}
