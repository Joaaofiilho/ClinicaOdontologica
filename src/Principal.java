import beans.Paciente;
import persistence.PacienteDAO;

public class Principal {
    public static void main(String[] args) {
        Paciente p = new Paciente("a", "b", "c", "d", "e", "f", 'm');
        PacienteDAO dao = new PacienteDAO();

        try {
            dao.inserir(p);
            System.out.println(dao.buscarPorCpf(p.getCpf()).getNome());
        }catch (Exception e){
            System.out.println("Algo ocorreu");
        }
    }
}
