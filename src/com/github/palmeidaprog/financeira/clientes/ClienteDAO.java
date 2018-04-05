package com.github.palmeidaprog.financeira.clientes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import com.github.palmeidaprog.financeira.info.*;
import com.github.palmeidaprog.financeira.info.telefone.*;
import com.github.palmeidaprog.financeira.operacoes.*;
//import com.thoughtworks.xstream.XStream;
//import com.thoughtworks.xstream.io.StreamException;


public class ClienteDAO {
    private final String ARQUIVO = "clientes.ser";
    private List<Cliente> clientes = new ArrayList<>(); // persistenciaa
    // private XStream xstream = new XStream();

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

    //public ClienteDAO() throws IOException {    }

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

    /*private void salva() throws IOException {
        /*xstream.alias("cliente", Cliente.class);
        xstream.alias("automovel", Automovel.class);
        xstream.alias("bem", Bem.class);
        xstream.alias("cadastro", Cadastro.class);
        xstream.alias("credito", Credito.class);
        xstream.alias("imovel", Imovel.class);
        xstream.alias("pendencia", Pendencia.class);
        xstream.alias("pendenciacontroller", PendenciaController.class);
        xstream.alias("pessoafisica", PessoaFisica.class);
        xstream.alias("pessoajuridica", PessoaJuridica.class);
        xstream.alias("renda", Renda.class);
        xstream.alias("rendacontroller", RendaController.class);
        xstream.alias("bairro", Bairro.class);
        xstream.alias("cep", Cep.class);File file = new File(ARQUIVO);
        if(!file.exists()) {
            if(!file.createNewFile()) {
                throw new IOException("Problemas ao criar arquivo de dados");
            }
        } else if(file.length() > 0) {
            //le();
        xstream.alias("cidade", Cidade.class);
        xstream.alias("cnpj", Cnpj.class);
        xstream.alias("cpf", Cpf.class);
        xstream.alias("endereco", Endereco.class);
        xstream.alias("enderecocontroller",EnderecoController.class);
        xstream.alias("Estado", Estado.class);
        xstream.alias("InscricaoFiscal", InscricaoFiscal.class);
        xstream.alias("Pais", Pais.class);
        xstream.alias("Debito", Debito.class);
        xstream.alias("Financiamento", Financiamento.class);
        xstream.alias("OperacaoCredito", OperacaoCredito.class);
        xstream.alias("Pagamento", Pagamento.class);
        xstream.alias("PagamentoController", PagamentoController.class);
        xstream.alias("Parcela", Parcela.class);
        xstream.alias("ParcelaController", ParcelaController.class);
        xstream.alias("Quitacao", Quitacao.class);
        xstream.alias("CodigoArea", CodigoArea.class);
        xstream.alias("NumeroTelefone", NumeroTelefone.class);
        xstream.alias("Telefone", Telefone.class);
        xstream.alias("TelefoneController", TelefoneController.class);
        xstream.alias("TipoTelefone", TipoTelefone.class);

        String xml = xstream.toXML(clientes.get(0));
        try(FileWriter fw = new FileWriter(ARQUIVO)) {
            fw.write(xml);
            fw.close();
        }
        stringToDom(xml);
    }*/

    public void salva() throws IOException {
        try(ObjectOutputStream objOut  = new ObjectOutputStream(new
                FileOutputStream(ARQUIVO))) {
            objOut.writeObject(clientes.get(0));
        }
    }

    public void le() throws IOException {
        try(ObjectInputStream objIn  = new ObjectInputStream(new
                FileInputStream(ARQUIVO))) {
            clientes.add((Cliente) objIn.readObject());
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /*private void le() throws IOException {
        try {
            //Cliente cliente = (Cliente) xstream.fromXML(domToString());
            //clientes.add(cliente);
        } /*catch (StreamException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Não foi possivel ler o arquivo de " +
                    "clientes");
        }
    }*/
}

