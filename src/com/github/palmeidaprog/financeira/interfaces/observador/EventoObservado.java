package com.github.palmeidaprog.financeira.interfaces.observador;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

public class EventoObservado implements EventoObs {
    private final Object objetoModificado;
    private final Deque<Observado> observados = new ArrayDeque<>();
    private final TipoEvento tipo;
    private final T container;

    public EventoObservado(Observado obs, Object objetoModificado,
                           TipoEvento tipo) {
        this.objetoModificado = objetoModificado;
        this.tipo = tipo;
        observados.add(obs);
    }

    public void adiciona(Observado o) {
        observados.addFirst(o);
    }

    public Enum getTipo() {
        return tipo;
    }

    public Object getModificado() {
        return objetoModificado;
    }



    public Deque<Observado> getObservados() {
        return observados;
    }
}


