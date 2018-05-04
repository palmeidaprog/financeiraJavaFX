package com.github.palmeidaprog.financeira.gui.operacoes_gui;

import com.github.palmeidaprog.financeira.gui.cadastro.CadastrosViewController;

public class ControllerViewNovoCreditoEmprestimo {
    private static volatile ControllerViewNovoCreditoEmprestimo instance;
   
    private ControllerViewNovoCreditoEmprestimo() { }

    public synchronized static ControllerViewNovoCreditoEmprestimo
            getInstance() {
        if(instance == null) {
            instance = new ControllerViewNovoCreditoEmprestimo();
        }
        return instance;
    }

    public void novoBemBtnClick() {
        CadastrosViewController.getInstance().showAutomovel(
                ControllerViewNovoCredito.getInstance().getCliente()
                        .getCadastro(),"Novo Automóvel");
    }
}
