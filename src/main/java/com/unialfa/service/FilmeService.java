package com.unialfa.service;

import com.unialfa.dao.FilmeDao;
import com.unialfa.model.Filme;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FilmeService {

    private static final String NOME_DIRETORIO = System.getProperty("user.dir");
    private static final String NOME_ARQUIVO = "\\Locadoras.txt";

    private final File locadoraFile = new File(NOME_DIRETORIO, NOME_ARQUIVO);

    public void salvar(Filme filme) {
        try {
            FilmeDao dao = new FilmeDao();
            dao.inserir(filme);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void atualizar(Filme filme) {
        try{
            FilmeDao dao = new FilmeDao();
            dao.atualizar(filme);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public List<Filme> listarFilmes() {
        try {
            FilmeDao dao = new FilmeDao();
            return dao.listarTodos();
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }
    }
}
