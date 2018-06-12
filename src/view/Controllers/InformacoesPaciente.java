package view.Controllers;

import app.MainApp;
import beans.Paciente;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class InformacoesPaciente {

    public Label lblNome;
    public Label lblCpf;
    public Label lblEmail;
    public Label lblTe;
    public Label lblSexo;
    public Label lblEnd;
    public Label lblComplemento;
    public Button btnFechar;


    private MainApp mainApp;

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    public void exibir(Paciente p){
        lblTe.setText(p.getTelefone());
        lblNome.setText(p.getNome());
        lblCpf.setText(p.getCpf());
        lblEmail.setText(p.getEmail());
        lblSexo.setText(p.getSexo() == 'M' ? "Masculino" : " Femino");
        lblEnd.setText(p.getLogradouro_end() + ", " + p.getNumero_end() + " - " + p.getBairro_end() + ", " + p.getCidade_end() + " - " +
        p.getEstado_end());
        lblComplemento.setText(p.getComplemento_end());
    }

    public void fecharOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnFechar.getScene().getWindow();
        stage.close();
    }


}
