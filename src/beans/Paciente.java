package beans;

import java.text.DateFormat;
import java.util.Date;

public class Paciente {
    private String nome;
    private String cpf;
    private Date nascimento;
    private String telefone;
    private String email;
    private String logradouro_end;
    private int numero_end;
    private String complemento_end;
    private String bairro_end;
    private String cidade_end;
    private String estado_end;
    private char sexo;

    //Construtores


    public Paciente(String nome, String cpf, Date nascimento, String telefone, String email, String logradouro_end, int numero_end, String complemento_end, String bairro_end, String cidade_end, String estado_end, char sexo) {
        this.nome = nome;
        this.cpf = cpf;
        this.nascimento = nascimento;
        this.telefone = telefone;
        this.email = email;
        this.logradouro_end = logradouro_end;
        this.numero_end = numero_end;
        this.complemento_end = complemento_end;
        this.bairro_end = bairro_end;
        this.cidade_end = cidade_end;
        this.estado_end = estado_end;
        this.sexo = sexo;
    }

    @Override
    public String toString(){
        return this.nome.toUpperCase() + " (" + this.sexo + ")";
    }

    //Getters e setters
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getNascimento() {
        return nascimento;
    }
    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public char getSexo() {
        return sexo;
    }
    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getLogradouro_end() {
        return logradouro_end;
    }
    public void setLogradouro_end(String logradouro_end) {
        this.logradouro_end = logradouro_end;
    }

    public int getNumero_end() {
        return numero_end;
    }
    public void setNumero_end(int numero_end) {
        this.numero_end = numero_end;
    }

    public String getComplemento_end() {
        return complemento_end;
    }
    public void setComplemento_end(String complemento_end) {
        this.complemento_end = complemento_end;
    }

    public String getBairro_end() {
        return bairro_end;
    }
    public void setBairro_end(String bairro_end) {
        this.bairro_end = bairro_end;
    }

    public String getCidade_end() {
        return cidade_end;
    }
    public void setCidade_end(String cidade_end) {
        this.cidade_end = cidade_end;
    }

    public String getEstado_end() {
        return estado_end;
    }
    public void setEstado_end(String estado_end) {
        this.estado_end = estado_end;
    }
}
