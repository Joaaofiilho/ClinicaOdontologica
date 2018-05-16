package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static Connection getConnection() throws SQLException {
        //
        //serverTimezone=UTC-3
        return DriverManager.getConnection("jdbc:mysql://localhost/clinicaodontologica?useTimezone=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC","root","12345678");
    }
}
