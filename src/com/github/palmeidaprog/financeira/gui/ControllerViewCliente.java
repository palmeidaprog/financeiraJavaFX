package com.github.palmeidaprog.financeira.gui;

import com.github.palmeidaprog.financeira.clientes.Cadastro;
import com.github.palmeidaprog.financeira.clientes.Cliente;
import com.github.palmeidaprog.financeira.clientes.PessoaFisica;
import com.github.palmeidaprog.financeira.clientes.PessoaJuridica;
import com.github.palmeidaprog.financeira.gui.cadastro.CadastrosViewController;
import com.github.palmeidaprog.financeira.gui.operacoes_gui.OperacoesViewController;
import com.github.palmeidaprog.financeira.info.Endereco;
import com.github.palmeidaprog.financeira.info.telefone.Telefone;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.text.ParseException;

public class ControllerViewCliente {
    @FXML private Label primeiroNomeLabel, meioNomeLabel, sobrenomeLabel;
    @FXML private Label primeiroNomeLabel2, meioNomeLabel2, sobrenomeLabel2;
    @FXML private Label cpfLabel2, outroLabel2, CpfLabel1, orgaoLabel2;
    @FXML private Label ruaLabel2, complLabel2, bairroLabel2, cidadeLabel2;
    @FXML private Label cepLabel2, paisLabel2, tipoEndLabel2, telefoneLabel2;
    @FXML private Label comentLabel2, tipoTelLabel2, credTotalLabel;
    @FXML private Label credDispLabel, debTotLabel, debNomLabel, rendasLabel;
    @FXML private Label bensLabel;
    @FXML private Button editaCadastroBtn;
    private Cliente c;

    private static volatile ControllerViewCliente instance;
    private ControllerViewCliente() { }
    public synchronized static ControllerViewCliente getInstance() {
        if(instance == null) {
            instance = new ControllerViewCliente();
        }
        return instance;
    }



    public void mostraCliente(Cliente cliente) {
        this.c = cliente;
        if(cliente instanceof PessoaFisica) {
            PessoaFisica pf = (PessoaFisica) cliente;
            mostraCliente(pf);
        } else if(cliente instanceof PessoaJuridica){
            PessoaJuridica pj = (PessoaJuridica) cliente;
            mostraCliente(pj);
        }
        mostraEndereco(cliente);
        mostraTelefone(cliente);
        complLabel2.setText(cliente.getComentario());
        mostraCadastro(cliente.getCadastro());
    }

    private void mostraCliente(PessoaJuridica cliente) {
        primeiroNomeLabel.setText("Razão Social:");
        primeiroNomeLabel2.setText(cliente.getRazaoSocial());
        meioNomeLabel.setText("Nome Fantasia:");
        meioNomeLabel2.setText(cliente.getNomeFantasia());
        sobrenomeLabel.setVisible(false);
        sobrenomeLabel2.setVisible(false);
        cpfLabel2.setText("CNPJ (Sem pontos):");
        cpfLabel2.setText(cliente.getCnpj().formatado());
        orgaoLabel2.setText(cliente.getCnpj().getOrgaoExpedidor() + "/" +
            cliente.getCnpj().getEstado().getSigla());
    }

    private void mostraCliente(PessoaFisica cliente) {
        primeiroNomeLabel.setText("Primeiro Nome:");
        primeiroNomeLabel2.setText(cliente.getPrimeiroNome());
        meioNomeLabel.setText("Nome do Meio:");
        meioNomeLabel2.setText(cliente.getNomeDoMeio());
        sobrenomeLabel.setVisible(true);
        sobrenomeLabel2.setVisible(true);
        cpfLabel2.setText("CPF (Sem pontos):");
        cpfLabel2.setText("" + cliente.getCpf().formatado());
        orgaoLabel2.setText(cliente.getCpf().getOrgaoExpedidor() + "/" +
                cliente.getCpf().getEstado().getSigla());
    }

    private void mostraEndereco(Cliente cliente) {
        Endereco e = cliente.getEnderecos().getPrincipal();
        ruaLabel2.setText(e.getRua() + ", " + e.getNumero());

        bairroLabel2.setText(e.getBairro().formatado());
        cidadeLabel2.setText(e.getCidade().formatado() + "-" + e.getEstado()
                .getSigla());
        cepLabel2.setText(e.getCep().formatado());
        paisLabel2.setText(e.getPais().getNome());
        tipoEndLabel2.setText(e.getTipo().formatado());
    }

    private void mostraTelefone(Cliente cliente) {
        Telefone t = cliente.getTelefones().getPrincipal();
        telefoneLabel2.setText(t.getPais().getCodigo() + " " +
                t.getCodigoArea().formatado() + "-" + t.getNumero()
                .formatado());
        tipoTelLabel2.setText(t.getTipo().formatado());
    }

    private String formataValor(double v) {
        return String.format("%.2f", v);
    }

    public void novaOperacaoBtnClicked() {
        OperacoesViewController.getInstance().showNovaOperacao(c);
    }

    public void mostraCadastro(Cadastro c) {
        bensLabel.setText(formataValor(c.getBens().totalLiquido()));
        rendasLabel.setText(formataValor(c.getRendas().total()));
        credTotalLabel.setText(formataValor(c.getCredito()
                .getFinanciamento()));
        credDispLabel.setText(formataValor(c.getCredito().getPessoal()));
    }
    /*
    redDispLabel, debTotLabel, debNomLabel, rendasLabel;
    @FXML private Label bensLabel;
     */

    public void editaCadastroBtnClicked() {
        CadastrosViewController.getInstance().showEditaCadastro(
                c.getCadastro());
    }

    public Cliente getCliente() {
        return c;
    }
}
