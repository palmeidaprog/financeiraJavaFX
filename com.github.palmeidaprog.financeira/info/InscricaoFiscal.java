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


public class InscricaoFiscal {
    private String orgaoExpedidor;
    private Estado estado;

    public InscricaoFiscal(String orgaoExpedidor, Estado estado) {
        this.orgaoExpedidor = orgaoExpedidor;
        this.estado = estado;
    }

    public void setOrgaoExpedidor(String orgaoExpedidor) {
        this.orgaoExpedidor = orgaoExpedidor;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Estado getEstado() {
        return estado;
    }

    public String getOrgaoExpedidor() {
        return orgaoExpedidor;
    }
}
