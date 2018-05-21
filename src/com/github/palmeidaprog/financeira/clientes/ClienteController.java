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
import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

// @design observer
public class ClienteController implements Observer {
    ClienteDao dao = FactoryClienteDao.getInstance();

    // Singleton
    private static volatile ClienteController instance;
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
    }

    public void inserir(Cliente cliente) throws IOException {
        dao.inserir(cliente);
    }

    public void remover(Cliente cliente) throws IOException {
        dao.remover(cliente);
    }

    @Override
    public void update(Observable o, Object arg) {
        dao.update(o, arg);
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

    //--Metodos Antigos (Candidatos a serem removidos)------------------------


    /*
    public Cliente get(int index) {
        return dao.get(index);
    }*/

    /*public int indexOf(Cliente cliente) throws ProcuraSemResultadoException {
        return dao.indexOf(cliente);
    }*/

    /*
    @Deprecated
    public void remover(int index) throws IOException {
        remover(get(index));
    }*/


}
