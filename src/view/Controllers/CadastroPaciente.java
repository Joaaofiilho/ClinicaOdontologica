package view.Controllers;

import beans.Agenda;
import beans.Paciente;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

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

    public String sexo;

    public void rdBtnMasculinoOnAction(ActionEvent event){
        sexo = "M";
    }

    public void rdBtnFemininoOnAction(ActionEvent event){
        sexo = "F";
    }

    public void btnCancelarOnAction(ActionEvent event){

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
    }
}
