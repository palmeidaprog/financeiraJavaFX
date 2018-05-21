package com.github.palmeidaprog.financeira.clientes;

import java.io.IOException;

// @design FactoryClienteDao
public class FactoryClienteDao {
    private static int tipo = 0;

    public static void setTipo(int tipo) {
        FactoryClienteDao.tipo = tipo;
    }

    public static ClienteDao getInstance() throws IOException {
        switch(tipo) {
            default: // Surubão Design Pattern
                return ArquivoClienteDao.getInstance();
        }
    }
}
