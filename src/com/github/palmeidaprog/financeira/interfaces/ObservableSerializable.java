package com.github.palmeidaprog.financeira.interfaces;

import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

public abstract class ObservableSerializable extends Observable {

    protected void notifyChange(Object obj) {
        setChanged();
        notifyObservers(obj);
    }

    public <T extends Observable> void addObserversToElements(
            Observer o, Collection<T> c) {
        for(Observable elem : c) {
            elem.addObserver(o);
        }
    }
}
