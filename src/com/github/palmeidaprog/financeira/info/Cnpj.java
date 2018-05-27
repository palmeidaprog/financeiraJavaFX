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

import java.io.Serializable;
import java.util.Objects;

public class Cnpj extends InscricaoFiscal implements Serializable {
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
            throw new InscricaoInvalidaException("CNPJ: " + this.formatado());
        }
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public String formatado() {
        if(CNPJ.length() != 14) {
            return CNPJ;
        } else {
            return CNPJ.substring(0, 2) + "." + CNPJ.substring(2, 5) + "." +
                    CNPJ.substring(5, 8) + "/" + CNPJ.substring(8, 12) + "-" +
                    CNPJ.substring(12, 14);
        }
    }

    //--Object override-------------------------------------------------------

    @Override
    public String toString() {
        return "Cnpj{" +
                "CNPJ='" + CNPJ + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if (!(o instanceof Cnpj)) {
            return false;
        } else {
            Cnpj cnpj = (Cnpj) o;
            return super.equals(o) && CNPJ.equals(cnpj.CNPJ);
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode() + CNPJ.hashCode();
    }
}
