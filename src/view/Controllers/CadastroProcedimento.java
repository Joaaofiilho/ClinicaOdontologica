package view.Controllers;

import app.MainApp;
import beans.Agenda;
import beans.Procedimento;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import persistence.ProcedimentoDAO;

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

    private ProcedimentoDAO ProDAO = new ProcedimentoDAO();

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    //Metodos
    public void btnCancelarOnAction (ActionEvent event){
        //TODO fechar a janela

        mainApp.exibirTelaPrincipal();
    }

    public void btnConfirmarOnAction (ActionEvent event) throws Exception{
        //TODO validar os dados inseridos
        double valor = Double.parseDouble(txtFieldValor.getText());
        int duracao = Integer.parseInt(txtFieldDuracao.getText());
        Procedimento p = new Procedimento(txtFieldTitulo.getText(), txtFieldDescricao.getText(), valor, duracao);
        Agenda.adicionarProcedimento(p);

        Stage stage = (Stage) btnConfirmar.getScene().getWindow();
        stage.close();
        
//        mainApp.exibirTelaPrincipal();
    }
}
