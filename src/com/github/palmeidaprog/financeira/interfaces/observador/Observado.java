package com.github.palmeidaprog.financeira.interfaces.observador;

import java.util.Collection;
import java.util.Vector;

public abstract class Observado {
    private Vector<Observador> obs = new Vector<>();


    public synchronized void adicionaObservador(Observador o) {
        obs.add(o);
    }

    public synchronized void deletaObservador(Observador o) {
        obs.remove(o);
    }

    public int numeroDeObservadores() {
        return obs.size();
    }

    public synchronized void deletaTodosObservadores() {
        obs.clear();
    }

    // notificação do evento ocorrido aos observadores
    protected void notificarEvento(Object obj, TipoEvento tipo) {
        EventoObservado evento = new EventoObservado(this, obj, tipo);
        notificaObserservadores(evento);
    }

    // repasse de notificação entre observadores que contem o observador
    // original
    protected  void notificarEvento(EventoObservado evento) {
        evento.adicionaObservado(this);
        notificaObserservadores(evento);
    }

    // suporte para notificarEvento
    private void notificaObserservadores(EventoObservado evento) {
        for(int i = 0; i < obs.size(); i++) {
            obs.get(i).atualizar(evento);
        }
    }

    // metodo que adiciona um Observador a todos elementos observaveis de uma
    // colecão (observa cada elemento individualmente, não observa a colecao)
    public <T extends Observado> void observarElementos(
            Observador o, Collection<T> c) {
        for(Observado elem : c) {
            elem.adicionaObservador(o);
        }
    }
}
