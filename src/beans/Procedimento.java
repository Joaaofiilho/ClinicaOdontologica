package beans;

public class Procedimento {

    //Atributos
    private String procedimento;
    private String descricao;
    private double valor;
    private int id = 0;
    private static  int contadorID = 0;


    //Construtores
    public Procedimento(String procedimento, String descricao, double valor, int id) {  //Usar para ler
        this.procedimento = procedimento;
        this.descricao = descricao;
        this.valor = valor;
        this.id = id;
    }

    public Procedimento(String procedimento, String descricao, double valor) {  //Usar na hora de criar para pode garantir o ID Exclusivo
        this.procedimento = procedimento;
        this.descricao = descricao;
        this.valor = valor;
        this.id = contadorID;
        contadorID++;
    }

    public Procedimento(){

    }


    // Metodos Acessores
    public String getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(String procedimento) {
        this.procedimento = procedimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getContadorID() {
        return contadorID;
    }

    public static void setContadorID(int contadorID) {
        Procedimento.contadorID = contadorID;
    }



}

