package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/ClinicaOdontologica-bd?useTimezone=true&serverTimezone=UTC-3","root","12345678");
    }
}
