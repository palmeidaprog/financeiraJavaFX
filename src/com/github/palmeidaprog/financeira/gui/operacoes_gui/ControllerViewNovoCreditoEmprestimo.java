package com.github.palmeidaprog.financeira.gui.operacoes_gui;

import com.github.palmeidaprog.financeira.gui.cadastro.EditaCadastroController;

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
        EditaCadastroController.getInstance().showAutomovel(
                NovoCreditoController.getInstance().getCliente()
                        .getCadastro(),"Novo Automóvel");
    }
}
