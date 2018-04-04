package persistence;

import java.io.*;
import java.util.ArrayList;

import beans.Agenda;
import beans.Consulta;

public class ConsultaDAO {
    //Static
    private static ArrayList<Consulta> consultas = new ArrayList<>();

    //Métodos

    private static final char fileSeparator = ';';

    private static void gravarDados() throws Exception {

        File f = new File("consultas.txt");

        PrintWriter w = new PrintWriter(f);
        w.print("");
        w.close();

        FileWriter fw = null;
        BufferedWriter bw = null;

        try {

            fw = new FileWriter(f);
            bw = new BufferedWriter(fw);


            //Escreveu uma linha no texto
            for(Consulta c : consultas) {
                String dia = Integer.toString(c.getDia());
                String mes = Integer.toString(c.getMes());
                String ano = Integer.toString(c.getAno());

                bw.write(dia +fileSeparator+ mes +fileSeparator+ ano +fileSeparator+
                        c.getPaciente().getCpf()+fileSeparator+c.getHorarioCompleto()+
                        fileSeparator+c.getDescricao()+fileSeparator+ c.getValor());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void inserir(Consulta c) throws Exception{
        consultas.add(c);

        gravarDados();
    }

    public static void alterar(Consulta c) throws Exception{
        Consulta aux = new Consulta();
        aux.setDia(c.getDia());
        aux.setMes(c.getMes());
        aux.setAno(c.getAno());
        aux.setPaciente(c.getPaciente());
        aux.setHorarioCompleto(c.getHorarioCompleto());
        aux.setDescricao(c.getDescricao());
        aux.setValor(c.getValor());
        gravarDados();
    }

    public static void excluir(int id) throws Exception{
        for(int i = 0; i < consultas.size(); i++)
            if(consultas.get(i).getId() == id)
                consultas.remove(id);
        gravarDados();
    }

    public static ArrayList<Consulta> buscarPorCpf(String cpf) throws Exception{
        ArrayList<Consulta> temp = new ArrayList<>();
        for (Consulta c:
             consultas) {
            if(c.getPaciente().getCpf().equals(cpf)) temp.add(c);
        }
        return temp;
    }

    public static ArrayList<Consulta> buscarPorData(String data) throws Exception{
        ArrayList<Consulta> temp = new ArrayList<>();
        String diaMesAno[] = data.split("-");

//        int dia = Integer.parseInt(diaMesAno[0]);
//        int mes = Integer.parseInt(diaMesAno[1]);
//        int ano = Integer.parseInt(diaMesAno[2]);



        for (Consulta c: consultas) {
            if(String.valueOf(c.getAno()).substring(2,4).equals(diaMesAno[2]) &&
                    Integer.parseInt(diaMesAno[1]) ==  c.getMes() &&
                        Integer.parseInt(diaMesAno[0]) == c.getDia()){

                temp.add(c);
            }
        }
        return temp;
    }

    public static Consulta buscarPorId(int id){
        for(Consulta consulta : consultas){
            if(consulta.getId() == id)
                return consulta;
        }
        return null;
    }


    public static ArrayList<Consulta> getConsultas(){
        return consultas;
    }

    static {
        consultas.clear();

        File f = new File("consultas.txt");
        if(f.exists()) {
            FileReader fr = null;
            BufferedReader br = null;
            try {

                fr = new FileReader(f);
                br = new BufferedReader(fr);

                String linha;

                while ((linha = br.readLine()) != null) {
                    String[] dados = linha.split(";");
                    try {
                        Consulta c = new Consulta(Integer.parseInt(dados[0]), Integer.parseInt(dados[1]), Integer.parseInt(dados[2]),
                                PacienteDAO.buscarPorCpf(dados[3]), dados[4], dados[5], Float.parseFloat(dados[6]), Consulta.getContadorID());
                        //int dia, int mes, int ano, Paciente paciente, String horarioCompleto, String descricao, float valor
                        consultas.add(c);
                        Consulta.setContadorID(Consulta.getContadorID() + 1);
                    }catch (Exception e){
                        System.out.println("Erro ao buscar o paciente!");
                    }
                }

//                Consulta.setContadorID(consultas.get(consultas.size() - 1).getId() + 1);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (br != null)
                        br.close();
                    if (fr != null)
                        fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
