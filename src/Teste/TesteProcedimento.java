package Teste;
import beans.Procedimento;
import persistence.ProcedimentoDAO;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class TesteProcedimento {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ProcedimentoDAO procedimentoDAO = new ProcedimentoDAO();

        Procedimento.setContadorID(procedimentoDAO.tamanho()); //Para deixar automatico o ID
        //System.out.println(Procedimento.getContadorID());  //Contador OK!!







        //Exibir Tudo armazenado OK!
//        procedimentoDAO.exibirTodosProcedimentos();


        //Buscar Por Descrição OK!!

//        ArrayList<Procedimento> lista1 =  procedimentoDAO.buscarProcedimento("ahsduashduasd");
//        ArrayList<Procedimento> lista =  procedimentoDAO.buscarProcedimento("akira");
//        System.out.println(lista1.toString());
//        System.out.println(lista.toString());


        //Alterar OK!
//        Procedimento alterar = new Procedimento("Kira", "Hello", 110, 10);
//
//        try {
//            procedimentoDAO.alterar(alterar);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        //Metodo equals OK!
//        Procedimento t1 = new Procedimento("asda","asdasd",10,1);
//        Procedimento t2 = new Procedimento("asda","asdasd",10,1);
//
//        System.out.println(t1.equals(t2));



        //Meotod busca ok!
//        Procedimento procedimento = procedimentoDAO.buscarPorID(1);
//
//        System.out.println(procedimento.toString());


        //TESTAR para adicionar OK!!
//        for (int i = 0; i < 2; i++) {
//            try {
//                Procedimento procedimento = new Procedimento(sc.next(), sc.next(), sc.nextDouble());
//                procedimentoDAO.adicionarProcedimento(procedimento);
//
//            }catch (InputMismatchException e){
//                System.out.println("Preechido incorretamente");
//            }catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }




    }
}
