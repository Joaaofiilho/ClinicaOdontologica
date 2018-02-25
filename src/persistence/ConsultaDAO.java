package persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import beans.Consulta;

public class ConsultaDAO {
    //Static
    private static ArrayList<Consulta> consultas = new ArrayList<>();

    static {
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
                    Consulta c = new Consulta();

                    c = new Consulta(Integer.parseInt(dados[0]), Integer.parseInt(dados[1]), Integer.parseInt(dados[2]),
                            c.getPaciente(), dados[4], dados[5], Float.parseFloat(dados[6]));

                    consultas.add(c);
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

        FileWriter fw = null;
        BufferedWriter bw = null;

        try {

            fw = new FileWriter(f);
            bw = new BufferedWriter(fw);

            //Escreveu uma linha no texto
            for(Consulta c : consultas) {

                //TODO | Não consigo escrever um objeto paciente num arquivo de texto. Tou usando o metodo que criei pra pegar a info
                //TODO | do paciente e colocar num vetor... Porem não sei se fica eficiente/elegante.
                bw.write(c.getDia()+fileSeparator+c.getMes()+fileSeparator+c.getAno()+fileSeparator+
                        c.getInfoPaciente()[0]+fileSeparator+c.getInfoPaciente()[1]+fileSeparator+c.getInfoPaciente()[2]
                        +fileSeparator+c.getInfoPaciente()[3]+fileSeparator+c.getInfoPaciente()[4]+fileSeparator+
                        c.getInfoPaciente()[5]+fileSeparator+c.getInfoPaciente()[6]+fileSeparator+c.getHorarioCompleto()+
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
        //TODO | Necessita dos métodos de busca

        gravarDados();
    }

    public void excluir(String cpf) throws Exception{
        //TODO | Necessita dos métodos de busca

        gravarDados();
    }

    //TODO | Métodos para buscar. Buscamos pelo que?

}
