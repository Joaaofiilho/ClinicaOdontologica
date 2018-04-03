import beans.Paciente;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.Controllers.CadastroPaciente;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Principal extends Application {
    private BorderPane rootLayout;
    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/TelaPrincipal.fxml"));
        primaryStage.setTitle("Clínica Odontologica");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        stage = primaryStage;
        primaryStage.show();
    }

    public void exibirCadastroPaciente() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("view/CadastroPaciente.fxml"));
            stage.setTitle("Clínica Odontologica");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e){

        }
    }

    //Teste teste

    public static void main(String[] args) {
        launch(args);
    }
}
