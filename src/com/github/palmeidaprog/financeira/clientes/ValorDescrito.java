package com.github.palmeidaprog.financeira.clientes;

/*
 * UNICAP - Univesidade Catolica de Pernambuco
 * @author Aluno: Paulo R. Almeida Filho
 * http://www.github.com/palmeidaprog/financeira
 * @email pauloalmeidaf@gmail.com
 * @email palmeidaprogramming@gmail.com
 * Professor: Antonio Canvalcanti
 */


import javafx.beans.property.StringProperty;

public interface ValorDescrito { // @design BRIDGE
    double getValor();
    void setValor(double valor);
    String valorFormatado(double valor);
    void setDescricao(String descricao);
    String getDescricao();
    StringProperty valorPProperty();
    StringProperty descricaoPProperty();
}
