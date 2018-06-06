package com.github.palmeidaprog.financeira.interfaces.observador;

import java.util.*;

public class EventoObservado implements EventoObs {
    private final Object objetoModificado;
    private final Deque<Observado> observados = new ArrayDeque<>();
    private final TipoEvento tipo;
    private Collection<? extends Observado> lista;

    public EventoObservado(Observado obs, Object objetoModificado,
                           TipoEvento tipo) {
        this.objetoModificado = objetoModificado;
        this.tipo = tipo;
        observados.add(obs);
    }

    public void adiciona(Observado o) {
        observados.addFirst(o);
    }

    @SuppressWarnings("unchecked") // seguro
    public <T extends Enum> T getTipo() {
        return (T) tipo;
    }

    public Object getModificado() {
        return objetoModificado;
    }

    public Collection<? extends Observado> getLista() {
        return lista;
    }

    public void setLista(Collection<? extends Observado> lista) {
        this.lista = lista;
    }

    public Deque<Observado> getObservados() {
        return observados;
    }
}


