package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static Connection getConnection() throws SQLException {
        //
        //serverTimezone=UTC-3

        String usuario = "root";
        String senha = "root";

        return DriverManager.getConnection("jdbc:mysql://localhost/clinicaodontologica?useTimezone=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC",usuario,senha);
    }
}
