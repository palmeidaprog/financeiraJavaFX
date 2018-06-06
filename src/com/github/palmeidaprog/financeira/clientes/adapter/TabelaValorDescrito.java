package com.github.palmeidaprog.financeira.clientes.adapter;


import com.github.palmeidaprog.financeira.exception.ProcuraSemResultadoException;
import com.github.palmeidaprog.financeira.interfaces.ValorDescrito;
import com.github.palmeidaprog.financeira.interfaces.ValorDescritoController;
import com.github.palmeidaprog.financeira.interfaces.observador.EventoObservado;
import com.github.palmeidaprog.financeira.interfaces.observador.Observador;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class TabelaValorDescrito<T extends ValorDescrito> implements
        Observador {
    private TableView<T> tabela;
    private ObservableList<T> lista;
    private ValorDescritoController controller;

    public TabelaValorDescrito(TableView<T> tabela,
                               ValorDescritoController controller) {
        this.tabela = tabela;
        this.controller = controller;
        controller.addTabela(this);
        ativarEventos();
    }

    public ObservableList<T> getLista() {
        return lista;
    }

    public void setLista(ObservableList<T> lista) {
        this.lista = lista;
    }

    private void ativarEventos() {
        tabela.setItems(lista);

        @SuppressWarnings("unchecked")
        TableColumn<T, String> c1 =
                (TableColumn<T, String>) tabela.getColumns().get(0);
        c1.setCellValueFactory(cellData -> cellData.getValue()
                .descricaoPProperty());

        @SuppressWarnings("unchecked")
        TableColumn<T, String> c2 =
                (TableColumn<T,String>) tabela.getColumns().get(1);
        c2.setCellValueFactory(cellData -> cellData.getValue()
                .valorPProperty());
    }

    @Override @SuppressWarnings("unchecked")
    public void atualizar(EventoObservado ev) {
        T p = (T) ev.getObjetoModificado();
        //todo: olhar codigo e substituir por logica do TipoEvento
        try {
            System.out.println(controller.indexOf(p));
            lista.add(p);
        } catch(ProcuraSemResultadoException e) {
            lista.remove(p);
        }
    }
}
