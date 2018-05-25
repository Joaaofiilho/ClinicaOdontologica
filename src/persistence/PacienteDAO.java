package persistence;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import beans.Agenda;
import beans.Consulta;
import beans.Paciente;
import javafx.collections.ObservableList;


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
            stmt.setDate(4, new java.sql.Date(p.getNascimento().getTime()));
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



    public static void alterar(Paciente paciente){
        try {
            alterarPacienteBD(paciente);
            Agenda.atualizarPaciente();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void alterarPacienteBD(Paciente p) throws Exception{
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = Conexao.getConnection();

            stmt = con.prepareStatement("update paciente set nome=?, telefone=?, nascimento=?, email=?, sexo=?, logradouro_end=?, numero_end=?, complemento_end=?, bairro_end=?, cidade_end=?, estado_end=? where cpf=?");

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getTelefone());
            stmt.setDate(3, new java.sql.Date(p.getNascimento().getTime()));
            stmt.setString(4, p.getEmail());
            stmt.setString(5, Character.toString(p.getSexo()));
            stmt.setString(6, p.getLogradouro_end());
            stmt.setInt(7, p.getNumero_end());
            stmt.setString(8, p.getComplemento_end());
            stmt.setString(9, p.getBairro_end());
            stmt.setString(10, p.getCidade_end());
            stmt.setString(11, p.getEstado_end());
            stmt.setString(12,p.getCpf());

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

    public static List<Paciente> buscarTudo() throws Exception{
        Connection con = null;
        PreparedStatement stmt = null;
        ArrayList<Paciente> pacientes = new ArrayList<>();
        try {
            con = Conexao.getConnection();

            stmt = con.prepareStatement("select * from paciente");

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                Date nascimento = rs.getDate("nascimento");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");
                String logradouro_end = rs.getString("logradouro_end");
                int numero_end = rs.getInt("numero_end");
                String complemento_end = rs.getString("complemento_end");
                String bairro_end = rs.getString("bairro_end");
                String cidade_end = rs.getString("cidade_end");
                String estado_end = rs.getString("estado_end");
                String sexo = rs.getString("sexo");
                char sexo2 = sexo.charAt(0);
                pacientes.add(new Paciente(nome, cpf, nascimento, telefone, email, logradouro_end, numero_end, complemento_end, bairro_end, cidade_end, estado_end, sexo2));
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

    public static Paciente buscarPorCpf(String cpf) throws Exception{
        Connection con = null;
        PreparedStatement stmt = null;
        Paciente p = null;
        try {
            con = Conexao.getConnection();

            stmt = con.prepareStatement("select * from paciente where cpf=?");
            stmt.setString(1, cpf);



            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String nome = rs.getString("nome");
                String cpff = rs.getString("cpf");
                Date nascimento = rs.getDate("nascimento");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");
                String logradouro_end = rs.getString("logradouro_end");
                int numero_end = rs.getInt("numero_end");
                String complemento_end = rs.getString("complemento_end");
                String bairro_end = rs.getString("bairro_end");
                String cidade_end = rs.getString("cidade_end");
                String estado_end = rs.getString("estado_end");
                String sexo = rs.getString("sexo");
                char sexo2 = sexo.charAt(0);
                p = new Paciente(nome, cpff, nascimento, telefone, email, logradouro_end, numero_end, complemento_end, bairro_end, cidade_end, estado_end, sexo2);
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
        return p;
    }

    public static ArrayList<Consulta> buscarConsultas(String cpf) throws Exception{
        Connection con = null;
        PreparedStatement stmt = null;
        ArrayList<Consulta> consultas = new ArrayList<Consulta>();
        try {
            con = Conexao.getConnection();

            stmt = con.prepareStatement("select * from consulta where cpf_paciente=?");
            stmt.setString(1, cpf);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String cpf_paciente = rs.getString("cpf_paciente");
                String horario_completo = rs.getString("horario_completo");
                String descricao = rs.getString("descricao");
                double valor = rs.getDouble("valor");
                Date data = new Date(rs.getTimestamp("data").getTime());

                Consulta c = new Consulta(data, PacienteDAO.buscarPorCpf(cpf_paciente), horario_completo,
                        descricao,valor, id);
                consultas.add(c);
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
        return consultas;
    }

}
