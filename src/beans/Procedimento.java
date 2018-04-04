package beans;

import java.util.ArrayList;

public class Procedimento {

    //Atributos
    private String titulo;
    private String descricao;
    private double valor;
    private int duracao;
    private int id = 0;
    private static  int contadorID = 0;


    //Construtores

    public Procedimento(String titulo, String descricao, double valor, int duracao, int id) { //Usar para ler
        this.titulo = titulo;
        this.descricao = descricao;
        this.valor = valor;
        this.duracao = duracao;
        this.id = id;
    }

    public Procedimento(String titulo, String descricao, double valor, int duracao) {  //Usar na hora de criar para pode garantir o ID Exclusivo
        this.titulo = titulo;
        this.descricao = descricao;
        this.valor = valor;
        this.duracao = duracao;
        this.id = contadorID;
        contadorID++;
    }

    //    public Procedimento(String titulo, String descricao, double valor, int id) {
//        this.titulo = titulo;
//        this.descricao = descricao;
//        this.valor = valor;
//        this.id = id;
//    }
//
//    public Procedimento(String titulo, String descricao, double valor) {
//        this.titulo = titulo;
//        this.descricao = descricao;
//        this.valor = valor;
//        this.id = contadorID;
//        contadorID++;
//    }

    public Procedimento(){

    }


    // Metodos Acessores


    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    @Override
    public String toString() {
        return this.titulo.toUpperCase();
    }

    @Override
    public boolean equals(Object o) {
        Procedimento aux = (Procedimento) o;
        return this.id == aux.getId();
    }

}

