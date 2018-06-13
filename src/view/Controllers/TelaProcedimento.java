package view.Controllers;

import app.MainApp;
import beans.Agenda;
import beans.Procedimento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import persistence.ProcedimentoDAO;

public class TelaProcedimento {

    public  ListView<Procedimento> lstvCadastrados;
    public  ListView<Procedimento> lstvUtilizados;
    public Button btnCriar;
    public Button btnExcluir;
    public Button btnSelecionar;
    public Button btnRetirar;
    public Button btnSalvar;
    public Button btnAtualizar;

    private MainApp mainApp;


    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    public void atualizarListView(){
        lstvUtilizados.refresh();
        lstvCadastrados.refresh();
    }


    public void initialize(){

        ObservableList<Procedimento> procedimentosTotal = FXCollections.observableArrayList();

        try {
            procedimentosTotal.addAll(Agenda.getProcedimentos());
        }catch (Exception e){
            e.printStackTrace();
        }

        lstvCadastrados.setItems(procedimentosTotal);


    }




    public void criarNovo(ActionEvent actionEvent) {
        CadastroProcedimento.setModificando(false);
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

    public void atualizar(ActionEvent actionEvent){
        Agenda.atualizarProcedimento();
        lstvCadastrados.setItems(Agenda.getProcedimentos());

        lstvCadastrados.refresh();
        lstvUtilizados.refresh();
    }

    public void modificiar(ActionEvent actionEvent) {
        CadastroProcedimento.setModificando(true);
        mainApp.modificarCadastroProcedimento(ProcedimentoDAO.buscarPorID(lstvCadastrados.getSelectionModel().getSelectedItem().getId()));
    }
}
