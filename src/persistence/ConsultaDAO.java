package persistence;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import beans.Agenda;
import beans.Consulta;

public class ConsultaDAO {
    //Static

    //Métodos

    public static void inserir(Consulta c) throws Exception{
        Connection con = null;
        PreparedStatement stmt = null;
        ArrayList<Consulta> consultas = new ArrayList<Consulta>();
        try {
            con = Conexao.getConnection();

            stmt = con.prepareStatement("insert into consulta values (null,?,?,?,?,?)");

            stmt.setString(1, c.getPaciente().getCpf());
            stmt.setString(2, c.getHorarioCompleto());
            stmt.setString(3, c.getDescricao());
            stmt.setDouble(4, c.getValor());
            stmt.setTimestamp(5, new java.sql.Timestamp(c.getData().getTime()));
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

    }

    public static void excluir(int id) throws Exception{

    }

    public static ArrayList<Consulta> buscarPorCpf(String cpf) throws Exception{

    }

    public static ArrayList<Consulta> buscarPorData(String data) throws Exception{

    }

    public static Consulta buscarPorId(int id){

    }
}
