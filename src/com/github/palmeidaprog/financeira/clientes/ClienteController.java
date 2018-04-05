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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClienteController {
    ClienteDAO dao;
    private List<Cliente> clientes;

    // Singleton
    private static volatile ClienteController instance;
    private ClienteController() throws IOException {
        dao = ClienteDAO.getInstance();
        clientes = dao.getClientes();
    }

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

    public void remover(int index) throws IOException {
        remover(get(index));
    }

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
