package beans;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Agenda {
    Calendar calendario;

    public Agenda(){
        calendario = new GregorianCalendar();

    }

    public void acrescentarDia(){
        calendario.add(Calendar.DAY_OF_MONTH, 1);
    }

    public void decrescerDia(){
        calendario.add(Calendar.DAY_OF_MONTH, -1);
    }

    public String getData(){
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
    //Getters e setters
}
