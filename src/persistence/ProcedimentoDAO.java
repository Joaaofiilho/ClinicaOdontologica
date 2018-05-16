package persistence;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Procedimento;


public class ProcedimentoDAO {

    public static void inserir(Procedimento p) throws Exception { //Adiciona um procedimento ao Arraylist e ao txt.
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = Conexao.getConnection();

            stmt = con.prepareStatement("insert into procedimento values (null,?,?,?,?)");

            stmt.setString(1, p.getTitulo());
            stmt.setString(2, p.getDescricao());
            stmt.setDouble(3, p.getValor());
            stmt.setInt(4, p.getDuracao());

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

    public static void alterar(Procedimento p) throws Exception{ //Vai alterar uma informação de um procedimento
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = Conexao.getConnection();

            stmt = con.prepareStatement("update procedimento set (titulo, descricao, valor, duracao) values (?,?,?,?) where id=?");

            stmt.setString(1, p.getTitulo());
            stmt.setString(2, p.getDescricao());
            stmt.setDouble(3, p.getValor());
            stmt.setInt(4, p.getDuracao());

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

    public static void excluir(int id) throws Exception{
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = Conexao.getConnection();

            stmt = con.prepareStatement("delete from procedimento where id=?");
            stmt.setInt(1, id);

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

    public static Procedimento buscarPorID(int id) throws NullPointerException{
        Connection con = null;
        PreparedStatement stmt = null;
        Procedimento proc = null;
        try {
            con = Conexao.getConnection();

            stmt = con.prepareStatement("select * from procedimento where id=?");
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                //TODO!

                //proc = new procedimento blah blah
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
        return proc;
    }

    public static ArrayList<Procedimento> buscarProcedimento(String procedimento){
        return null;
    }

    public int tamanho(){
        //return procedimentos.size();
        //A gente precisa disso?
        return 0;
    }

    public void exibirTodosProcedimentos(){
        //E disso?
    }

}
