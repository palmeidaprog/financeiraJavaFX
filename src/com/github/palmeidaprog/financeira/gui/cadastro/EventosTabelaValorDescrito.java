package com.github.palmeidaprog.financeira.gui.cadastro;

import com.github.palmeidaprog.financeira.clientes.ValorDescrito;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;

public class EventosTabelaValorDescrito {
    private TableView<ValorDescrito> tabela;
    private ObservableList<ValorDescrito> lista;

    public EventosTabelaValorDescrito(TableView<ValorDescrito> tabela) {
        this.tabela = tabela;
        ativarEventos();
    }

    public ObservableList<ValorDescrito> getLista() {
        return lista;
    }

    private void ativarEventos() {
        lista = FXCollections.observableArrayList(new ArrayList<>());
        tabela.setItems(lista);
        TableColumn<ValorDescrito, String> c1 = (TableColumn<ValorDescrito,
                String>) tabela.getColumns().get(0);
        c1.setCellValueFactory(cellData -> cellData.getValue()
                .descricaoPProperty());
        TableColumn<ValorDescrito, String> c2 = (TableColumn<ValorDescrito,
                String>) tabela.getColumns().get(1);
        c2.setCellValueFactory(cellData -> cellData.getValue()
                .valorPProperty());
    }
}
