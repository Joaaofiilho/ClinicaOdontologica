package view.Controllers;

import app.MainApp;
import beans.Agenda;
import beans.Consulta;
import beans.Paciente;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import persistence.ConsultaDAO;
import persistence.PacienteDAO;
import persistence.ProcedimentoDAO;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class CadastroConsulta {

    private ConsultaDAO dao = new ConsultaDAO();

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

    private MainApp mainApp;

    private static boolean modificando;

    public void initialize(){
        cbPacientes.setItems(Agenda.getPacientes());
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

        LocalDate date = consulta.getData().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        dateData.setValue(date);
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
            String valor = txtfieldValor.getText();
            if(!checarRegex(valor, "^\\d+(\\.\\d{1,})?$", true, txtfieldValor)) valido = false;
            String horarioCompleto = txtfieldHorario.getText();
            if(!checarRegex(horarioCompleto, "^[0-2]\\d:[0-5]\\d-[0-2]\\d:[0-5]\\d$", true, txtfieldHorario)) valido = false;


            //Pegando a informação do datePicker
            LocalDate localDate = dateData.getValue();
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            if(valido) {
                Consulta consulta = new Consulta(date,cbPacientes.getSelectionModel().getSelectedItem(),
                        txtfieldHorario.getText(), txtfieldDescricao.getText(), Float.parseFloat(txtfieldValor.getText()));
                if(isModificando())
                    ConsultaDAO.alterar(consulta);
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
}
