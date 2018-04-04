package persistence;

import java.io.*;
import java.util.ArrayList;

import beans.Consulta;

public class ConsultaDAO {
    //Static
    private static ArrayList<Consulta> consultas = new ArrayList<>();

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
                                PacienteDAO.buscarPorCpf(dados[3]), dados[4], dados[5], Float.parseFloat(dados[6]));
                        //int dia, int mes, int ano, Paciente paciente, String horarioCompleto, String descricao, float valor
                        consultas.add(c);
                    }catch (Exception e){
                        System.out.println("Erro ao buscar o paciente!");
                    }
                }

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

    //Métodos

    private final char fileSeparator = ';';

    public void gravarDados() throws Exception {

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

    public void inserir(Consulta c) throws Exception{
        consultas.add(c);

        gravarDados();
    }

    public void alterar(Consulta c) throws Exception{
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

    public void excluir(String horarioInicial) throws Exception{
        for (Consulta c:
             consultas) {
            if(c.getHorarioInicial().equals(horarioInicial)) consultas.remove(c);
        }
        gravarDados();
    }

    public ArrayList<Consulta> buscarPorCpf(String cpf) throws Exception{
        ArrayList<Consulta> temp = new ArrayList<>();
        for (Consulta c:
             consultas) {
            if(c.getPaciente().getCpf().equals(cpf)) temp.add(c);
        }
        return temp;
    }

}
