package app;

import beans.Agenda;
import beans.Paciente;
import beans.Consulta;
import beans.Procedimento;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import persistence.ConsultaDAO;
import persistence.PacienteDAO;
import persistence.ProcedimentoDAO;
import view.Controllers.*;

import javax.swing.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Clínica Odontológica");

        Agenda.init();
        for(Procedimento p: ProcedimentoDAO.buscarTudo()){
            System.out.println(p.getId());
        }

        initRoot();
    }

    private void initRoot(){
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/RootLayout.fxml"));
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);

            primaryStage.show();

            RootLayout controller = loader.getController();
            controller.setMainApp(this);

            exibirTelaPrincipal();
        }catch (IOException e){
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    //Exibir telas
    public void exibirTelaPrincipal(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/TelaPrincipal.fxml"));

            AnchorPane telaPrincipalPane = (AnchorPane) loader.load();

            rootLayout.setCenter(telaPrincipalPane);

            TelaPrincipal controller = loader.getController();
            controller.setMainApp(this);

        }catch (IOException e){
            e.printStackTrace();
//            System.err.println("Erro: Falha ao exibir tela principal (" + e.getMessage() + ")");
        }
    }

    public void exibirCadastroPaciente() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/CadastroPaciente.fxml"));
            AnchorPane telaCadastroPacientePane = (AnchorPane) loader.load();

            rootLayout.setCenter(telaCadastroPacientePane);

            CadastroPaciente controller = loader.getController();
            controller.setMainApp(this);
        }catch (IOException e){
            System.err.println("Erro: Falha ao exibir a tela de cadastro de paciente.");
        }
    }




    //Estou modificando essa daqui
    public void exibirPacienteInfo(Paciente paciente) {
        try {
            Stage info = new Stage();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/InformacoesPaciente.fxml"));


            AnchorPane telaInfoPaci = (AnchorPane) loader.load();

            InformacoesPaciente controller = loader.getController();
            controller.exibir(paciente);


            info.setScene(new Scene(telaInfoPaci));
            info.setTitle("Informações sobre o pacientes");


            info.initOwner(primaryStage);
            info.initModality(Modality.APPLICATION_MODAL);
            info.showAndWait();



//            rootLayout.setCenter(telaCadastroPacientePane);
//            controller.setMainApp(this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void exibirCadastroPaciente(Paciente paciente) {
        try {


            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/CadastroPaciente.fxml"));
            AnchorPane telaCadastroPacientePane = (AnchorPane) loader.load();

            CadastroPaciente controller = loader.getController();
            controller.preencher(paciente);

            rootLayout.setCenter(telaCadastroPacientePane);
            controller.setMainApp(this);
        }catch (IOException e){
            System.err.println("Erro: Falha ao exibir a tela de modificação de paciente.");
        }
    }

    public void exibirCadastroConsulta(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/CadastroConsulta.fxml"));
            AnchorPane telaPrincipalPane = (AnchorPane) loader.load();

            rootLayout.setCenter(telaPrincipalPane);

            CadastroConsulta controller = loader.getController();
            controller.setMainApp(this);
        }catch (IOException e){
            System.err.println("Erro: Falha ao exibir a tela de cadastro de consulta.");
        }
    }

    public void exibirCadastroConsulta(Consulta consulta){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/CadastroConsulta.fxml"));
            AnchorPane telaPrincipalPane = (AnchorPane) loader.load();

            CadastroConsulta controller = loader.getController();
            controller.preencher(consulta);

            rootLayout.setCenter(telaPrincipalPane);
            controller.setMainApp(this);
        }catch (IOException e){
            System.err.println("Erro: Falha ao exibir a tela de modificação de paciente.");
        }
    }

    public void exibirCadastroProcedimento(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/CadastroProcedimento.fxml"));
            AnchorPane telaPrincipalPane = (AnchorPane) loader.load();

            rootLayout.setCenter(telaPrincipalPane);

            CadastroProcedimento controller = loader.getController();
            controller.setMainApp(this);
        }catch (IOException e){
            System.err.println("Erro: Falha ao exibir a tela de cadastro de procedimento.");
        }
    }
}
