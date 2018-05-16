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
import persistence.PacienteDAO;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    private PacienteDAO PacDAO = new PacienteDAO();

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

    private boolean checarRegex(String nome, String regex, boolean combinar, TextField elemento){
        if(nome.equals("")){
            elemento.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                    BorderWidths.DEFAULT)));
            return false;
        }
        if(nome.matches(regex)){
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

    public void preencher(Paciente paciente){
        txtfieldNome.setText(paciente.getNome());
        txtfieldTelefone.setText(paciente.getTelefone());
        txtfieldEmail.setText(paciente.getEmail());
        txtfieldCPF.setText(paciente.getCpf());
        if(paciente.getSexo() == 'M') rdBtnMasculino.setSelected(true);
        else rdBtnFeminino.setSelected(true);
    }

    public void btnSalvarOnAction(ActionEvent event) throws Exception{
        //TODO validar os dados inseridos

        //Dados
        String nome = txtfieldNome.getText(), telefone = txtfieldTelefone.getText(),
        email = txtfieldEmail.getText(), cpf = txtfieldCPF.getText(),
        dataNasc;
        //Montar data
        String data = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = dateNascimento.getValue();
        if (date != null)
            data = (formatter.format(date));
        char sexo = rdBtnMasculino.isSelected() ? 'M' : 'F';

        //Validacao
        boolean valido = true;
        if(!checarRegex(nome, "^\\d+$", false, txtfieldNome)) valido = false;
        if(!checarRegex(telefone, "^\\d{11}$", true, txtfieldTelefone)) valido = false;
        if(!checarRegex(email, "^(\\D{1}\\w*)@(\\D+\\.\\D{2,3})$", true, txtfieldEmail)) valido = false;
        if(!checarRegex(cpf, "^\\d{11}$", true, txtfieldCPF)) valido = false;
        if(!checarRegex(txtFieldLogradouro.getText(), "^\\d+$", false, txtFieldLogradouro)) valido = false;
        if(!checarRegex(txtFieldNumero.getText(), "^\\d+$", true, txtFieldNumero)) valido = false;
        if(!checarRegex(txtFieldBairro.getText(), "^\\d+$", false, txtFieldBairro)) valido = false;
        if(!checarRegex(txtFieldCidade.getText(), "^\\d+$", false, txtFieldCidade)) valido = false;
        if(!checarRegex(txtFieldEstado.getText(), "^\\d+$", false, txtFieldEstado)) valido = false;
        if(dateNascimento.getValue() == null){
            dateNascimento.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                    BorderWidths.DEFAULT)));
            valido = false;
        }else dateNascimento.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));
        if(!rdBtnMasculino.isSelected() && !rdBtnFeminino.isSelected()){
            rdBtnFeminino.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                    BorderWidths.DEFAULT)));
            rdBtnMasculino.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                    BorderWidths.DEFAULT)));
            valido = false;
        }else{
            rdBtnFeminino.setBorder(null);
            rdBtnMasculino.setBorder(null);
        }
        if(valido){
            Paciente p = new Paciente(nome, cpf, data, telefone, email, txtFieldLogradouro.getText(), Integer.parseInt(txtFieldNumero.getText()), txtFieldComplemento.getText(),
                    txtFieldBairro.getText(),txtFieldCidade.getText(),txtFieldEstado.getText(), sexo);
            Agenda.adicionarPaciente(p);
            mainApp.exibirTelaPrincipal();
        }
    }
}
