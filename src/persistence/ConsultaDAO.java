package persistence;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import beans.Agenda;
import beans.Consulta;
import beans.Paciente;

public class ConsultaDAO {
    //Static

    //Métodos
    public static void inserir(Consulta c) throws Exception{
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = Conexao.getConnection();

            stmt = con.prepareStatement("insert into consulta values (null,?,?,?,?,?)");

            stmt.setString(1, c.getPaciente().getCpf());
            stmt.setString(2, c.getHorarioCompleto());
            stmt.setString(3, c.getDescricao());
            stmt.setDouble(4, c.getValor());
            stmt.setDate(5, new java.sql.Date(c.getData().getTime()));

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

    public static void alterar(Consulta c) throws Exception{
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = Conexao.getConnection();

            stmt = con.prepareStatement("update consulta set cpf_paciente=?,horario_completo=?,descricao=?,valor=?,data_consulta=? where id=?");

            stmt.setString(1, c.getPaciente().getCpf());
            stmt.setString(2, c.getHorarioCompleto());
            stmt.setString(3, c.getDescricao());
            stmt.setDouble(4, c.getValor());
            stmt.setDate(5, new java.sql.Date(c.getData().getTime()));
            stmt.setInt(6, c.getId());

            stmt.executeUpdate();
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
    }

    public static List<Consulta> buscarTudo() throws Exception{
        Connection con = null;
        PreparedStatement stmt = null;
        ArrayList<Consulta> consultas = new ArrayList<Consulta>();
        try {
            con = Conexao.getConnection();

            stmt = con.prepareStatement("select * from consulta");

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String cpf_paciente = rs.getString("cpf_paciente");
                String horario_completo = rs.getString("horario_completo");
                String descricao = rs.getString("descricao");
                double valor = rs.getDouble("valor");
                Date dataConsulta = new Date(rs.getDate("data_consulta").getTime());

                consultas.add(new Consulta(dataConsulta, PacienteDAO.buscarPorCpf(cpf_paciente), horario_completo,
                        descricao,valor, id));
            }
        } catch (Exception e1) {
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

    public static List<Consulta> buscarPorData(Date data) throws Exception{
        Connection con = null;
        PreparedStatement stmt = null;
        ArrayList<Consulta> consultas = new ArrayList<Consulta>();



        try {
            con = Conexao.getConnection();

            stmt = con.prepareStatement("select * from consulta where data_consulta=?");


            //Modoficado aqui para ele pegar a data com a conversão corretamente.
            stmt.setDate(1, java.sql.Date.valueOf(data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
//            stmt.setDate(1, (java.sql.Date)data);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String cpf_paciente = rs.getString("cpf_paciente");
                String horario_completo = rs.getString("horario_completo");
                String descricao = rs.getString("descricao");
                double valor = rs.getDouble("valor");
                Date dataConsulta = new Date(rs.getDate("data_consulta").getTime());

                Consulta c = new Consulta(dataConsulta, PacienteDAO.buscarPorCpf(cpf_paciente), horario_completo,
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

    public static Consulta buscarPorId(int id){
        Connection con = null;
        PreparedStatement stmt = null;
        Consulta consulta = null;
        try {
            con = Conexao.getConnection();

            stmt = con.prepareStatement("select * from consulta where id=?");
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){

                System.out.println(rs.getDate("data_consulta").toString());

                int idConsulta = rs.getInt("id");
                String cpf_paciente = rs.getString("cpf_paciente");
                String horario_completo = rs.getString("horario_completo");
                String descricao = rs.getString("descricao");
                double valor = rs.getDouble("valor");

                Date dataConsulta = new java.util.Date(rs.getDate("data_consulta").getTime());

                consulta = new Consulta(dataConsulta, PacienteDAO.buscarPorCpf(cpf_paciente), horario_completo,
                        descricao,valor, idConsulta);
            }
        }catch (Exception e) {
            e.printStackTrace();
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
        return consulta;
    }

    public static void excluir(int id) throws Exception{
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = Conexao.getConnection();

            stmt = con.prepareStatement("delete from consulta where id=?");
            stmt.setInt(1, id);

            stmt.executeUpdate();
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
    }
}
