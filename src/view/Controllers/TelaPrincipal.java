package view.Controllers;

import beans.Agenda;
import app.MainApp;

import beans.Consulta;
import beans.Paciente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;
import persistence.ConsultaDAO;
import persistence.PacienteDAO;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class TelaPrincipal {
    //Controle do dia
    public Label lblDia;
    public Label lblHorario;
    public Button btnEsquerda;
    public Button btnDireita;

    //Controle lista lateral
    public ToggleButton tglPaciente;
    public ToggleButton tglConsulta;

    //Lista lateral
    public ListView<Consulta> lstViewConsulta;
    public ListView<Paciente> lstViewPaciente;
    public Button btnAdicionar;
    public Button btnModificar;
    public Button btnRemover;

    private MainApp mainApp;
    private Stage dialogStage;

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void initialize(){
        lblDia.setText(Agenda.transformarData(Agenda.getData()));
        tglConsulta.fire();
//        new Thread() {
//
//            @Override
//            public void run() {
//                while (true){
//                    Date date = Calendar.getInstance().getTime();
//                    String hora = Integer.toString(date.getHours()), minutos = Integer.toString(date.getMinutes()),
//                    segundos = Integer.toString(date.getSeconds());
//                    String horaAtual = hora + ":" + minutos + ":" + segundos;
//                    lblHorario.setText(horaAtual);
//
//                }
//            }
//        }.start();
    }

    //TODO | Boolean/String/Int (com switch case, falando serio, no caso str/int) para controlar o botao de adicionar, a lista e outras manipulacoes (consulta/paciente)
    //TODO | Por exemplo, quando for pressionado o botao "Pacientes", a parte do dia/data vai voltar para o dia atual, e o usuario nao podera mudar o dia.
    //TODO | Tambem deveremos controlar o visual dos botoes (toggle). So um podera aparentar estar com toggle.
    //TODO | Alem disso, o botao de adicionar adicionara OU um paciente OU uma consulta, dependendo da opcao selecionada na esquerda.
    //TODO | Colocamos um botao para voltar ao dia de hoje, talvez? A lista mostrara ou TODOS os pacientes, ou as consultas DO DIA SELECIONADO.
    //TODO | Perguntar para o Francisco Samuel como abrir uma nova tela e deixar a tela "de tras" nao selecionavel.

    //Controle do dia
    public void btnDireitaOnAction(ActionEvent event){
        Agenda.acrescentarDia();
        lblDia.setText(Agenda.transformarData(Agenda.getData()));

        ObservableList<Consulta> consultaData = FXCollections.observableArrayList();
        try {
            consultaData.addAll(ConsultaDAO.buscarPorData(Agenda.getData()));
        } catch (Exception e) {
            System.err.println("Erro: Impossível encontrar as consultas.");
            e.printStackTrace();
        }
        lstViewConsulta.setItems(consultaData);
    }

    public void btnEsquerdaOnAction(ActionEvent event){
        Agenda.decrescerDia();
        lblDia.setText(Agenda.transformarData(Agenda.getData()));

        ObservableList<Consulta> consultaData = FXCollections.observableArrayList();
        try {
            consultaData.addAll(ConsultaDAO.buscarPorData(Agenda.getData()));
        } catch (Exception e) {
            System.err.println("Erro: Impossível encontrar as consultas.");
            e.printStackTrace();
        }
        lstViewConsulta.setItems(consultaData);
    }

    //Controle lista lateral
    public void tglPacienteOnAction(ActionEvent event){
        tglConsulta.setSelected(false);
        lstViewPaciente.setItems(Agenda.getPacientes());

        lstViewConsulta.setVisible(false);
        lstViewConsulta.setManaged(false);
        lstViewPaciente.setVisible(true);
        lstViewPaciente.setManaged(true);

        tglPaciente.setDisable(true);
        tglConsulta.setDisable(false);

        btnDireita.setDisable(true);
        btnEsquerda.setDisable(true);
        lblDia.setText("");
    }

    public void tglConsultaOnAction(ActionEvent event){
        tglPaciente.setSelected(false);
        lblDia.setText(Agenda.transformarData(Agenda.getData()));

        ObservableList<Consulta> consultaData = FXCollections.observableArrayList();
        try {
            consultaData.addAll(ConsultaDAO.buscarPorData(Agenda.getData()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        lstViewConsulta.setItems(consultaData);

        lstViewConsulta.setVisible(true);
        lstViewConsulta.setManaged(true);
        lstViewPaciente.setVisible(false);
        lstViewPaciente.setManaged(false);

        tglPaciente.setDisable(false);
        tglConsulta.setDisable(true);

        btnDireita.setDisable(false);
        btnEsquerda.setDisable(false);
    }

    //Lista lateral
    public void btnAdicionarOnAction(ActionEvent event){
        if(tglPaciente.isSelected()) {
            CadastroPaciente.setModificando(false);
            mainApp.exibirCadastroPaciente();
        }else {
            CadastroConsulta.setModificando(false);
            mainApp.exibirCadastroConsulta();
        }
    }

    public void btnModificarOnAction(ActionEvent event){
            if (tglPaciente.isSelected()) {
                try {
                    CadastroPaciente.setModificando(true);
                    mainApp.exibirCadastroPaciente(lstViewPaciente.getSelectionModel().getSelectedItem());
                } catch (Exception e) {
                    System.err.println("Erro: Impossível exibir cadastro do paciente! ");
                    e.getStackTrace();
                }
            } else {
                CadastroConsulta.setModificando(true);
                mainApp.exibirCadastroConsulta(ConsultaDAO.buscarPorId(lstViewConsulta.getSelectionModel().getSelectedItem().getId()));
            }

    }

    public void btnRemoverOnAction(ActionEvent event) {
        if(tglPaciente.isSelected()){
            try {
                PacienteDAO.excluir(lstViewPaciente.getSelectionModel().getSelectedItem().getCpf());
                ObservableList<Paciente> pacientes = FXCollections.observableArrayList();
                try {
                    pacientes.addAll(Agenda.getPacientes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                lstViewPaciente.setItems(pacientes);
            }catch (Exception e){

            }
        }else{
            try {
                ConsultaDAO.excluir(lstViewConsulta.getSelectionModel().getSelectedItem().getId());
                //Agenda.getPacientes().clear();
                //Agenda.init();

                ObservableList<Consulta> consultas = FXCollections.observableArrayList();
                try {
                    consultas.addAll(ConsultaDAO.buscarPorData(Agenda.getData()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                lstViewConsulta.setItems(consultas);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }







    }
}
