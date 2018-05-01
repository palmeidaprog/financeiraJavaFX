package com.github.palmeidaprog.financeira.clientes;

/*
 * UNICAP - Univesidade Catolica de Pernambuco
 * @author Aluno: Paulo R. Almeida Filho
 * http://www.github.com/palmeidaprog/financeira
 * @email pauloalmeidaf@gmail.com
 * @email palmeidaprogramming@gmail.com
 * Professor: Antonio Canvalcanti
 */

import com.github.palmeidaprog.financeira.exception.ProcuraSemResultadoException;
import com.github.palmeidaprog.financeira.info.Cnpj;
import com.github.palmeidaprog.financeira.info.Cpf;
import javafx.application.Platform;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClienteController {
    ClienteDAO dao = ClienteDAO.getInstance();

    // Singleton
    /*private static volatile ClienteController instance;
    private ClienteController() throws IOException { }

    public synchronized static ClienteController getInstance() throws
            IOException {
        if(instance == null) {
            try {
                instance = new ClienteController();
            } catch (IOException e) {
                e.printStackTrace();
                throw new IOException("Não foi possivel ler o banco de dados"
                        + "do programa.");
            }
        }
        return instance;
    }*/

    public

    public void inserir(Cliente cliente) throws IOException {
        dao.inserir(cliente);
    }

    public void remover(Cliente cliente) throws IOException {
        dao.remover(cliente);
    }

    public void remover(int index) throws IOException {
        remover(get(index));
    }

    public void atualiza() throws IOException {
        dao.atualiza();
    }

    public Cliente get(int index) {
        return dao.get(index);
    }

    public int indexOf(Cliente cliente) throws ProcuraSemResultadoException {
        return dao.indexOf(cliente);
    }

    public PessoaFisica procurar(Cpf cpf) throws
            ProcuraSemResultadoException {
        return dao.procurar(cpf);
    }

    public PessoaJuridica procurar(Cnpj cnpj) throws
            ProcuraSemResultadoException {
        return dao.procurar(cnpj);
    }

    public List<Cliente> procurar(String nomeOuCometario) throws
            ProcuraSemResultadoException {
        return dao.procurar(nomeOuCometario);
    }
}
