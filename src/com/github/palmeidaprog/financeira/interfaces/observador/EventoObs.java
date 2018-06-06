package com.github.palmeidaprog.financeira.interfaces.observador;

import java.util.Collection;
import java.util.Deque;

public interface EventoObs {

    void adiciona(Observado o);
    Object getModificado();
    Deque<Observado> getObservados();
    <T extends Enum> T getTipo(); // retorna Enum
    Collection<? extends Observado> getLista();
    void setLista(Collection<? extends Observado> lista);
}
