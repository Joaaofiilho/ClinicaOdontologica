package beans;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistence.ConsultaDAO;
import persistence.PacienteDAO;
import persistence.ProcedimentoDAO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Agenda {
    private static Calendar calendario;
    private static ObservableList<Paciente> obsPacientes = FXCollections.observableArrayList();
    private static ObservableList<Consulta> obsConsultas = FXCollections.observableArrayList();
    private static ObservableList<Procedimento> obsProcedimentos = FXCollections.observableArrayList();

    public static void acrescentarDia(){
        calendario.add(Calendar.DAY_OF_MONTH, 1);
    }

    public static void decrescerDia(){
        calendario.add(Calendar.DAY_OF_MONTH, -1);
    }

    public static String transformarData(Date data){
        return DateFormat.getDateInstance().format(data);
    }

    public static Date getData(){
        return calendario.getTime();
    }



    public static void adicionarPaciente(Paciente paciente) throws Exception{
        PacienteDAO.inserir(paciente);
        obsPacientes.add(paciente);
    }

    public static void adicionarConsulta(Consulta consulta) throws Exception{
        ConsultaDAO.inserir(consulta);
        obsConsultas.add(consulta);
    }

    public static void adicionarProcedimento(Procedimento procedimento) throws Exception {
        ProcedimentoDAO.inserir(procedimento);
        obsProcedimentos.add(procedimento);
    }


    public static void atualizarProcedimento(){
        obsProcedimentos.clear();

        try{
            obsProcedimentos.addAll(ProcedimentoDAO.buscarTudo());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void atualizarPaciente(){
        obsPacientes.clear();

        try {
            obsPacientes.addAll(PacienteDAO.buscarTudo());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Getters e setters
    public static ObservableList<Paciente> getPacientes(){
        return obsPacientes;
    }

    public static ObservableList<Consulta> getConsultas(){
        return obsConsultas;
    }

    public static ObservableList<Procedimento> getProcedimentos() {
        return obsProcedimentos;
    }

    public static void init(){
        try {
            calendario = new GregorianCalendar();

            obsConsultas.addAll(ConsultaDAO.buscarTudo());
            obsPacientes.addAll(PacienteDAO.buscarTudo());
            obsProcedimentos.addAll(ProcedimentoDAO.buscarTudo());
        }catch (Exception e){
            System.err.println("Erro: Impossível pegar os dados do banco de dados");
        }

    }
}
