package com.unialfa.view;

import com.unialfa.model.Diretor;
import com.unialfa.service.DiretorService;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DiretorForm extends JFrame{
    private DiretorService service;
    private JLabel labelId;
    private JTextField campoId;
    private JLabel labelNomeDiretor;
    private JTextField campoNomeDiretor;
    private JLabel labelNacionalidade;
    private JTextField campoNacionalidade;
    private JLabel labelDataNascimento;
    private JTextField campoDataNascimento;
    private JLabel labelPremiacao;
    private JTextField campoPremiacao;
    private JLabel labelDataInicioCarreira;
    private JTextField campoDataInicioCarreira;
    
    private JButton botaoSalvar;
    private JButton botaoCancelar;
    private JButton botaoExcluir;
    private JTable tabelaFilme;


    public DiretorForm(){
        service = new DiretorService();

        setTitle("Diretor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 550);

        JPanel painelEntrada = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        
        labelId = new JLabel("ID:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        painelEntrada.add(labelId, constraints);

        campoId = new JTextField(20);
        campoId.setEnabled(false);
        constraints.gridx = 1;
        constraints.gridy = 0;
        painelEntrada.add(campoId, constraints);

        labelNomeDiretor = new JLabel("Nome do Diretor:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        painelEntrada.add(labelNomeDiretor, constraints);

        campoNomeDiretor = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        painelEntrada.add(campoNomeDiretor, constraints);

        labelNacionalidade = new JLabel("Nacionalidade do Diretor:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        painelEntrada.add(labelNacionalidade, constraints);

        campoNacionalidade = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 2;
        painelEntrada.add(campoNacionalidade, constraints);

        labelDataNascimento = new JLabel("Data de Nascimento do Diretor:");
        constraints.gridx = 0;
        constraints.gridy = 3;
        painelEntrada.add(labelDataNascimento, constraints);

        campoDataNascimento = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 3;
        painelEntrada.add(campoDataNascimento, constraints);

        labelPremiacao = new JLabel("Quantidade de Premiações do Diretor:");
        constraints.gridx = 0;
        constraints.gridy = 4;
        painelEntrada.add(labelPremiacao, constraints);

        campoPremiacao = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 4;
        painelEntrada.add(campoPremiacao, constraints);

        labelDataInicioCarreira = new JLabel("Data de Início de Carreira do Diretor:");
        constraints.gridx = 0;
        constraints.gridy = 5;
        painelEntrada.add(labelDataInicioCarreira, constraints);

        campoDataInicioCarreira = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 5;
        painelEntrada.add(campoDataInicioCarreira, constraints);

        botaoSalvar = new JButton("Salvar");
        botaoSalvar.addActionListener(e ->
            executarSalvar()
        );
        constraints.gridx = 1;
        constraints.gridy = 6;
        painelEntrada.add(botaoSalvar, constraints);

        botaoExcluir = new JButton("Excluir");
        botaoExcluir.addActionListener(e -> executarDeletar());
        constraints.gridx = 2;
        constraints.gridy = 6;
        painelEntrada.add(botaoExcluir, constraints);

        JPanel painelSaida = new JPanel(new BorderLayout());

        //tabelaFilme = new JTable();
        //tabelaFilme.setModel(carregarDadosLocadoras());
        //tabelaFilme.getSelectionModel().addListSelectionListener(e -> selecionarFilme(e));
        //tabelaFilme.setDefaultEditor(Object.class, null);
        //JScrollPane scrollPane = new JScrollPane(tabelaFilme);
        //painelSaida.add(scrollPane, BorderLayout.CENTER);

        getContentPane().add(painelEntrada, BorderLayout.NORTH);
        //getContentPane().add(painelSaida, BorderLayout.CENTER);

        setLocationRelativeTo(null);


    }

    private Diretor construirDiretor(){
        return campoId.getText().isEmpty()
                ? new Diretor(campoNomeDiretor.getText(), campoNacionalidade.getText(), Date.valueOf(campoDataNascimento.getText()) , Integer.parseInt(campoPremiacao.getText()), Date.valueOf(campoDataInicioCarreira.getText()))
                : new Diretor(Integer.parseInt(campoId.getText()), campoNomeDiretor.getText(), campoNacionalidade.getText(), Date.valueOf(campoDataNascimento.getText()), Integer.parseInt(campoPremiacao.getText()), Date.valueOf(campoDataInicioCarreira.getText()));
    }

    private void executarDeletar() {
    }

    private void limparCampos() {
        campoId.setText("");
        campoNomeDiretor.setText("");
        campoNacionalidade.setText("");
        campoPremiacao.setText("");
        campoDataNascimento.setText("");
        campoDataInicioCarreira.setText("");
    }

    private void executarSalvar() {
            service.salvar(construirDiretor());
            limparCampos();

    }
}
