package com.github.palmeidaprog.financeira.interfaces.observador;

public class FactoryEvento {
    private static int tipo = 1;

    public static void setTipo(int pTipo) {
        tipo = pTipo;
    }

    public static EventoObs getInstance(Observado o, Object obj, Enum tipo) {
        return new EventoObservado(o, obj, (TipoEvento) tipo);
    }
}
