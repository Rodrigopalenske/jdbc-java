package com.unialfa.model;


import java.text.ParseException;
import java.sql.Date;

public class Diretor {
    private Integer id;
    private String nome;
    private String nacionalidade;
    private Date DateNascimento;
    private Integer premiacao;
    private Date DateInicioCarreira;

    public Diretor(Integer id, String nome, String nacionalidade, Date DateNascimento, Integer premiacao, Date DateInicioCarreira) {
        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.DateNascimento = DateNascimento;
        this.premiacao = premiacao;
        this.DateInicioCarreira = DateInicioCarreira;
    }

    public Diretor(String nome, String nacionalidade, Date DateNascimento, Integer premiacao, Date DateInicioCarreira) {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.DateNascimento = DateNascimento;
        this.premiacao = premiacao;
        this.DateInicioCarreira = DateInicioCarreira;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public Date getDateNascimento() {
        return DateNascimento;
    }

    public void setDateNascimento(Date DateNascimento){

        this.DateNascimento = DateNascimento;

    }

    public Integer getPremiacao() {
        return premiacao;
    }

    public void setPremiacao(Integer premiacao) {
        this.premiacao = premiacao;
    }

    public Date getDateInicioCarreira() {
        return DateInicioCarreira;
    }

    public void setDateInicioCarreira(Date DateInicioCarreira) {
        this.DateInicioCarreira = DateInicioCarreira;

    }

    @Override
    public String toString() {
        return "Id: " + id + " Nome: " + nome + " Nacionalidade: " + nacionalidade + " Date Nascimento: "
                + DateNascimento + " Quantidade de Prêmios: " + premiacao + " Date de Inicio de Carreira: " + DateInicioCarreira;

    }
}
