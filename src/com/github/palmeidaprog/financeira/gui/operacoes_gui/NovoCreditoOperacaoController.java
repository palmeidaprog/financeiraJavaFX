package com.github.palmeidaprog.financeira.gui.operacoes_gui;

import com.github.palmeidaprog.financeira.gui.cadastro.EditaCadastroController;

public class NovoCreditoOperacaoController {
    private static volatile NovoCreditoOperacaoController instance;

    private NovoCreditoOperacaoController() { }

    public synchronized static NovoCreditoOperacaoController
    getInstance() {
        if(instance == null) {
            instance = new NovoCreditoOperacaoController();
        }
        return instance;
    }

    public void novoBemBtnClick() {
        EditaCadastroController.getInstance().showAutomovel(
                NovoCreditoController.getInstance().getCliente()
                        .getCadastro(),"Novo Automóvel");
    }
}
