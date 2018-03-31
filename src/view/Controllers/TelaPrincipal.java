package view.Controllers;

import beans.Paciente;
import beans.Consulta;

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
        tglPaciente.setSelected(true);
        lstViewLista = new ListView<Paciente>();
    }
}
