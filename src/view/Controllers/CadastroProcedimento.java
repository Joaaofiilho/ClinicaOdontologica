package view.Controllers;

import app.MainApp;
import beans.Agenda;
import beans.Procedimento;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CadastroProcedimento {
    //Textfields
    public TextField txtFieldTitulo;
    public TextField txtFieldDescricao;
    public TextField txtFieldValor;
    public TextField txtFieldDuracao;

    //Botoes
    public Button btnCancelar;
    public Button btnConfirmar;

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    //Metodos
    public void btnCancelarOnAction (ActionEvent event){
        //TODO fechar a janela

        mainApp.exibirTelaPrincipal();
    }

    public void btnConfirmarOnAction (ActionEvent event){
        //TODO validar os dados inseridos
        double valor = Double.parseDouble(txtFieldValor.getText());
        int duracao = Integer.parseInt(txtFieldDuracao.getText());
        Procedimento p = new Procedimento(txtFieldTitulo.getText(), txtFieldDescricao.getText(), valor, duracao);
        Agenda.adicionarProcedimento(p);

        mainApp.exibirTelaPrincipal();
    }
}
