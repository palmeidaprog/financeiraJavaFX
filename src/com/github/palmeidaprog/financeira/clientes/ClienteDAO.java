package com.github.palmeidaprog.financeira.clientes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;



public class ClienteDAO {
    private final String ARQUIVO = "clientes.ser";
    private List<Cliente> clientes = new ArrayList<>(); // persistenciaa

    // Singleton
    private static volatile ClienteDAO instance;
    private ClienteDAO() throws IOException {
        File file = new File(ARQUIVO);
        if(!file.exists()) {
            if(!file.createNewFile()) {
                throw new IOException("Problemas ao criar arquivo de dados");
            }
        } else if(file.length() > 0) {
            le();
        }
    }

    public synchronized static ClienteDAO getInstance() throws
            IOException {
        if(instance == null) {
            try {
                instance = new ClienteDAO();
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
            salva();
        } catch (IOException e) {
            e.printStackTrace();
            clientes.remove(cliente);
            throw new IOException("Não foi possivel criar cliente!");
        }
    }

    public void atualiza() throws IOException {
        try {
            salva();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Não foi possivel salvar as alteracoes!");
        }
    }

    public void remover(Cliente cliente) throws IOException {
        clientes.remove(cliente);
        try {
            salva();
        } catch (IOException e) {
            e.printStackTrace();
            clientes.add(cliente);
            throw new IOException("Não foi possivel criar cliente!");
        }
    }

    public List<Cliente> getClientes() {
        return clientes;
    }


    public void salva() throws IOException {
        try(ObjectOutputStream objOut  = new ObjectOutputStream(new
                FileOutputStream(ARQUIVO))) {
            objOut.writeObject(clientes);
        }
    }

    public void le() throws IOException {
        try(ObjectInputStream objIn  = new ObjectInputStream(new
                FileInputStream(ARQUIVO))) {
            clientes = (List<Cliente>) objIn.readObject();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

