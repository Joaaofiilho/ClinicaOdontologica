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

    static {
        File f = new File("pacientes.txt");

        FileReader fr = null;
        BufferedReader br = null;

        try {

            fr = new FileReader(f);
            br = new BufferedReader(fr);

            String linha;

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");

                Paciente p = new Paciente(dados[0], dados[1], dados[2], dados[3], dados[4], dados[5], dados[6].charAt(0));
                //TODO arraydepacientes.add(p)
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
