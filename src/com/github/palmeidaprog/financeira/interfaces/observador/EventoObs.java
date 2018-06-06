package com.github.palmeidaprog.financeira.interfaces.observador;

import java.util.Deque;

public interface EventoObs {

    void adiciona(Observado o);
    Object getModificado();
    Deque<Observado> getObservados();
    Enum getTipo();
}
