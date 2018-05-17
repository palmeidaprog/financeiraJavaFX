package com.github.palmeidaprog.financeira.clientes;

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
