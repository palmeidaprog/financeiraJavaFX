package com.github.palmeidaprog.financeira.gui.cadastro;

import com.github.palmeidaprog.financeira.interfaces.ObservableSerializable;
import com.github.palmeidaprog.financeira.interfaces.ValorDescrito;
import com.github.palmeidaprog.financeira.interfaces.ValorDescritoController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class EventosTabelaValorDescrito implements Observer {
    private TableView<ValorDescrito> tabela;
    private ObservableList<ValorDescrito> lista;
    private ValorDescritoController obs;

    public EventosTabelaValorDescrito<T extends ValorDescrito>(
            TableView<T> tabela, ValorDescritoController obs) {
        this.tabela = tabela;
        this.obs = obs;
        obs.addObserver(this);
        ativarEventos();
    }

    public ObservableList<? extends ValorDescrito> getLista() {
        return lista;
    }

    private ObservableList<ValorDescrito>


    private void ativarEventos() {
        lista = obs.getLista();
        tabela.setItems(lista);

        @SuppressWarnings("unchecked")
        TableColumn<ValorDescrito, String> c1 = (TableColumn<ValorDescrito,
                String>) tabela.getColumns().get(0);
        c1.setCellValueFactory(cellData -> cellData.getValue()
                .descricaoPProperty());
        @SuppressWarnings("unchecked")
        TableColumn<ValorDescrito, String> c2 = (TableColumn<ValorDescrito,
                String>) tabela.getColumns().get(1);
        c2.setCellValueFactory(cellData -> cellData.getValue()
                .valorPProperty());
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("O: " + o);
        System.out.println("Arg: " + arg);
    }
}
