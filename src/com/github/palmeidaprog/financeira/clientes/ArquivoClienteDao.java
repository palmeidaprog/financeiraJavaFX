package com.github.palmeidaprog.financeira.clientes;

import com.github.palmeidaprog.financeira.exception.ProcuraSemResultadoException;
import com.github.palmeidaprog.financeira.info.Cnpj;
import com.github.palmeidaprog.financeira.info.Cpf;
import com.github.palmeidaprog.financeira.interfaces.Memento;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


// @design memento
// Implementação SalvaTudo no arquivo nas mudanças
// Tb conhecido co0mo Surubão Design Pattern
public class ArquivoClienteDao extends ClienteDao implements Memento {
    private final String ARQUIVO = "clientes.ser";
    private ObservableList<Cliente> clientes = FXCollections
            .observableArrayList(); // persistenciaa

    // Singleton
    private static volatile ArquivoClienteDao instance;
    private ArquivoClienteDao() throws IOException {
        clientes.addListener(new ListChangeListener<Cliente>() {
            @Override
            public void onChanged(Change<? extends Cliente> c) {
                try {
                    setState();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        });

        File file = new File(ARQUIVO);
        if(!file.exists()) {
            if(!file.createNewFile()) {
                throw new IOException("Problemas ao criar arquivo de dados");
            }
        } else if(file.length() > 0) {
            getState();
        }
        clientes.addListener(new ListChangeListener<Cliente>() {
            @Override
            public void onChanged(Change<? extends Cliente> c) {
                try {
                    atualiza();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public synchronized static ArquivoClienteDao getInstance() throws
            IOException {
        if(instance == null) {
            try {
                instance = new ArquivoClienteDao();
            } catch (IOException e) {
                e.printStackTrace();
                throw new IOException("Não foi possivel ler o banco de dados"
                        + "do programa.");
            }
        }
        return instance;
    }

    public void inserir(Cliente cliente) throws IOException {
        clientes.add(cliente);
        try {
            setState();
        } catch (IOException e) {
            e.printStackTrace();
            clientes.remove(cliente);
            throw new IOException("Não foi possivel criar cliente!");
        }
    }

    public void atualiza() throws IOException {
        try {
            setState();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Não foi possivel salvar as alteracoes!");
        }
    }

    public void remover(Cliente cliente) throws IOException {
        clientes.remove(cliente);
        try {
            setState();
        } catch (IOException e) {
            e.printStackTrace();
            clientes.add(cliente);
            throw new IOException("Não foi possivel criar cliente!");
        }
    }

    /*public ObservableList<Cliente> getClientes() {
        return clientes;
    }*/

    //--Memento Design--------------------------------------------------------

    @Override
    public void getState() throws IOException {
        try(ObjectInputStream objIn  = new ObjectInputStream(new
                FileInputStream(ARQUIVO))) {
            clientes = FXCollections.observableArrayList
                    ((List<Cliente>) objIn.readObject());
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setState() throws IOException {
        try(ObjectOutputStream objOut  = new ObjectOutputStream(new
                FileOutputStream(ARQUIVO))) {
            List<Cliente> cl = new ArrayList<>();
            cl.addAll(clientes);
            objOut.writeObject(cl);
        }
    }

    //--Observer--------------------------------------------------------------

    @Override
    public void update(Observable o, Object arg) {
        try {
            setState();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //------------------------------------------------------------------------

    public Cliente get(int index) {
        return clientes.get(index);
    }

    public int indexOf(Cliente cliente) throws ProcuraSemResultadoException {
        for(int i = 0; i < clientes.size(); i++) {
            if(clientes.get(i).equals(cliente)) {
                return i;
            }
        }
        throw new ProcuraSemResultadoException("Cliente " + cliente + "nãao "
                + "existe.");
    }

    public PessoaFisica procurar(Cpf cpf) throws
            ProcuraSemResultadoException {
        for(Cliente c : clientes) {
            if(c instanceof PessoaFisica) {
                PessoaFisica pf = (PessoaFisica) c;
                if(pf.getCpf().equals(cpf)) {
                    return pf;
                }
            }
        }
        throw new ProcuraSemResultadoException("Cliente com CPF " + cpf +
                " não existe.");
    }

    public PessoaJuridica procurar(Cnpj cnpj) throws
            ProcuraSemResultadoException {
        for(Cliente c : clientes) {
            if(c instanceof PessoaJuridica) {
                PessoaJuridica pj = (PessoaJuridica) c;
                if(pj.getCnpj().equals(cnpj)) {
                    return pj;
                }
            }
        }
        throw new ProcuraSemResultadoException("Cliente com CNPJ " + cnpj +
                " não existe.");
    }

    public List<Cliente> procurar(String nomeOuCometario) throws
            ProcuraSemResultadoException {
        nomeOuCometario = nomeOuCometario.toLowerCase();
        List<Cliente> resposta = new ArrayList<>();
        for(Cliente c : clientes) {
            if(c.getComentario().toLowerCase().contains(nomeOuCometario
                    .toLowerCase())) {
                resposta.add(c);
            }

            if(c instanceof PessoaFisica) {
                PessoaFisica pf = (PessoaFisica) c;
                if(pf.getPrimeiroNome().toLowerCase().contains(
                        nomeOuCometario) || pf.getNomeDoMeio().toLowerCase()
                        .contains(nomeOuCometario) || pf.getSobrenome()
                        .toLowerCase().contains(nomeOuCometario)) {
                    resposta.add(pf);
                }
            } else if(c instanceof PessoaJuridica) {
                PessoaJuridica pj = (PessoaJuridica) c;
                if(pj.getRazaoSocial().toLowerCase().contains(nomeOuCometario)
                        || pj.getNomeFantasia().toLowerCase().contains
                        (nomeOuCometario)) {
                    resposta.add(pj);
                }
            }
        }
        if(resposta.isEmpty()) {
            throw new ProcuraSemResultadoException("Não existe " +
                    "clientes com os dados \"" + nomeOuCometario + "\"");
        }
        return resposta;
    }
}

