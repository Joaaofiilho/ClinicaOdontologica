package beans;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistence.ConsultaDAO;
import persistence.PacienteDAO;
import persistence.ProcedimentoDAO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Agenda {
    private static Calendar calendario;
    private static ObservableList<String> obsPacientes = FXCollections.observableArrayList();
    private static ObservableList<String> obsConsultas = FXCollections.observableArrayList();

    private static ConsultaDAO ConDAO = new ConsultaDAO();
    private static PacienteDAO PacDAO = new PacienteDAO();
    private static ProcedimentoDAO ProDAO = new ProcedimentoDAO();

    public static void acrescentarDia(){
        calendario.add(Calendar.DAY_OF_MONTH, 1);
    }

    public static void decrescerDia(){
        calendario.add(Calendar.DAY_OF_MONTH, -1);
    }

    public static String getData(){
        String dia = "", mes = "", ano;
        if(calendario.get(Calendar.DAY_OF_MONTH) < 10)
            dia += "0";
        if(calendario.get(Calendar.MONTH) < 10)
            mes += "0";
        dia += Integer.toString(calendario.get(Calendar.DAY_OF_MONTH));
        mes += Integer.toString(calendario.get(Calendar.MONTH)+1);
        ano = Integer.toString(calendario.get(Calendar.YEAR));
        return dia + "/" + mes + "/" + ano.substring(2, 4);
    }

    public static void adicionarPaciente(Paciente paciente) throws Exception{
        PacDAO.inserir(paciente);
        obsPacientes.add(paciente.toString());
    }

    public static void adicionarConsulta(Consulta consulta) throws Exception{
        ConDAO.inserir(consulta);
        obsConsultas.add(consulta.toString());
    }

    public static void adicionarProcedimento(Procedimento procedimento) throws Exception {
        //ProDAO.inserir(procedimento);
    }
    //Getters e setters
    public static ObservableList<String> getPacientes(){
        return obsPacientes;
    }

    public static ObservableList<String> getConsultas(){
        return obsConsultas;
    }

    static {
        calendario = new GregorianCalendar();
    }
}
