package com.github.palmeidaprog.financeira.clientes;

/*
 * UNICAP - Univesidade Catolica de Pernambuco
 * @author Aluno: Paulo R. Almeida Filho
 * http://www.github.com/palmeidaprog/financeira
 * @email pauloalmeidaf@gmail.com
 * @email palmeidaprogramming@gmail.com
 * Professor: Antonio Canvalcanti
 */

import com.github.palmeidaprog.financeira.exception
        .ProcuraSemResultadoException;
import com.github.palmeidaprog.financeira.info.Cnpj;
import com.github.palmeidaprog.financeira.info.Cpf;
import com.github.palmeidaprog.financeira.interfaces.observador.EventoObs;;
import com.github.palmeidaprog.financeira.interfaces.observador.Observador;

import java.io.IOException;
import java.util.List;



// @design Observador
public class ClienteController implements Observador {
    private ClienteDao dao = FactoryClienteDao.getInstance();

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
        cliente.adicionaObservador(this);
    }

    public void remover(Cliente cliente) throws IOException {
        dao.remover(cliente);
        cliente.adicionaObservador(this);
    }

    @Override
    public void atualizar(EventoObs ev) {
        dao.atualizar(ev);
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
