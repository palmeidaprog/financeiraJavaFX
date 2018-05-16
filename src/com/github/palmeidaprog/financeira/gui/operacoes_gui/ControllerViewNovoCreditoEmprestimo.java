package com.github.palmeidaprog.financeira.gui.operacoes_gui;

import com.github.palmeidaprog.financeira.gui.cadastro.CadastroViewFrontController;

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
        CadastroViewFrontController.getInstance().showAutomovel(
                NovoCreditoController.getInstance().getCliente()
                        .getCadastro(),"Novo Automóvel");
    }
}
