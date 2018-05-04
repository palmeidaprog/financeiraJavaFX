package com.github.palmeidaprog.financeira.gui.operacoes_gui;

import com.github.palmeidaprog.financeira.gui.cadastro.CadastrosViewController;

public class ControllerViewNovoCreditoOutraOperacao {
    private static volatile ControllerViewNovoCreditoOutraOperacao instance;

    private ControllerViewNovoCreditoOutraOperacao() { }

    public synchronized static ControllerViewNovoCreditoOutraOperacao
    getInstance() {
        if(instance == null) {
            instance = new ControllerViewNovoCreditoOutraOperacao();
        }
        return instance;
    }

    public void novoBemBtnClick() {
        CadastrosViewController.getInstance().showAutomovel(
                ControllerViewNovoCredito.getInstance().getCliente()
                        .getCadastro(),"Novo Automóvel");
    }
}
