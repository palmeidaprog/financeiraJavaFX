package com.github.palmeidaprog.financeira.gui.operacoes_gui;

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
}
