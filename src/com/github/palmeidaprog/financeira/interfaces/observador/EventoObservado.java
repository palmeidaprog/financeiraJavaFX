package com.github.palmeidaprog.financeira.interfaces.observador;

import java.util.Deque;

public class EventoObservado {
    private Object origem;
    private Deque<Object> objects;
    private

    public EventoObservado(Object origem) {
        this.origem = origem;
        objects.addFirst(origem);
    }



}


