package beans;

import javafx.beans.property.IntegerProperty;

public class Consulta {

    //Atributos
    private int dia;
    private int mes;
    private int ano;
    private Paciente paciente;
    private String horarioCompleto;
    private String descricao;
    private float valor;
    private int id;
    private static  int contadorID = 0;

    //Construtores
    public Consulta(int dia, int mes, int ano, Paciente paciente, String horarioCompleto, String descricao, float valor) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.paciente = paciente;
        this.horarioCompleto = horarioCompleto;
        this.descricao = descricao;
        this.valor = valor;
        this.id = contadorID++;
    }

    public Consulta(int dia, int mes, int ano, Paciente paciente, String horarioCompleto, String descricao, float valor, int id) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.paciente = paciente;
        this.horarioCompleto = horarioCompleto;
        this.descricao = descricao;
        this.valor = valor;
        this.id = id;
    }

    public Consulta() {
    }

    @Override
    public String toString(){
        return  getHorarioInicial() + " - " + paciente.getNome().toUpperCase() + " - " + getId();
    }

    //Metodos especiais
    public String getHorarioInicial(){
        return horarioCompleto.split("-")[0];
    }

    public String getHorarioFinal(){
        return horarioCompleto.split("-")[1];
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public static void setContadorID(int contadorID) {
        Consulta.contadorID = contadorID;
    }

    public static int getContadorID() {
        return contadorID;
    }

    public String getData(){
        String dia = "", mes = "", ano;
        if(this.dia < 10)
            dia += "0";
        if(this.mes < 10)
            mes += "0";
        dia += Integer.toString(this.dia);
        mes += Integer.toString(this.mes);
        ano = Integer.toString(this.ano);

        return dia + "/" + mes + ano.substring(2, 4);
    }
}