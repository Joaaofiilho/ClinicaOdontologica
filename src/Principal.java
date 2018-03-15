import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Principal extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/TelaPrincipal.fxml"));
        primaryStage.setTitle("Clínica Odontologica");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
//        Paciente p = new Paciente("a", "b", "c", "d", "e", "f", 'm');
//        PacienteDAO dao = new PacienteDAO();
//
//        try {
//            dao.inserir(p);
//            System.out.println(dao.buscarPorCpf(p.getCpf()).getNome());
//        }catch (Exception e){
//            System.out.println("Algo ocorreu");
//        }

//}
//}