package com.github.palmeidaprog.financeira.gui.operacoes_gui;

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
}
