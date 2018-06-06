package com.github.palmeidaprog.financeira.interfaces.observador;

import java.util.ArrayDeque;
import java.util.Deque;

public class EventoObservado {
    private final Object objetoModificado;
    private final Deque<Observado> observados = new ArrayDeque<>();
    private final TipoEvento tipo;

    public EventoObservado(Observado obs, Object objetoModificado,
                           TipoEvento tipo) {
        this.objetoModificado = objetoModificado;
        this.tipo = tipo;
        observados.add(obs);
    }

    public void adicionaObservado(Observado o) {
        observados.addFirst(o);
    }

    public Object getObjetoModificado() {
        return objetoModificado;
    }

    public Deque<Observado> getObservados() {
        return observados;
    }
}


