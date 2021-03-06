package view.Controllers;

import beans.Agenda;
import app.MainApp;

import beans.Consulta;
import beans.Paciente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import persistence.ConsultaDAO;
import persistence.PacienteDAO;
import util.RelatorioUtil;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static beans.Agenda.getPacientes;

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
    public Button btnImprimir;

    //Buscar Paciente
    public Label lblBuscar;
    public TextField txtFBuscar;


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
        lstViewPaciente.refresh();

        tglConsulta.setSelected(false);
        lstViewPaciente.setItems(getPacientes());

        lstViewConsulta.setVisible(false);
        lstViewConsulta.setManaged(false);
        lstViewPaciente.setVisible(true);
        lstViewPaciente.setManaged(true);


        tglPaciente.setDisable(true);
        tglConsulta.setDisable(false);

        btnDireita.setDisable(true);
        btnEsquerda.setDisable(true);
        lblDia.setText("");

        //Exibindo o campo de busca ao paciente para o usuario;
        lblBuscar.setDisable(false);
        lblBuscar.setVisible(true);
        txtFBuscar.setVisible(true);
        txtFBuscar.setDisable(false);

        btnImprimir.setVisible(true);
        btnImprimir.setDisable(false);

        btnModificar.setText("Modificar");

        //Verificar se occorre um click duplo e exibi a tela do paciente
        lstViewPaciente.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {

                if (click.getClickCount() == 2) {
                    try {


//                        mainApp.exibirCadastroPaciente(lstViewPaciente.getSelectionModel().getSelectedItem());

                        //Modoficado aqui
                        mainApp.exibirPacienteInfo(lstViewPaciente.getSelectionModel().getSelectedItem());
                    } catch (Exception e) {
                        System.err.println("Erro: Impossível exibir cadastro do paciente! ");
//                        e.printStackTrace();
                    }

                }
            }
        });




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

        //Exibindo o campo de busca ao paciente para o usuario;
        lblBuscar.setVisible(false);
        txtFBuscar.setVisible(false);
        txtFBuscar.setDisable(true);


        btnImprimir.setVisible(false);
        btnImprimir.setDisable(true);

        btnModificar.setText("Atendimento");

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
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "Você deve selecionar um paciente", "Modificar Paciente",0);

            }
        } else {
            try{
                CadastroConsulta.setModificando(true);
                mainApp.exibirCadastroConsulta(ConsultaDAO.buscarPorId(lstViewConsulta.getSelectionModel().getSelectedItem().getId()));
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "Você deve selecionar uma consulta", "Atendimento",0);

            }
        }

    }

    public void buscaNomePaciente(KeyEvent keyEvent) {
        ObservableList<Paciente> listaNome = FXCollections.observableArrayList();

        //TODO -----------ADicionar para aparaecer vazio----------
        ObservableList<Paciente> listaFiltrada = Agenda.getPacientes();

            for (Paciente paciente : listaFiltrada) {
                if (paciente.getNome().toLowerCase().contains(txtFBuscar.getText().toLowerCase())) {
                    listaNome.add(paciente);
                }
            }

            lstViewPaciente.setItems(listaNome);
            lstViewPaciente.refresh();

    }




    public void btnRemoverOnAction(ActionEvent event) {

        if(tglPaciente.isSelected()){
            try{
                PacienteDAO.excluir(lstViewPaciente.getSelectionModel().getSelectedItem().getCpf());
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "Você deve selecionar um paciente", "Excluir Paciente",0);
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
            } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Você deve selecionar uma consulta", "Excluir Consulta",0);

            }
        }







    }


    public void imprimirPacientes(ActionEvent actionEvent) {
        try {

            HashMap<String, Object> params = new HashMap<>();

            RelatorioUtil.gerarPDF(params, "RelatorioPacientes");
            RelatorioUtil.exibirRelatorio(params,"RelatorioPacientes");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar o relatório!");
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }



    }
}