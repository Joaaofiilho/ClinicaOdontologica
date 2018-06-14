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

    public ListView<Procedimento> lstvCadastrados;
    public ListView<Procedimento> lstvUtilizados;
    ObservableList<Procedimento> procedimentosUtilizados = FXCollections.observableArrayList();
    public Button btnCriar;
    public Button btnExcluir;
    public Button btnSelecionar;
    public Button btnRetirar;
    public Button btnSalvar;
    public Button btnCancelar;


    private MainApp mainApp;


    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }


    public void initialize(){

        Agenda.atualizarProcedimento();
        lstvCadastrados.setItems(Agenda.getProcedimentos());


        lstvUtilizados.setItems(procedimentosUtilizados);
    }


    public void criarNovo(ActionEvent actionEvent) {
        CadastroProcedimento.setModificando(false);
        mainApp.exibirCadastroProcedimento();
        atualizar();
    }

    public void excluir(ActionEvent actionEvent) {
        try {
            ProcedimentoDAO.excluir(lstvCadastrados.getSelectionModel().getSelectedItem().getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        atualizar();
    }

    public void atualizar(){
        Agenda.atualizarProcedimento();

        lstvCadastrados.refresh();
        lstvUtilizados.refresh();
    }

    public void selecionar(ActionEvent actionEvent) {
        if(!lstvCadastrados.getSelectionModel().isEmpty())
            procedimentosUtilizados.add(lstvCadastrados.getSelectionModel().getSelectedItem());

    }

    public void retirar(ActionEvent actionEvent) {
        procedimentosUtilizados.remove(lstvUtilizados.getSelectionModel().getSelectedItem());
    }

    public void salvar(ActionEvent actionEvent) {

    }

    public void cancelar(ActionEvent actionEvent) {

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
        atualizar();
    }
}
