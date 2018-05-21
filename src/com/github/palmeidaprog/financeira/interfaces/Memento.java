package com.github.palmeidaprog.financeira.interfaces;

import java.io.IOException;

//@design memento
public interface Memento {
    void getState() throws IOException; // carrega load
    void setState() throws IOException; // salva
}
