package view.Controllers;

import app.MainApp;
import beans.Agenda;
import beans.Consulta;
import beans.Paciente;
import beans.Procedimento;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import persistence.ConsultaDAO;
import persistence.Item_da_consultaDAO;
import util.RelatorioUtil;

import javax.swing.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CadastroConsulta {

    private ConsultaDAO dao = new ConsultaDAO();
    private static int idConsulta = 0;

    //Textfields
    public TextField txtfieldDescricao;
    public TextField txtfieldValor;
    public TextField txtfieldHorario;

    //Datepicker
    public DatePicker dateData;

    //Combobox
    public ComboBox<Paciente> cbPacientes;

    //Botoes
    public Button btnCancelar;
    public Button btnSalvar;
    public Button btnProcedimento;
    public Button btnImprimir;


    public Label lblAtendimento;
    public Label lblValor;

    private MainApp mainApp;

    private static boolean modificando;

    public void initialize(){
        cbPacientes.setItems(Agenda.getPacientes());

        if(!isModificando()) {
            txtfieldDescricao.setDisable(true);
            txtfieldValor.setDisable(true);
            btnProcedimento.setDisable(true);
            lblAtendimento.setDisable(true);
            lblValor.setDisable(true);
            btnImprimir.setDisable(true);
        }


    }
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }



    public void btnCancelarOnAction(ActionEvent e){
        mainApp.exibirTelaPrincipal();
    }

    private boolean checarRegex(String nome, String regex, boolean combinar, TextField elemento){
        if(nome.equals("")){
            elemento.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                    BorderWidths.DEFAULT)));
            return false;
        }
        if(nome.matches(regex)){
            if(combinar) {
                elemento.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                        BorderWidths.DEFAULT)));
                return true;
            }else{
                elemento.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                        BorderWidths.DEFAULT)));
                return false;
            }
        }else{
            if(combinar) {
                elemento.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                        BorderWidths.DEFAULT)));
                return false;
            }else{
                elemento.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                        BorderWidths.DEFAULT)));
                return true;
            }
        }
    }



    public void preencher(Consulta consulta){
        txtfieldValor.setText(Double.toString(consulta.getValor()));
        txtfieldHorario.setText(consulta.getHorarioCompleto());
        txtfieldDescricao.setText(consulta.getDescricao());

        cbPacientes.getSelectionModel().select(consulta.getPaciente());

        LocalDate date = consulta.getData().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        dateData.setValue(date);

        idConsulta = consulta.getId();
    }

    public void btnSalvarOnAction(ActionEvent e) {
        try {
            boolean valido = true;

            if (dateData.getValue() == null) {
                dateData.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                        BorderWidths.DEFAULT)));
                valido = false;
            } else
                dateData.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                        BorderWidths.DEFAULT)));
            if (cbPacientes.getSelectionModel().isEmpty()){
                cbPacientes.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                        BorderWidths.DEFAULT)));
                valido = false;
            }else cbPacientes.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));
                //Modifiquei para ele poder colocar o valor depois
//            String valor = txtfieldValor.getText();
//            if(!checarRegex(valor, "^\\d+(\\.\\d{1,})?$", true, txtfieldValor)) valido = false;
            String horarioCompleto = txtfieldHorario.getText();
            if(!checarRegex(horarioCompleto, "^[0-2]\\d:[0-5]\\d-[0-2]\\d:[0-5]\\d$", true, txtfieldHorario)) valido = false;


            //Pegando a informação do datePicker
            LocalDate localDate = dateData.getValue();
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            if(valido) {
                //Colocando if ternario no construtor
                Consulta consulta = new Consulta(date,cbPacientes.getSelectionModel().getSelectedItem(),
                        txtfieldHorario.getText(), txtfieldDescricao.getText().isEmpty() ? "" : txtfieldDescricao.getText(), txtfieldValor.getText().isEmpty() ? 0 : Float.parseFloat(txtfieldValor.getText()));
                if(isModificando()){
                    consulta.setId(idConsulta);
                    ConsultaDAO.alterar(consulta);
                }
                else
                    Agenda.adicionarConsulta(consulta);

                mainApp.exibirTelaPrincipal();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public static boolean isModificando() {
        return modificando;
    }

    public static void setModificando(boolean modificando) {
        CadastroConsulta.modificando = modificando;
    }


    public void chamarProcedimento(ActionEvent actionEvent) {
        mainApp.exibirProcedimento();


        ArrayList<Procedimento> procedimentos = Item_da_consultaDAO.buscarProcedimentos(idConsulta);

        if(!procedimentos.isEmpty()){
            double valor = 0;

            for (Procedimento procedimento: procedimentos) {
                valor += procedimento.getValor();
            }
            txtfieldValor.setText(String.valueOf(valor));
        }else{
            txtfieldValor.setText("0");
        }

    }

    public static int getIdConsulta() {
        return idConsulta;
    }

    public static void setIdConsulta(int idConsulta) {
        CadastroConsulta.idConsulta = idConsulta;
    }

    public void imprimir(ActionEvent actionEvent) {

        HashMap<String, Object> params = new HashMap<String, Object>();

        params.put("cpf_busca", cbPacientes.getValue().getCpf());
        params.put("id_consulta", idConsulta);

        try {

            RelatorioUtil.gerarPDF(params, "relatorio_proce_cons_pac");
            RelatorioUtil.exibirRelatorio(params,"relatorio_proce_cons_pac");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar o relatório!");
            Logger.getLogger(InformacoesPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
}
