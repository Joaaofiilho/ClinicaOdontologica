package view.Controllers;

import app.MainApp;
import beans.Paciente;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import util.RelatorioUtil;

import javax.swing.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InformacoesPaciente {

    public Label lblNome;
    public Label lblCpf;
    public Label lblEmail;
    public Label lblTe;
    public Label lblSexo;
    public Label lblEnd;
    public Label lblComplemento;
    public Button btnFechar;
    public Button btnImprimirConsultas;



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

    public void imprimirConsultas(ActionEvent actionEvent){

        HashMap<String, Object> params = new HashMap<String, Object>();

        params.put("cpf_busca", lblCpf.getText());

        try {

            RelatorioUtil.gerarPDF(params, "relatorioConsultasPaciente");
            RelatorioUtil.exibirRelatorio(params,"relatorioConsultasPaciente");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar o relatório!");
            Logger.getLogger(InformacoesPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
}
