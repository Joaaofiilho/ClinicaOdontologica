package view.Controllers;

import app.MainApp;
import beans.Agenda;
import beans.Procedimento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import persistence.Item_da_consultaDAO;
import persistence.ProcedimentoDAO;

import java.util.ArrayList;

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

    private ArrayList<Procedimento> procedimentosUtilizadosBD = new ArrayList<>();

    private ArrayList<Procedimento> procedimentosAdiconadosParaSalvar = new ArrayList<>();
    private ArrayList<Procedimento> procedimentosRetirarParaSalvar = new ArrayList<>();

    private MainApp mainApp;


    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }


    public void initialize(){

        Agenda.atualizarProcedimento();
        lstvCadastrados.setItems(Agenda.getProcedimentos());


        procedimentosUtilizados.setAll(Item_da_consultaDAO.buscarProcedimentos(CadastroConsulta.getIdConsulta()));

        //teste
        procedimentosUtilizadosBD.addAll(procedimentosUtilizados);

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

        if(!lstvCadastrados.getSelectionModel().isEmpty()){
            if(!procedimentosAdiconadosParaSalvar.contains(lstvCadastrados.getSelectionModel().getSelectedItem())){
                procedimentosUtilizados.add(lstvCadastrados.getSelectionModel().getSelectedItem());
                procedimentosAdiconadosParaSalvar.add(lstvCadastrados.getSelectionModel().getSelectedItem());
            }

            if(procedimentosRetirarParaSalvar.contains(lstvCadastrados.getSelectionModel().getSelectedItem()))
                procedimentosRetirarParaSalvar.remove(lstvCadastrados.getSelectionModel().getSelectedItem());


        }

    }

    public void retirar(ActionEvent actionEvent) {

        if (procedimentosAdiconadosParaSalvar.contains(lstvUtilizados.getSelectionModel().getSelectedItem()))
            procedimentosAdiconadosParaSalvar.remove(lstvUtilizados.getSelectionModel().getSelectedItem());

        if(!procedimentosRetirarParaSalvar.contains(lstvUtilizados.getSelectionModel().getSelectedItem()))
            procedimentosRetirarParaSalvar.add(lstvUtilizados.getSelectionModel().getSelectedItem());

//        procedimentosRetirarParaSalvar.add(lstvUtilizados.getSelectionModel().getSelectedItem());


//        Item_da_consultaDAO.excluirProcedimento(CadastroConsulta.getIdConsulta(),lstvUtilizados.getSelectionModel().getSelectedItem().getId());
        procedimentosUtilizados.remove(lstvUtilizados.getSelectionModel().getSelectedItem());

    }

    public void salvar(ActionEvent actionEvent) {

        if (!procedimentosUtilizadosBD.isEmpty()){

            for (Procedimento pSalvos : procedimentosUtilizadosBD) {
                for (Procedimento pSalvar : procedimentosAdiconadosParaSalvar) {
                    if (pSalvar.getId() == pSalvos.getId()) {
                        procedimentosAdiconadosParaSalvar.remove(pSalvar);
                    }
                }
            }
        }

//        for (Procedimento p: procedimentosUtilizados) {
//            for (Procedimento pRetirar : procedimentosRetirarParaSalvar){
//                if (p.getId() == pRetirar.getId()){
//                    procedimentosRetirarParaSalvar.remove(pRetirar);
//                }
//            }
//        }
//
//        for (Procedimento pSalvar: procedimentosAdiconadosParaSalvar) {
//            for (Procedimento pRetirar: procedimentosRetirarParaSalvar) {
//                if (pSalvar.getId() == pRetirar.getId()){
//                    procedimentosAdiconadosParaSalvar.remove(pSalvar);
//                }
//            }
//        }

        for (Procedimento pSalvar: procedimentosAdiconadosParaSalvar) {
            Item_da_consultaDAO.inserirProcedimentos(CadastroConsulta.getIdConsulta(),pSalvar.getId());
        }

        for (Procedimento p: procedimentosRetirarParaSalvar) {
            Item_da_consultaDAO.excluirProcedimento(CadastroConsulta.getIdConsulta(),p.getId());
        }

        Stage stage = (Stage) btnSalvar.getScene().getWindow();
        stage.close();

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
