package beans;

public class Consulta {

    //Atributos
    private int dia;
    private int mes;
    private int ano;
    private Paciente paciente;
    private String horarioCompleto;
    private String descricao;
    private float valor;


    //Construtores
    public Consulta(int dia, int mes, int ano, Paciente paciente, String horarioCompleto, String descricao, float valor) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.paciente = paciente;
        this.horarioCompleto = horarioCompleto;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Consulta() {
    }


    //Método para pegar informações do paciente
    public String[] getInfoPaciente(){
        String[] informacoesPac = new String[7];

        informacoesPac[0] = this.paciente.getNome(); informacoesPac[1] = this.paciente.getCpf();
        informacoesPac[2] = this.paciente.getNascimento(); informacoesPac[3] = this.paciente.getTelefone();
        informacoesPac[4] = this.paciente.getEmail(); informacoesPac[5] = this.paciente.getEndereco();
        informacoesPac[6] = (this.paciente.getSexo() + "");

        return informacoesPac;
    }


    //Getters e setters
    public int getDia() {
        return dia;
    }
    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }
    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }

    public Paciente getPaciente() {
        return paciente;
    }
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getHorarioCompleto() {
        return horarioCompleto;
    }
    public void setHorarioCompleto(String horarioCompleto) {
        this.horarioCompleto = horarioCompleto;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor() {
        return valor;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }

}
