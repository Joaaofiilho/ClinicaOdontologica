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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
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
    }

    public void rdBtnFemininoOnAction(ActionEvent event){
        sexo = "F";
    }

    public void btnCancelarOnAction(ActionEvent event){
        mainApp.exibirTelaPrincipal();
    }

    private boolean checarRegex(String nome, String regex, boolean combinar, TextField elemento){
        if(nome.matches(regex) || nome.matches("")){
            if(combinar) {
                elemento.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                        BorderWidths.DEFAULT)));
                return true;
            }else{
                elemento.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                        BorderWidths.DEFAULT)));
                return false;
            }
        }else{
            if(combinar) {
                elemento.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                        BorderWidths.DEFAULT)));
                return false;
            }else{
                elemento.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                        BorderWidths.DEFAULT)));
                return true;
            }
        }
    }

    public void btnSalvarOnAction(ActionEvent event){
        //TODO validar os dados inseridos

        //Dados
        String nome = txtfieldNome.getText(), telefone = txtfieldTelefone.getText(),
        email = txtfieldEmail.getText(), cpf = txtfieldCPF.getText(),
        dataNasc = dateNascimento.toString();
        //Montar sexo
        char sexo;
        if(rdBtnMasculino.isSelected()) sexo = 'M';
        else sexo = 'F';
        //Montar endereco
        String endereco = txtFieldLogradouro.getText() + ", " + txtFieldNumero.getText() + " - " +
                txtFieldBairro.getText().toUpperCase() + ", " + txtFieldCidade.getText() + "-" + txtFieldEstado.getText();

        //Validacao
        boolean valido = true;
        if(!checarRegex(nome, "^\\d+$", false, txtfieldNome)) valido = false;
        if(!checarRegex(telefone, "^\\d{11}$", true, txtfieldTelefone)) valido = false;
        if(!checarRegex(email, "^(\\D{1}\\w*)@(\\D+\\.\\D{2,3})$", true, txtfieldEmail)) valido = false;
        valido = checarRegex(nome, "^\\d+$", false, txtfieldNome);
        valido = checarRegex(nome, "^\\d+$", false, txtfieldNome);
        valido = checarRegex(nome, "^\\d+$", false, txtfieldNome);
        valido = checarRegex(nome, "^\\d+$", false, txtfieldNome);
        valido = checarRegex(nome, "^\\d+$", false, txtfieldNome);


        if(!email.matches()){
            valido = false;
            txtfieldNome.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                    BorderWidths.DEFAULT)));
        }else txtfieldNome.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        if(!cpf.matches("^\\d{11}$")){
            valido = false;
            txtfieldNome.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                    BorderWidths.DEFAULT)));
        }else txtfieldNome.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        if(txtFieldLogradouro.getText().matches("^\\d+$")){
            valido = false;
            txtfieldNome.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                    BorderWidths.DEFAULT)));
        }else txtfieldNome.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        if(!txtFieldNumero.getText().matches("^\\d+$")){
            valido = false;
            txtfieldNome.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                    BorderWidths.DEFAULT)));
        }else txtfieldNome.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        if(!txtFieldBairro.getText().matches("^\\d+$")){
            valido = false;
            txtfieldNome.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                    BorderWidths.DEFAULT)));
        }else txtfieldNome.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        if(txtFieldCidade.getText().matches("^\\d+$")){
            valido = false;
            txtfieldNome.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                    BorderWidths.DEFAULT)));
        }else txtfieldNome.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        if(txtFieldEstado.getText().matches("^\\d+$")){
            valido = false;
            txtfieldNome.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                    BorderWidths.DEFAULT)));
        }else txtfieldNome.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        if(valido){
            Paciente p = new Paciente(nome, cpf, dataNasc, telefone, email, endereco, sexo);
            Agenda.adicionarPaciente(p);
            mainApp.exibirTelaPrincipal();
        }
    }
}
