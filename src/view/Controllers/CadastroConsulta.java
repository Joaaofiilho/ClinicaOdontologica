package view.Controllers;

import app.MainApp;
import beans.Agenda;
import beans.Consulta;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import persistence.ConsultaDAO;
import persistence.PacienteDAO;
import persistence.ProcedimentoDAO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CadastroConsulta {

    private ConsultaDAO dao = new ConsultaDAO();

    //Textfields
    public TextField txtfieldDescricao;
    public TextField txtfieldValor;
    public TextField txtfieldHorario;

    //Datepicker
    public DatePicker dateData;

    //Combobox
    public ComboBox<String> cbPacientes;

    //Botoes
    public Button btnCancelar;
    public Button btnSalvar;

    private MainApp mainApp;

    public void initialize(){
        cbPacientes.setItems(Agenda.getPacientes());
    }
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }



    public void btnCancelarOnAction(ActionEvent e){
        mainApp.exibirTelaPrincipal();
    }

    public void btnSalvarOnAction(ActionEvent e) {
        try {



            String data[] = new String[0];

            //Pegando a informação do datePicker
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate date = dateData.getValue();
            if (date != null) {
                data = (formatter.format(date)).split("-");
            }

            Consulta consulta = new Consulta(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]),
                    PacienteDAO.buscarPorIndex(cbPacientes.getSelectionModel().getSelectedIndex()),txtfieldHorario.getText(),
                    txtfieldDescricao.getText(), Float.parseFloat(txtfieldValor.getText()));

            Agenda.adicionarConsulta(consulta);
            mainApp.exibirTelaPrincipal();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }
}
