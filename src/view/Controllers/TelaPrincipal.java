package view.Controllers;

import beans.Agenda;
import beans.Paciente;
import app.MainApp;
import beans.Consulta;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.Principal;

public class TelaPrincipal {
    //Controle do dia
    public Label lblDia;
    public Button btnEsquerda;
    public Button btnDireita;

    //Controle lista lateral
    public ToggleButton tglPaciente;
    public ToggleButton tglConsulta;

    //Lista lateral
    public ListView<String> lstViewLista;
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

    //Agenda agenda = new Agenda();
    public void initialize(){
        tglConsulta.setSelected(true);
        lblDia.setText(Agenda.getData());
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
        lblDia.setText(Agenda.getData());
    }

    public void btnEsquerdaOnAction(ActionEvent event){
        Agenda.decrescerDia();
        lblDia.setText(Agenda.getData());
    }

    //Controle lista lateral
    public void tglPacienteOnAction(ActionEvent event){
        tglConsulta.setSelected(false);
        lstViewLista.setItems(Agenda.getPacientes());
        tglPaciente.setDisable(true);
        tglConsulta.setDisable(false);

    }

    public void tglConsultaOnAction(ActionEvent event){
        tglPaciente.setSelected(false);
        lstViewLista.setItems(Agenda.getConsultas());
        tglPaciente.setDisable(false);
        tglConsulta.setDisable(true);
    }

    //Lista lateral
    public void btnAdicionarOnAction(ActionEvent event){
        if(tglPaciente.isSelected()){
            mainApp.exibirCadastroPaciente();
        }else{
            mainApp.exibirCadastroConsulta();
        }
    }

    public void btnModificarOnAction(ActionEvent event){
        if(tglPaciente.isSelected()){
            mainApp.exibirCadastroPaciente();
        }else{
            mainApp.exibirCadastroConsulta();
        }
    }

    public void btnRemoverOnAction(ActionEvent event) {
        //Remove tanto da lista quanto do txt
    }
}
