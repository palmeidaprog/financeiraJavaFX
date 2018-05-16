package com.github.palmeidaprog.financeira.gui.operacoes_gui;

import com.github.palmeidaprog.financeira.gui.cadastro.CadastroViewFrontController;

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
        CadastroViewFrontController.getInstance().showAutomovel(
                NovoCreditoController.getInstance().getCliente()
                        .getCadastro(),"Novo Automóvel");
    }
}
