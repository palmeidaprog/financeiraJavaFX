package com.github.palmeidaprog.financeira.clientes;

import com.github.palmeidaprog.financeira.exception.ProcuraSemResultadoException;
import com.github.palmeidaprog.financeira.info.Cnpj;
import com.github.palmeidaprog.financeira.info.Cpf;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

//@design Bridge that implements Observador
public abstract class ClienteDao implements Observador {
    private ObservableList<Cliente> obs;

    abstract void inserir(Cliente cliente) throws IOException;
    abstract void remover(Cliente cliente) throws IOException;
    abstract PessoaFisica procurar(Cpf cpf) throws
            ProcuraSemResultadoException;
    abstract PessoaJuridica procurar(Cnpj cnpj) throws
            ProcuraSemResultadoException;

    public ObservableList<Cliente> getObservableList() {
        if(obs == null) {
            obs = FXCollections.observableArrayList();
        }
        return obs;
    }

    abstract public List<Cliente> procurar(String nomeOuCometario) throws
            ProcuraSemResultadoException;
    // Observer
    abstract public void atualizar(EventoObservado ev);


}
