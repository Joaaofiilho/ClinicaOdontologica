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

        procedimentosUtilizadosBD.addAll(Item_da_consultaDAO.buscarProcedimentos(CadastroConsulta.getIdConsulta()));


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


        if(!(lstvCadastrados.getSelectionModel().isEmpty())){

            Procedimento pAux = lstvCadastrados.getSelectionModel().getSelectedItem();

            if(!(procedimentosAdiconadosParaSalvar.contains(pAux))){
                procedimentosUtilizados.add(pAux);
                procedimentosAdiconadosParaSalvar.add(pAux);


//                if (procedimentosRetirarParaSalvar.contains(pAux) BUGA
                for (int i = 0; i < procedimentosRetirarParaSalvar.size(); i++) {
                    Procedimento p = procedimentosRetirarParaSalvar.get(i);

                    if(pAux.getId() == p.getId()){
                        procedimentosRetirarParaSalvar.remove(i);
                    }
                }

            }

        }



    }

    public void retirar(ActionEvent actionEvent) {

        //Funciona

        if(!(lstvUtilizados.getSelectionModel().isEmpty())){
            if (procedimentosAdiconadosParaSalvar.contains(lstvUtilizados.getSelectionModel().getSelectedItem())) {
                procedimentosAdiconadosParaSalvar.remove(lstvUtilizados.getSelectionModel().getSelectedItem());
            }

            if(!(procedimentosRetirarParaSalvar.contains(lstvUtilizados.getSelectionModel().getSelectedItem()))) {
                procedimentosRetirarParaSalvar.add(lstvUtilizados.getSelectionModel().getSelectedItem());
            }
            procedimentosUtilizados.remove(lstvUtilizados.getSelectionModel().getSelectedItem());
        }

//        procedimentosRetirarParaSalvar.add(lstvUtilizados.getSelectionModel().getSelectedItem());
//        Item_da_consultaDAO.excluirProcedimento(CadastroConsulta.getIdConsulta(),lstvUtilizados.getSelectionModel().getSelectedItem().getId());

    }

    public void salvar(ActionEvent actionEvent) {

        if (!(procedimentosUtilizadosBD.isEmpty())) {

            for (int i = 0; i < procedimentosUtilizadosBD.size(); i++) {
                Procedimento pBD = procedimentosUtilizadosBD.get(i);

                for (int j = 0; j < procedimentosAdiconadosParaSalvar.size(); j++) {
                    Procedimento pNew = procedimentosAdiconadosParaSalvar.get(j);

                    if (pBD.getId() == pNew.getId()) {
                        procedimentosAdiconadosParaSalvar.remove(j);
                        break;
                    }
                }
            }
        }


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

        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
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
