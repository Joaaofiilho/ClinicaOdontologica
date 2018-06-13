package view.Controllers;

import app.MainApp;
import beans.Agenda;
import beans.Procedimento;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Text;
import persistence.ProcedimentoDAO;

public class CadastroProcedimento {
    //Textfields
    public TextField txtFieldTitulo;
    public TextField txtFieldDescricao;
    public TextField txtFieldValor;
    public TextField txtFieldDuracao;
    public TextField txtfID;

    private static boolean modificando;

    //Botoes
    public Button btnCancelar;
    public Button btnConfirmar;

    private MainApp mainApp;

    private ProcedimentoDAO ProDAO = new ProcedimentoDAO();

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    //Metodos


    public void preencher(Procedimento p){
        txtFieldTitulo.setText(p.getTitulo());
        txtFieldDescricao.setText(p.getDescricao());
        txtFieldValor.setText(String.valueOf(p.getValor()));
        txtFieldDuracao.setText(String.valueOf(p.getDuracao()));
        txtfID.setText(String.valueOf(p.getId()));
    }


    public void btnCancelarOnAction (ActionEvent event){
        
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();

    }

    public void btnConfirmarOnAction (ActionEvent event) throws Exception{
        //TODO validar os dados inseridos

        String titulo = txtFieldTitulo.getText();
        String descrica = txtFieldDescricao.getText();
        double valor = Double.parseDouble(txtFieldValor.getText());
        int duracao = Integer.parseInt(txtFieldDuracao.getText());
        int id = Integer.parseInt(txtfID.getText());

        Procedimento p = new Procedimento(txtFieldTitulo.getText(), txtFieldDescricao.getText(), valor, duracao,id);

        if (isModificando()){
            ProcedimentoDAO.alterar(p);
        }else{
            Agenda.adicionarProcedimento(p);
        }

        Stage stage = (Stage) btnConfirmar.getScene().getWindow();


        stage.close();
//        mainApp.exibirTelaPrincipal();
    }


    public static boolean isModificando() {
        return modificando;
    }

    public static void setModificando(boolean modificando) {
        CadastroProcedimento.modificando = modificando;
    }
}
