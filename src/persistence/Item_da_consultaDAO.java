package persistence;

import beans.Procedimento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Item_da_consultaDAO {
    //Todos os metodos CRUD na consulta

    public static void inserirProcedimentos(int idCons, int idProc){
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            //Nesse codigo ele pega vários ids de procedimento e coloca tudo em uma consulta.
            con = Conexao.getConnection();

            stmt = con.prepareStatement("insert into item_da_consulta values (?,?)");

            stmt.setInt(1, idProc);
            stmt.setInt(2, idCons);

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

//    public static void alterarConsulta(int idCons, int... idsProc){
//        Connection con = null;
//        PreparedStatement stmt = null;
//
//        try {
//            con = Conexao.getConnection();
//            //Primeiramente limpo todos os procedimentos da consulta X no bando de dadoss
//            stmt = con.prepareStatement("delete from item_da_consulta where Consulta_id=?");
//            stmt.setInt(1, idCons);
//
//            stmt.executeUpdate();
//
//            //Depois insiro os novos elementos
//            inserirProcedimentos(idCons, idsProc);
//        } catch (SQLException e1) {
//            e1.printStackTrace();
//        } finally {
//            try {
//                //fechar statement e connection
//                if (stmt != null)
//                    stmt.close();
//                if (con != null)
//                    con.close();
//            } catch (SQLException e1) {
//                e1.printStackTrace();
//            }
//        }
//    }

    public static ArrayList<Procedimento> buscarProcedimentos(int idCons){
        Connection con = null;
        PreparedStatement stmt = null;

        ArrayList<Procedimento> procs = new ArrayList<>();

        try {
            con = Conexao.getConnection();

            stmt = con.prepareStatement("select Procedimento_id from item_da_consulta where Consulta_id=?");
            stmt.setInt(1, idCons);

            ResultSet rs = stmt.executeQuery();
            while(rs.next())
                procs.add(ProcedimentoDAO.buscarPorID(rs.getInt("Procedimento_id")));

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
        return procs;
    }

    public static void excluirProcedimento(int idCons, int idProc){
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = Conexao.getConnection();
            //Primeiramente limpo todos os procedimentos da consulta X no bando de dadoss

            //DELETE FROM `clinicaodontologica`.`item_da_consulta` WHERE (`Procedimento_id` = '5') and (`Consulta_id` = '6');
            stmt = con.prepareStatement("delete from clinicaodontologica.item_da_consulta where Consulta_id=? and Procedimento_id=?");

            stmt.setInt(1, idCons);
            stmt.setInt(2, idProc);

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
