package view.Controllers;

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

    public void rdBtnMasculinoOnAction(ActionEvent event){

    }

    public void rdBtnFemininoOnAction(ActionEvent event){

    }

    public void btnCancelarOnAction(ActionEvent event){

    }

    public void btnSalvarOnAction(ActionEvent event){
        //TODO validar os dados inseridos

    }
}
