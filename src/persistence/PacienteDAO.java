package persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import beans.Paciente;


public class PacienteDAO {
    //Static
    private static ArrayList<Paciente> pacientes = new ArrayList<>();

    static {
        File f = new File("pacientes.txt");
        if(f.exists()) {
            FileReader fr = null;
            BufferedReader br = null;
            try {

                fr = new FileReader(f);
                br = new BufferedReader(fr);

                String linha;

                while ((linha = br.readLine()) != null) {
                    String[] dados = linha.split(";");
                    Paciente p = new Paciente(dados[0], dados[1], dados[2], dados[3], dados[4], dados[5], dados[6].charAt(0));
                    pacientes.add(p);
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

    public void inserir(Paciente p) throws Exception{
        pacientes.add(p);

        gravarDados();
    }

    public void alterar(Paciente p) throws Exception{
        Paciente aux = buscarPorCpf(p.getCpf());
        aux.setNome(p.getNome());
        aux.setNascimento(p.getNascimento());
        aux.setTelefone(p.getTelefone());
        aux.setEmail(p.getEmail());
        aux.setEndereco(p.getEndereco());
        aux.setSexo(p.getSexo());

        gravarDados();
    }

    public void excluir(String cpf) throws Exception{
        pacientes.remove(buscarPorCpf(cpf));

        gravarDados();
    }

    public static Paciente buscarPorCpf(String cpf) throws Exception{
        for (Paciente p:
             pacientes) {
            if(p.getCpf().equals(cpf)) return p;
        }
        return null;
    }

    public ArrayList<Paciente> buscarPorNome(String nome) throws Exception{
        ArrayList<Paciente> temp = new ArrayList<>();
        for (Paciente p:
             pacientes) {
            if(p.getNome().contains(nome)) temp.add(p);
        }
        return temp;
    }

    public void gravarDados() throws Exception {
        File f = new File("pacientes.txt");

        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            //Classe que armazena caracteres
            fw = new FileWriter(f);

            //Classe que armazena strings
            bw = new BufferedWriter(fw);

            //Escreveu uma linha no texto
            for(Paciente p : pacientes) {
                bw.write(p.getNome()+";"+p.getCpf()+";"+p.getNascimento()+";"+
                        p.getTelefone()+";"+p.getEmail()+";"+p.getEndereco()+";"+
                        p.getSexo());
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


}
