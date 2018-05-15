package persistence;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Paciente;


public class PacienteDAO {
    //Métodos

    public static void inserir(Paciente p) throws Exception{
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = Conexao.getConnection();
            stmt = con.prepareStatement("insert into paciente values (?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, p.getCpf());
            stmt.setString(2, p.getNome());
            stmt.setString(3, p.getTelefone());
            stmt.setString(4, p.getNascimento());
            stmt.setString(5, p.getEmail());
            stmt.setString(6, Character.toString(p.getSexo()));
            stmt.setString(7, p.getLogradouro_end());
            stmt.setInt(8, p.getNumero_end());
            stmt.setString(9, p.getComplemento_end());
            stmt.setString(10, p.getBairro_end());
            stmt.setString(11, p.getCidade_end());
            stmt.setString(12, p.getEstado_end());

            stmt.executeUpdate();
        } catch (SQLException var15) {
            var15.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException var14) {
                var14.printStackTrace();
            }
        }
    }

    public static void alterar(Paciente p) throws Exception{

    }

    public static void excluir(int index) throws Exception{

    }

    public static Paciente buscarPorCpf(String cpf) throws Exception{

    }

    public ArrayList<Paciente> buscarPorNome(String nome) throws Exception{

    }

    private static void gravarDados() throws Exception {

    }

}
