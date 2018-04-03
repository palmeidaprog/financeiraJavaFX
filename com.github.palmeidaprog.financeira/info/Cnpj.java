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

    public Cnpj(String cnpj, String orgaoExpedidor, Estado estado) throws
            InscricaoInvalidaException {
        super(orgaoExpedidor, estado);
        CNPJ = cnpj;
        valida();
    }

    // TODO: validacao de CNPJ
    private void valida() throws InscricaoInvalidaException {
        if(CNPJ.equals("0")) {
            throw new InscricaoInvalidaException("CNPJ: " + this);
        }
    }

    public String getCNPJ() {
        return CNPJ;
    }

    @Override
    public String toString() {
        return CNPJ.substring(0, 1) + "." + CNPJ.substring(2,4) + "." + CNPJ.substring(5, 7) + "/" +
                CNPJ.substring(8, 11) + "-" + CNPJ.substring(12, 13);
    }
}
