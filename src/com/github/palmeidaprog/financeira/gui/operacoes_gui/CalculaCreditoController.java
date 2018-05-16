package com.github.palmeidaprog.financeira.gui.operacoes_gui;

import com.github.palmeidaprog.financeira.gui.ViewFrontController;
import com.github.palmeidaprog.financeira.gui.cadastro.EditaCadastroController;
import com.github.palmeidaprog.financeira.gui.validacoes.ValidaMoeda;
import com.github.palmeidaprog.financeira.operacoes.OperacaoCredito;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CalculaCreditoController implements Initializable {
    private static volatile CalculaCreditoController instance;
    private String tipo;
    private SimpleDateFormat formato;

    @FXML private Label descrLabel, parcelaValorLabel, bemLabel;
    @FXML private TextField valorOpText, jurosText;
    //@FXML private TextField dataTexto;
    @FXML private DatePicker dataPicker;
    @FXML private ComboBox<String> numParcelasCombo, bemCombo;
    @FXML private VBox tipoVBox;
    @FXML private Button novoBemBtn, aceitarBtn;
    @FXML private RadioButton imovelRadio, automovelRadio;

    private CalculaCreditoController() { }

    public synchronized static CalculaCreditoController getInstance() {
        if(instance == null) {
            instance = new CalculaCreditoController();
        }
        return instance;
    }

    public void initialize(URL u, ResourceBundle rb) {


        formato = new SimpleDateFormat("dd/MM/yyyy");
        descrLabel.setText(tipo);
        ObservableList<String> parc = FXCollections.observableArrayList(
                "1x", "2x", "3x", "4x", "5x", "6x", "8x", "10x", "12x"
                , "18x", "24x", "30x", "36x", "48x", "60x");
        numParcelasCombo.setItems(parc);
        numParcelasCombo.setValue("12x");

        // Vencimento Primeira parcela textField
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(System.currentTimeMillis());
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        dataPicker.getEditor().setText(formato.format(calendar.getTime()));

        // juros inicial
        jurosText.setText("1.00");
        ValidaMoeda valida = new ValidaMoeda(valorOpText);
        ValidaMoeda validaJuros = new ValidaMoeda(jurosText);
        eventoDatePicker();
    }

    private void eventoDatePicker() {
        dataPicker.setConverter(new StringConverter<LocalDate>() {
            String pattern = "dd/MM/yyyy";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern
                    (pattern);

            {
                dataPicker.setPromptText(pattern.toLowerCase());
            }

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setTitle(String title) {
        descrLabel.setText(title);
    }

    public void setTipoVBox(VBox tipoVBox) {
        this.tipoVBox.getChildren().clear();
        this.tipoVBox.getChildren().add(tipoVBox);
    }

    //--Events----------------------------------------------------------------

    public void novoBemBtnClick() {
        EditaCadastroController.getInstance().showAutomovel(
                NovoCreditoController.getInstance().getCliente()
                        .getCadastro(),"Novo Automóvel");
    }

    public void valorOpLabelAction() {
        try {
            valorOpText.setText(formataValor(valorOpText.getText()));
            atualizaParcela();
        } catch (NumberFormatException e) {
            ViewFrontController.getInstance().dialogoErro("Erro",
                    valorOpText.getText() + " não é um valor válido!");
        }
    }

    private void atualizaParcela() {
        try {
            Date venc = formato.parse(dataPicker.getEditor().getText());
            parcelaValorLabel.setText(String.format("%.2f", OperacaoCredito
                    .calculaParcela(Double.parseDouble(valorOpText.getText()),
                            Integer.parseInt(numParcelasCombo.getValue().
                                    replace("x", "")),
                            Double.parseDouble(jurosText.getText()), venc)));
        } catch(ParseException e) {
            OperacoesViewFrontController.getInstance().dialogoErro(
                    "Data Inválida", "A data " + dataPicker
                            .getEditor().getText() + " não é valida");
        }
    }

    public void jurosTextAction() {
        try {
            jurosText.setText(formataValor(jurosText.getText()));
            atualizaParcela();
        } catch(NumberFormatException e) {
            OperacoesViewFrontController.getInstance().dialogoErro("Juros " +
                    "Inválidos", "Taxa de juros ao mês é inválida.");
        }
    }

    public void numParcelasComboAction() {
        atualizaParcela();
    }

    private void resetaRadio() {
        imovelRadio.setSelected(false);
        automovelRadio.setSelected(false);
    }

    public void imovelRadioSelected() {
        resetaRadio();
        imovelRadio.setSelected(true);
    }

    public void automovelRadioSelected() {
        resetaRadio();
        automovelRadio.setSelected(true);
    }

    public void valorOpTextKeyTyped() {

    }

    private String formataValor(String valor) throws NumberFormatException {
        double value = Double.parseDouble(valor);
        return String.format(Locale.getDefault(), "%.2f", value);
    }
}
