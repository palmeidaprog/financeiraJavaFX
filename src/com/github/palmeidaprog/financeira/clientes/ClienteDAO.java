package com.github.palmeidaprog.financeira.clientes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import com.github.palmeidaprog.financeira.info.*;
import com.github.palmeidaprog.financeira.info.telefone.*;
import com.github.palmeidaprog.financeira.operacoes.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.StreamException;


public class ClienteDAO {
    private final String ARQUIVO = "clientes.xml";
    private List<Cliente> clientes; // persistencia
    private XStream xstream = new XStream();

    public ClienteDAO() throws IOException {
        File file = new File(ARQUIVO);
        if(!file.exists()) {
            clientes = new ArrayList<>();
            if(!file.createNewFile()) {
                throw new IOException("Problemas ao criar arquivo de dados");
            }
        } else if(file.length() > 0) {
            le();
        } else {
            clientes = new ArrayList<>();
        }
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

    private void salva() throws IOException {
        xstream.alias("cliente", Cliente.class);
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
        xstream.alias("cep", Cep.class);
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

        String xml = xstream.toXML(clientes);
        try(FileWriter fw = new FileWriter(ARQUIVO)) {
            fw.write(xml);
            fw.close();
        }
        stringToDom(xml);
    }

    public void stringToDom(String xmlSource) throws IOException {
        try(FileWriter fw = new FileWriter(ARQUIVO)) {
            fw.write(xmlSource);
            fw.close();
        }
    }

    public String domToString() throws IOException {
        String xml;
        try(FileReader fr = new FileReader(ARQUIVO)) {
           xml = new String(fr.toString());
        }
        return xml;
    }

    private void le() throws IOException {
        try {
            clientes = (List<Cliente>) xstream.fromXML(domToString());
        } catch (StreamException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Não foi possivel ler o arquivo de " +
                    "clientes");
        }
    }
}

