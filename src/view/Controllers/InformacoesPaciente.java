package view.Controllers;

import app.MainApp;
import beans.Paciente;
import javafx.scene.control.Label;

public class InformacoesPaciente {

    public Label lblNome;
    public Label lblCpf;
    public Label lblEmail;
    public Label lblTe;
    public Label lblSexo;
    public Label lblEnd;


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
    }

    ////TODO----------Fazer o botão OK fechar a janela.

}
