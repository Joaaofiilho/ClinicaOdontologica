package beans;

import javafx.beans.property.IntegerProperty;
import javafx.scene.control.SingleSelectionModel;

import java.util.Date;

public class Consulta{

    //Atributos
    private Date data;
    private Paciente paciente;
    private String horarioCompleto;
    private String descricao;
    private double valor;
    private int id;
    //Construtores


    public Consulta(Date data, Paciente paciente, String horarioCompleto, String descricao, double valor) {
        this.data = data;
        this.paciente = paciente;
        this.horarioCompleto = horarioCompleto;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Consulta(Date data, Paciente paciente, String horarioCompleto, String descricao, double valor, int id) {
        this.data = data;
        this.paciente = paciente;
        this.horarioCompleto = horarioCompleto;
        this.descricao = descricao;
        this.valor = valor;
        this.id = id;
    }

    @Override
    public String toString(){
        return  "[" + getHorarioInicial() + "] " + paciente.getNome().toUpperCase();
    }

    //Metodos especiais
    public String getHorarioInicial(){
        return horarioCompleto.split("-")[0];
    }

    public String getHorarioFinal(){
        return horarioCompleto.split("-")[1];
    }

    //Getters e setters


    public void setData(Date data) {
        this.data = data;
    }

    public Date getData() {
        return data;
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

    public double getValor() {
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

//    public String getData(){
//        String dia = "", mes = "", ano;
//        if(this.dia < 10)
//            dia += "0";
//        if(this.mes < 10)
//            mes += "0";
//        dia += Integer.toString(this.dia);
//        mes += Integer.toString(this.mes);
//        ano = Integer.toString(this.ano);
//
//        return dia + "/" + mes + ano.substring(2, 4);
//    }
}