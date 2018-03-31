package view.Controllers;

import beans.Paciente;
import beans.Consulta;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.shape.Rectangle;

public class TelaPrincipal {
    //Controle do dia
    public Label lblDia;
    public Button btnEsquerda;
    public Button btnDireita;

    //Controle lista lateral
    public ToggleButton tglPaciente;
    public ToggleButton tglConsulta;

    //Lista lateral
    public ListView lstViewLista;
    public Button btnAdicionar;
    public Button btnModificar;
    public Button btnRemover;

    public void initialize(){
        tglConsulta.setSelected(true);
        lstViewLista = new ListView<Consulta>();
    }

    //Controle do dia
    public void btnDireitaOnAction(ActionEvent event){

    }

    public void btnEsquerdaOnAction(ActionEvent event){

    }

    //Controle lista lateral
    public void tglPacienteOnAction(ActionEvent event){

    }

    public void tglConsultaOnAction(ActionEvent event){

    }

    //Lista lateral
    public void btnAdicionarOnAction(ActionEvent event){

    }

    public void btnModificarOnAction(ActionEvent event){

    }

    public void btnRemoverOnAction(ActionEvent event){

    }
}
