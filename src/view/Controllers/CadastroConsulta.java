package view.Controllers;

import app.MainApp;
import beans.Consulta;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import persistence.ConsultaDAO;

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

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void btnCancelarOnAction(ActionEvent e){
        mainApp.exibirTelaPrincipal();
    }

    public void btnSalvarOnAction(ActionEvent e){

        /*
        private int dia;
        private int mes;
        private int ano;
        private Paciente paciente;
        private String horarioCompleto;
        private String descricao;
        private float valor;


        */

        try {
            Consulta consulta = new Consulta();

            String data[] = new String[0];

            //Pegando a informação do datePicker
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate date = dateData.getValue();
            if (date != null) {
                data = (formatter.format(date)).split("-");
                System.out.println(data);
            } else {


            }
            consulta.setDia(Integer.parseInt(data[0]));
            consulta.setMes(Integer.parseInt(data[1]));
            consulta.setAno(Integer.parseInt(data[2]));
            consulta.setHorarioCompleto(txtfieldHorario.getText());
            consulta.setDescricao(txtfieldDescricao.getText());
            consulta.setValor(Float.parseFloat(txtfieldValor.getText()));
            //consulta.setPaciente();
            //TODO Fazer o paciente escolhendo da lista






//            dao.inserir(consulta);
            mainApp.exibirTelaPrincipal();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }
}
