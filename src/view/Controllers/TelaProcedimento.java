package view.Controllers;

import app.MainApp;
import beans.Procedimento;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class TelaProcedimento {

    public ListView<Procedimento> lstvCadastrados;
    public ListView<Procedimento> lstvUtilizados;
    public Button btnCriar;
    public Button btnExcluir;
    public Button btnSelecionar;
    public Button btnRetirar;
    public Button btnSalvar;


    private MainApp mainApp;


    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }


    public void criarNovo(ActionEvent actionEvent) {
        mainApp.exibirCadastroProcedimento();
    }

    public void excluir(ActionEvent actionEvent) {
    }

    public void selecionar(ActionEvent actionEvent) {
    }

    public void retirar(ActionEvent actionEvent) {
    }

    public void salvar(ActionEvent actionEvent) {
    }
}
