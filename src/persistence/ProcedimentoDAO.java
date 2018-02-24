package persistence;

import java.io.*;
import java.util.ArrayList;

import beans.Procedimento;


public class ProcedimentoDAO {


    //Static
    private static ArrayList<Procedimento> procedimentosArray = new ArrayList<>(); //Contem todas as informações do txt.

    static { //Leitura do arquivo para armazenar no Arraylist
        File f = new File("procedimento.txt");

        if(f.exists()) {

            FileReader fr = null;
            BufferedReader br = null;

            try {

                fr = new FileReader(f);
                br = new BufferedReader(fr);

                String linha;

                while ((linha = br.readLine()) != null) {
                    String[] dados = linha.split(";");

                    Procedimento p = new Procedimento(dados[0], dados[1], Double.parseDouble(dados[2]),
                            Integer.parseInt(dados[3]));
                    procedimentosArray.add(p);

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


    //Metodos
    public void adicionarProcedimento(Procedimento procedimento){ //Adiciona um procedimento ao Arraylist e ao txt.

        procedimentosArray.add(procedimento);

        gravarDados();

    }

    private void gravarDados() { //Metodo responsavel por gravar no arquivo

        File file = new File("procedimento.txt");

        FileWriter fw = null;
        BufferedWriter bw = null;

        try {

            fw = new FileWriter(file);

            bw = new BufferedWriter(fw);

            for (Procedimento procedimento : procedimentosArray) {

                bw.write(procedimento.getProcedimento() + ";" + procedimento.getDescricao() + ";" +
                        procedimento.getValor() + ";" + procedimento.getId());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
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


    public void Alterar(Procedimento procedimento){ //Vai alterar uma informação de um procedimento

        //TESTAr

        Procedimento aux = buscar(procedimento.getId());
        aux.setDescricao(procedimento.getDescricao());
        aux.setProcedimento(procedimento.getProcedimento());
        aux.setValor(procedimento.getValor());


        gravarDados();


    }

    public Procedimento buscar(int id){ // TESTAr
        for (Procedimento procedimento: procedimentosArray){
            if (procedimento.getId() == id){
                return procedimento;
            }
        }
        return null;
    }

    public int tamanho(){
        return procedimentosArray.size();
    }


}
