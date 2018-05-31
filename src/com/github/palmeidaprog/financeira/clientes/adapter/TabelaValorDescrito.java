package com.github.palmeidaprog.financeira.clientes.adapter;


import com.github.palmeidaprog.financeira.clientes.Pendencia;
import com.github.palmeidaprog.financeira.exception.ProcuraSemResultadoException;
import com.github.palmeidaprog.financeira.interfaces.ValorDescrito;
import com.github.palmeidaprog.financeira.interfaces.ValorDescritoController;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.util.Observable;
import java.util.Observer;

public class TabelaValorDescrito<T extends ValorDescrito> implements
        Observer {
    private TableView<T> tabela;
    private ObservableList<T> lista;
    private ValorDescritoController obs;

    public TabelaValorDescrito(TableView<T> tabela,
                               ValorDescritoController obs) {
        this.tabela = tabela;
        this.obs = obs;
        obs.addTabela(this);
        ativarEventos();
    }

    public ObservableList<T> getLista() {
        return lista;
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
    public void update(Observable o, Object arg) {
        try {
        } catch(ProcuraSemResultadoException e) {
            lista.remove(arg);
        }
    }
}
