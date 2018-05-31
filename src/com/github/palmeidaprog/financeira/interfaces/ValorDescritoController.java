package com.github.palmeidaprog.financeira.interfaces;

import com.github.palmeidaprog.financeira.clientes.adapter.TabelaValorDescrito;

import java.util.List;

public abstract class ValorDescritoController extends ObservableSerializable {
    public abstract <T extends TabelaValorDescrito> void addTabela(T o);
}

