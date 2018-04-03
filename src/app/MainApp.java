package app;

import beans.Paciente;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.Controllers.CadastroPaciente;
import view.Controllers.RootLayout;
import view.Controllers.TelaPrincipal;

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

    public void exibirCadastroPaciente() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/CadastroPaciente.fxml"));
            AnchorPane telaCadastroPacientePane = (AnchorPane) loader.load();

            rootLayout.setCenter(telaCadastroPacientePane);

            CadastroPaciente controller = loader.getController();
            controller.setMainApp(this);
        }catch (IOException e){
            System.out.printf("TRETA");
        }
    }

    public void exibirTelaPrincipal(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/TelaPrincipal.fxml"));
            AnchorPane telaPrincipalPane = (AnchorPane) loader.load();

            rootLayout.setCenter(telaPrincipalPane);

            TelaPrincipal controller = loader.getController();
            controller.setMainApp(this);

        }catch (IOException e){
            System.out.printf("TRETA");
        }
    }
}
