package com.github.palmeidaprog.financeira.interfaces;

import com.github.palmeidaprog.financeira.clientes.adapter.TabelaValorDescrito;
import com.github.palmeidaprog.financeira.exception.ProcuraSemResultadoException;
import com.github.palmeidaprog.financeira.interfaces.observador.Observado;

import java.security.InvalidParameterException;


public abstract class ValorDescritoController extends Observado {

    public abstract <T extends TabelaValorDescrito> void addTabela(T o);
    public abstract <T extends ValorDescrito> void remover(T p) throws
            InvalidParameterException;
    public abstract <T extends ValorDescrito> void inserir(T p) throws
            InvalidParameterException;
    public abstract <T extends ValorDescrito> int indexOf(T pendencia) throws
            ProcuraSemResultadoException;

}

