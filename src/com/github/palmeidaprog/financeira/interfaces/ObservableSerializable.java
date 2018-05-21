package com.github.palmeidaprog.financeira.interfaces;

import java.util.Observable;

public abstract class ObservableSerializable extends Observable {

    public void notifyChange() {
        setChanged();
        notifyObservers(this);
    }
}
