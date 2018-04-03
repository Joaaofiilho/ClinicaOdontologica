package view.Controllers;

import app.MainApp;
import beans.Agenda;
import beans.Paciente;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CadastroPaciente {
    public TextField txtfieldNome;
    public TextField txtfieldTelefone;
    public TextField txtfieldEmail;
    public TextField txtfieldCPF;
    public TextField txtFieldLogradouro;
    public TextField txtFieldNumero;
    public TextField txtFieldComplemento;
    public TextField txtFieldBairro;
    public TextField txtFieldCidade;
    public TextField txtFieldEstado;

    public DatePicker dateNascimento;
    public RadioButton rdBtnMasculino;
    public RadioButton rdBtnFeminino;

    public Button btnCancelar;
    public Button btnSalvar;

    private String sexo;

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    public void rdBtnMasculinoOnAction(ActionEvent event){
        sexo = "M";

        if (rdBtnFeminino.isSelected()){
            rdBtnFeminino.setSelected(false);
        }
    }

    public void rdBtnFemininoOnAction(ActionEvent event){
        sexo = "F";

        if(rdBtnMasculino.isSelected()){
            rdBtnMasculino.setSelected(false);
        }

    }

    public void btnCancelarOnAction(ActionEvent event){
        mainApp.exibirTelaPrincipal();
    }

    public void btnSalvarOnAction(ActionEvent event){
        //TODO validar os dados inseridos

        //Montar endereco
        String endereco = txtFieldLogradouro.getText() + ", " + txtFieldNumero.getText() + " - " +
                txtFieldBairro.getText().toUpperCase() + ", " + txtFieldCidade.getText() + "-" + txtFieldEstado.getText();

        //Montar sexo
        char sexo;
        if(rdBtnMasculino.isSelected()) sexo = 'M';
        else sexo = 'F';

        Paciente p = new Paciente(txtfieldNome.getText(), txtfieldCPF.getText(), dateNascimento.toString(), txtfieldTelefone.getText(),
                txtfieldEmail.getText(), endereco, sexo);

        Agenda.adicionarPaciente(p);

        mainApp.exibirTelaPrincipal();
    }
}
