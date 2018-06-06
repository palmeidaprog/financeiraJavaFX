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


import com.github.palmeidaprog.financeira.interfaces.observador.Observado;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

public class InscricaoFiscal extends Observado implements
        Serializable, Observer {
    private String orgaoExpedidor;
    private Estado estado;

    // construtor para ser utilizado em procuras com CPF e CNPJ
    public InscricaoFiscal() { }

    public InscricaoFiscal(String orgaoExpedidor, Estado estado) {
        this.orgaoExpedidor = orgaoExpedidor;
        this.estado = estado;
        this.estado.addObserver(this);
    }

    public void setOrgaoExpedidor(String orgaoExpedidor) {
        this.orgaoExpedidor = orgaoExpedidor;
        notifyChange(this);
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
        notifyChange(this);
    }

    public Estado getEstado() {
        return estado;
    }

    public String getOrgaoExpedidor() {
        return orgaoExpedidor;
    }

    //--Observer intereface---------------------------------------------------

    @Override
    public void update(Observable o, Object arg) {
        notifyChange(o);
    }

    //--Object override-------------------------------------------------------

    @Override
    public String toString() {
        return "InscricaoFiscal{" +
                "orgaoExpedidor='" + orgaoExpedidor + '\'' +
                ", estado=" + estado +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(!(o instanceof InscricaoFiscal)) {
            return false;
        } else {
            InscricaoFiscal insc = (InscricaoFiscal) o;
            return orgaoExpedidor.equals(insc.orgaoExpedidor) &&
                    estado.equals(insc.estado);
        }
    }

    @Override
    public int hashCode() {
        return orgaoExpedidor.hashCode() + estado.hashCode();
    }
}
