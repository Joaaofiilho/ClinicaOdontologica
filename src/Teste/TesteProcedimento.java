package Teste;
import beans.Procedimento;
import persistence.ProcedimentoDAO;

import java.util.Scanner;


public class TesteProcedimento {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ProcedimentoDAO procedimentoDAO = new ProcedimentoDAO();

        Procedimento.setContadorID(procedimentoDAO.tamanho()); //Para deixar automatico o ID
        System.out.println(Procedimento.getContadorID());

        for (int i = 0; i < 3; i++) {
            Procedimento procedimento = new Procedimento (sc.next(), sc.next(), sc.nextDouble());
            procedimentoDAO.adicionarProcedimento(procedimento);
        }




    }
}
