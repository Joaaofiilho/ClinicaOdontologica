package persistence;

import java.io.*;
import java.util.ArrayList;

import beans.Procedimento;


public class ProcedimentoDAO {

    //Static
    private static ArrayList<Procedimento> procedimentos = new ArrayList<>(); //Contem todas as informações do txt.

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
                            Integer.parseInt(dados[3]), Integer.parseInt(dados[4]));
                    procedimentos.add(p);

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
    public void adicionarProcedimento(Procedimento procedimento) throws Exception { //Adiciona um procedimento ao Arraylist e ao txt.

        procedimentos.add(procedimento);

        gravarDados();

    }

    private void gravarDados() throws Exception{ //Metodo responsavel por gravar no arquivo

        File file = new File("procedimento.txt");

        FileWriter fw = null;
        BufferedWriter bw = null;

        try {

            fw = new FileWriter(file);

            bw = new BufferedWriter(fw);

            for (Procedimento procedimento : procedimentos) {

                bw.write(procedimento.getTitulo() + ";" + procedimento.getDescricao() + ";" +
                        procedimento.getValor() + ";" + procedimento.getDuracao() + ";" + procedimento.getId());
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


    public void alterar(Procedimento procedimento) throws Exception{ //Vai alterar uma informação de um procedimento

        try{
            Procedimento aux = buscarPorID(procedimento.getId());
            aux.setDescricao(procedimento.getDescricao());
            aux.setTitulo(procedimento.getTitulo());
            aux.setValor(procedimento.getValor());
            aux.setDuracao(procedimento.getDuracao());
            gravarDados();
        }catch (NullPointerException e){
            System.out.println("ID não cadastrado.");
        }

    }

    public void excluir(int id) throws Exception{
        procedimentos.remove(buscarPorID(id));
        gravarDados();
    }

    public Procedimento buscarPorID(int id) throws NullPointerException{
        for (Procedimento procedimento: procedimentos){
            if (procedimento.getId() == id){
                return procedimento;
            }
        }

        return null;
    }

    public ArrayList<Procedimento> buscarProcedimento(String procedimento){
        ArrayList<Procedimento> procedimentosBusca = new ArrayList<>();

        for (Procedimento aux : procedimentos){
            if (aux.getTitulo().equals(procedimento)){
                procedimentosBusca.add(aux);
            }
        }

        return procedimentosBusca;
    }

    public int tamanho(){
        return procedimentos.size();
    }

    public void exibirTodosProcedimentos(){
        System.out.println(procedimentos.toString());
    }


}
