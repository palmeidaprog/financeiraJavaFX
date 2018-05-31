package com.github.palmeidaprog.financeira.interfaces;

import com.github.palmeidaprog.financeira.clientes.adapter.TabelaValorDescrito;

import java.security.InvalidParameterException;
import java.util.List;

public abstract class ValorDescritoController extends ObservableSerializable {
    public abstract <T extends TabelaValorDescrito> void addTabela(T o);
    public abstract <T extends ValorDescrito> void remover(T p) throws
            InvalidParameterException;
    public abstract <T extends ValorDescrito> void inserir(T p) throws
            InvalidParameterException;

    public <T extends ValorDescrito> void remover(T p) throws
            InvalidParameterException {

            p.remove(pendencia);
            notifyChange(pendencias);
        } else {
            throw new InvalidParameterException(pendencia.getClass().getName()
                    + " não é um tipo válido!");
        }
    }
}

