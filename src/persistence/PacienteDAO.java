package persistence;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = Conexao.getConnection();

            stmt = con.prepareStatement("update consulta set (nome, telefone, nascimento, email, sexo, logradouro_end, numero_end, complemento_end, bairro_end, cidade_end, estado_end) values (?,?,?,?,?) where cpf=?");

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getTelefone());
            stmt.setString(3, p.getNascimento());
            stmt.setString(4, p.getEmail());
            stmt.setString(5, Character.toString(p.getSexo()));
            stmt.setString(6, p.getLogradouro_end());
            stmt.setInt(7, p.getNumero_end());
            stmt.setString(8, p.getComplemento_end());
            stmt.setString(9, p.getBairro_end());
            stmt.setString(10, p.getCidade_end());
            stmt.setString(11, p.getEstado_end());

            stmt.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            try {
                //fechar statement e connection
                if (stmt != null)
                    stmt.close();
                if (con != null)
                    con.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static void excluir(String cpf) throws Exception{
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = Conexao.getConnection();

            stmt = con.prepareStatement("delete from paciente where cpf=?");
            stmt.setString(1, cpf);

            stmt.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            try {
                //fechar statement e connection
                if (stmt != null)
                    stmt.close();
                if (con != null)
                    con.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static Paciente buscarPorCpf(String cpf) throws Exception{
        Connection con = null;
        PreparedStatement stmt = null;
        ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
        try {
            con = Conexao.getConnection();

            stmt = con.prepareStatement("select * from paciente where cpf=?");
            stmt.setString(1, cpf);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String nome = rs.getString("nome");
                String cpff = rs.getString("cpf");
                String nascimento = rs.getString("nascimento");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");
                String logradouro_end = rs.getString("logradouro_end");
                String numero_end = rs.getString("numero_end");
                String complemento_end = rs.getString("complemento_end");
                String bairro_end = rs.getString("bairro_end");
                String cidade_end = rs.getString("cidade_end");
                //char sexo = rs.getChar????("sexo");


                Paciente p = new Paciente(nome, cpff, nascimento, telefone, email, logradouro_end, numero_end, complemento_end, bairro_end, cidade_end, estado_end, sexo);
                pacientes.add(p);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (con != null)
                    con.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return pacientes;
    }

}
