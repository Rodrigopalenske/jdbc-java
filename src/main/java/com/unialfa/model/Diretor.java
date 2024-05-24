package com.unialfa.model;


import java.text.ParseException;
import java.sql.Date;

public class Diretor {
    private Integer id;
    private String nome;
    private String nacionalidade;
    private Date DataNascimento;
    private Integer premiacao;
    private Date DataInicioCarreira;

    public Diretor(Integer id, String nome, String nacionalidade, Date DataNascimento, Integer premiacao, Date DataInicioCarreira) {
        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.DataNascimento = DataNascimento;
        this.premiacao = premiacao;
        this.DataInicioCarreira = DataInicioCarreira;
    }

    public Diretor(String nome, String nacionalidade, Date DataNascimento, Integer premiacao, Date DataInicioCarreira) {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.DataNascimento = DataNascimento;
        this.premiacao = premiacao;
        this.DataInicioCarreira = DataInicioCarreira;
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

    public Date getDataNascimento() {
        return DataNascimento;
    }

    public void setDataNascimento(Date DataNascimento){

        this.DataNascimento = DataNascimento;

    }

    public Integer getPremiacao() {
        return premiacao;
    }

    public void setPremiacao(Integer premiacao) {
        this.premiacao = premiacao;
    }

    public Date getDataInicioCarreira() {
        return DataInicioCarreira;
    }

    public void setDataInicioCarreira(Date DataInicioCarreira) {
        this.DataInicioCarreira = DataInicioCarreira;

    }

    @Override
    public String toString() {
        return "Id: " + id + " Nome: " + nome + " Nacionalidade: " + nacionalidade + " Date Nascimento: "
                + DataNascimento + " Quantidade de Prêmios: " + premiacao + " Date de Inicio de Carreira: " + DataInicioCarreira;

    }
}
