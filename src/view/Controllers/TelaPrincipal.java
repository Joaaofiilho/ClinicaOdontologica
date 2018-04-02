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

    //TODO | Boolean/String/Int (com switch case, falando serio, no caso str/int) para controlar o botao de adicionar, a lista e outras manipulacoes (consulta/paciente)
    //TODO | Por exemplo, quando for pressionado o botao "Pacientes", a parte do dia/data vai voltar para o dia atual, e o usuario nao podera mudar o dia.
    //TODO | Tambem deveremos controlar o visual dos botoes (toggle). So um podera aparentar estar com toggle.
    //TODO | Alem disso, o botao de adicionar adicionara OU um paciente OU uma consulta, dependendo da opcao selecionada na esquerda.
    //TODO | Colocamos um botao para voltar ao dia de hoje, talvez? A lista mostrara ou TODOS os pacientes, ou as consultas DO DIA SELECIONADO.
    //TODO | Perguntar para o Francisco Samuel como abrir uma nova tela e deixar a tela "de tras" nao selecionavel.

    //Controle do dia
    public void btnDireitaOnAction(ActionEvent event){

    }

    public void btnEsquerdaOnAction(ActionEvent event){

    }

    //Controle lista lateral
    public void tglPacienteOnAction(ActionEvent event){
        tglConsulta.setSelected(false);
        lstViewLista = new ListView<Paciente>();
        //TODO criar uma observable list dos pacientes
        //lstViewLista.setItems();
    }

    public void tglConsultaOnAction(ActionEvent event){
        tglPaciente.setSelected(false);
        lstViewLista = new ListView<Consulta>();
        //TODO criar uma observable list das consultas do dia
        //lstViewLista.setItems();
    }

    //Lista lateral
    public void btnAdicionarOnAction(ActionEvent event){
        if(tglPaciente.isSelected()){

        }else{

        }
    }

    public void btnModificarOnAction(ActionEvent event){
        if(tglPaciente.isSelected()){

        }else{

        }
    }

    public void btnRemoverOnAction(ActionEvent event){

    }
}
