package com.github.palmeidaprog.financeira.gui.cadastro;

/*
 * UNICAP - Univesidade Catolica de Pernambuco
 * @author Aluno: Paulo R. Almeida Filho
 * http://www.github.com/palmeidaprog/financeira
 * @email pauloalmeidaf@gmail.com
 * @email palmeidaprogramming@gmail.com
 * Professor: Antonio Canvalcanti
 */


import com.github.palmeidaprog.financeira.clientes.Automovel;
import com.github.palmeidaprog.financeira.clientes.Cadastro;
import com.github.palmeidaprog.financeira.clientes.Pendencia;
import com.github.palmeidaprog.financeira.clientes.PendenciaController;
import com.github.palmeidaprog.financeira.interfaces.ValorDescrito;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.ResourceBundle;

public class AdicionaAutomovelController implements Initializable {
    private Cadastro cadastro;

    //--FXML------------------------------------------------------------------
    @FXML
    private Label descrLabel, bemLabel;
    @FXML
    private TextField marcaText, modeloText, anoModText, anoFabText,
            valorText;
    @FXML
    private VBox tipoVBox;
    @FXML
    private Button adicPendBtn, delPendBtn, criarBtn;
    @FXML
    private TableView<ValorDescrito> pendTable;
    @FXML
    private TableColumn<ValorDescrito, String> descrPendCol;
    @FXML
    private TableColumn<ValorDescrito, String> valorPendCol;
    private ObservableList<ValorDescrito> pendencias;
    private PendenciaController novaPendencias = new PendenciaController();

    //--Singleton-------------------------------------------------------------
    private static volatile AdicionaAutomovelController instance;
    private AdicionaAutomovelController() { }

    public synchronized static AdicionaAutomovelController getInstance() {
        if(instance == null) {
            instance = new AdicionaAutomovelController();
        }
        return instance;
    }

    //--Inicializacao---------------------------------------------------------

    public void initialize(URL u, ResourceBundle rb) {
        EventosTabelaValorDescrito eventos = new EventosTabelaValorDescrito(
                pendTable, novaPendencias);
        pendencias = eventos.getLista();
    }

    //--Setters Getters-------------------------------------------------------

    public void setCadastro(Cadastro cadastro) {
        this.cadastro = cadastro;
    }


    //--Eventos---------------------------------------------------------------

    // click do botao Adicionar
    public void adicBtnClicked() {
        if(validaCampos()){
            // todo: terminar o adicionar automovel button (checar os campos
            // do financiamento se esta adicionando o novo automovel
            cadastro.getBens().inserir(new Automovel(Double.parseDouble(
                    valorText.getText()), marcaText.getText(), modeloText
                    .getText(), Integer.parseInt(anoModText.getText()),
                    Integer.parseInt(anoFabText.getText())));
            CadastroViewFrontController.getInstance().fechaAutomovel();
            EditaCadastroController.getInstance().atualizaRendas();
        }
    }

    // evento do deletar Pendencia da TableView
    public void delPendBtnClicked() {
        pendencias.remove(pendTable.getSelectionModel().getSelectedItem());
    }

    // evento Adicionar Pendencia na TableVoew
    public void adicPendBtnClicked() {
        CadastroViewFrontController.getInstance().showNovaPendencia();
    }

    // evento cancelar (adicionar automovel)
    public void cancelBtnClicked() {
        CadastroViewFrontController.getInstance().fechaAutomovel();
    }


    //--Metodos Suporte-------------------------------------------------------

    public void adicionaPendencia(Pendencia pendencia) {
        pendencias.add(pendencia);
    }

    // validáçào global de todos campos antes de adicioanr o automovel
    private boolean validaCampos() {
        int anoF, anoM;
        try {
            anoF = parseInt(anoFabText);
            anoM = parseInt(anoModText);
            if(anoF > anoM) {
                CadastroViewFrontController.getInstance().dialogoErro(
                        "Erro Logico", "O ano de fabricação não "
                                + "pode ser posterior ao ano modelo.");
                anoFabText.requestFocus();
                return false;
            } else if(anoF < 1769) {
                CadastroViewFrontController.getInstance().dialogoErro(
                        "Erro Logico", "O ano de fabricação não "
                                + "pode ser anterior ao ano 1767.");
                anoFabText.requestFocus();
                return false;
            } else if(anoM < 1769) {
                CadastroViewFrontController.getInstance().dialogoErro(
                        "Erro Logico", "O ano de modelo não "
                                + "pode ser anterior ao ano 1767.");
                anoModText.requestFocus();
                return false;
            }
        } catch(Exception e) {
            return false;
        }

        return (!campoVazio(marcaText, "Marca") &&
                !campoVazio(modeloText, "Modelo") &&
                !campoVazio(anoModText, "Ano Modelo") &&
                !campoVazio(anoFabText, "Ano de Fabricação") &&
                !campoVazio(valorText, "valor"));
    }

    //suporte para validaCampos()
    private int parseInt(TextField tf) throws Exception {
        int num;
        try {
            num = Integer.parseInt(tf.getText());
        } catch(NumberFormatException e) {
            CadastroViewFrontController.getInstance().dialogoErro(
                    "Ano Inválido", "Formato "
                    + "inválido de número");
            tf.requestFocus();
            throw new Exception();
        }
        return num;
    }

    // suporte para validaCampos()
    private boolean campoVazio(TextField text, String nome) {
        if(text.getText().trim().isEmpty()) {
            CadastroViewFrontController.getInstance().dialogoErro("Erro",
                    "Você precisa preencher o campo " + nome + " para " +
                            "adionar o novo automóvel.");
            text.requestFocus();
            return true;
        }
        return false;
    }
}
