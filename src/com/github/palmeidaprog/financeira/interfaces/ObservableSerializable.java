package com.github.palmeidaprog.financeira.interfaces;

import java.util.Observable;

public abstract class ObservableSerializable extends Observable {

    protected void notifyChange(Object obj) {
        setChanged();
        notifyObservers(obj);
    }
}
