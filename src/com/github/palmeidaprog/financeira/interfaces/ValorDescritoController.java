package com.github.palmeidaprog.financeira.interfaces;

import javafx.collections.ObservableList;

import java.util.Collection;

public abstract class ValorDescritoController extends ObservableSerializable {
    public abstract <T> ObservableList<T> getLista();
}
