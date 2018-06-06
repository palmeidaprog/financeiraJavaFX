package com.github.palmeidaprog.financeira.clientes;

import com.github.palmeidaprog.financeira.exception.ProcuraSemResultadoException;
import com.github.palmeidaprog.financeira.info.Cnpj;
import com.github.palmeidaprog.financeira.info.Cpf;
import com.github.palmeidaprog.financeira.interfaces.observador.EventoObs;
import com.github.palmeidaprog.financeira.interfaces.observador.Observador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.util.List;


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
    // Observador
    abstract public void atualizar(EventoObs ev);


}
