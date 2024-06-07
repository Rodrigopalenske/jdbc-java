package com.unialfa.view;

import com.unialfa.dao.DiretorDao;
import com.unialfa.model.Diretor;
import com.unialfa.model.Filme;
import com.unialfa.service.FilmeService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;

import static java.lang.Integer.parseInt;

public class FilmeForm extends JFrame {
    private FilmeService service;
    private JLabel labelId;
    private JTextField campoId;
    private JLabel labelNomeFilme;
    private JTextField campoNomeFilme;
    private JLabel labelDiretor;
    private JButton botaoSalvar;
    private JButton botaoCancelar;
    private JButton botaoExcluir;
    private JTable tabelaFilme;
    private JComboBox campoDiretor;

    public FilmeForm() {
        service = new FilmeService();

        setTitle("Filme");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(600, 550);

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

        labelNomeFilme = new JLabel("Nome do Filme:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        painelEntrada.add(labelNomeFilme, constraints);

        campoNomeFilme = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        painelEntrada.add(campoNomeFilme, constraints);

        labelDiretor = new JLabel("Diretor do Filme:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        painelEntrada.add(labelDiretor, constraints);

        campoDiretor = new JComboBox<>();
        campoDiretor.setEditable(false);
        campoDiretor.setSize(20, 12);
        listarDiretor().forEach(nomeDiretor ->
                campoDiretor.addItem(nomeDiretor)
        );
        constraints.gridx = 1;
        constraints.gridy = 2;
        painelEntrada.add(campoDiretor, constraints);

        botaoCancelar = new JButton("Cancelar");
        botaoCancelar.addActionListener(e -> limparCampos());
        constraints.gridx = 0;
        constraints.gridy = 3;
        painelEntrada.add(botaoCancelar, constraints);

        botaoSalvar = new JButton("Salvar");
        botaoSalvar.addActionListener(e -> executarAcaoDoBotao());
        constraints.gridx = 1;
        constraints.gridy = 3;
        painelEntrada.add(botaoSalvar, constraints);

        botaoExcluir = new JButton("Excluir");
        botaoExcluir.addActionListener(e -> executarDeletar());
        constraints.gridx = 2;
        constraints.gridy = 3;
        painelEntrada.add(botaoExcluir, constraints);

        JPanel painelSaida = new JPanel(new BorderLayout());

        tabelaFilme = new JTable();
        tabelaFilme.setModel(carregarDadosLocadoras());
        tabelaFilme.getSelectionModel().addListSelectionListener(e -> selecionarFilme(e));
        tabelaFilme.setDefaultEditor(Object.class, null);
        JScrollPane scrollPane = new JScrollPane(tabelaFilme);
        painelSaida.add(scrollPane, BorderLayout.CENTER);

        getContentPane().add(painelEntrada, BorderLayout.NORTH);
        getContentPane().add(painelSaida, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    private java.util.List<Object> listarDiretor() {
        try {
            var dao = new DiretorDao();
            var diretores =  dao.listarTodos();
            var nomeDiretores = new ArrayList<>();
            diretores.forEach(diretor ->
                    nomeDiretores.add(diretor.getNome())
            );
            return nomeDiretores;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }
    }

    private void executarDeletar() {
        service.deletar(construirFilme());
        limparCampos();
        tabelaFilme.setModel(carregarDadosLocadoras());

    }

    private void limparCampos() {
        campoNomeFilme.setText("");
        campoDiretor.setSelectedIndex(0);
        campoId.setText("");
    }

    private DefaultTableModel carregarDadosLocadoras() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nome");
        model.addColumn("Diretor");

        service.listarFilmes().forEach(filme ->
                model.addRow(new Object[]{
                                filme.getId(),
                                filme.getNome(),
                                filme.getDiretor()
                        }
                )
        );

        return model;
    }

    private void executarAcaoDoBotao() {
        try {
            service.salvar(construirFilme());
            limparCampos();
            tabelaFilme.setModel(carregarDadosLocadoras());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }

    private Filme construirFilme() {
        if (campoNomeFilme.getText().isEmpty()) throw new RuntimeException("Campo nome do filme não pode ser vazio");
        if (campoNomeFilme.getText().isBlank()) throw new RuntimeException("Campo nome do filme não pode ser espaço");

        if (campoDiretor.getSelectedItem().toString().isEmpty()) throw new RuntimeException("Campo diretor do filme não pode ser vazio");
        if (campoDiretor.getSelectedItem().toString().isBlank()) throw new RuntimeException("Campo diretor do filme não pode ser espaço");

        return campoId.getText().isEmpty()
                ? new Filme(campoNomeFilme.getText(), campoDiretor.getSelectedItem().toString())
                : new Filme(parseInt(campoId.getText()),
                campoNomeFilme.getText(),
                campoDiretor.getSelectedItem().toString());
    }


    private void selecionarFilme(ListSelectionEvent e){
        if (!e.getValueIsAdjusting()) {
            int selectedRow = tabelaFilme.getSelectedRow();
            if (selectedRow != -1) {
                var id = (Integer) tabelaFilme.getValueAt(selectedRow, 0);
                campoId.setText(id.toString());

                var nome = (String) tabelaFilme.getValueAt(selectedRow, 1);
                campoNomeFilme.setText(nome);

                var diretor = (String) tabelaFilme.getValueAt(selectedRow, 2);
                int indexDiretor = ((DefaultComboBoxModel)campoDiretor.getModel()).getIndexOf(diretor);

                campoDiretor.setSelectedIndex(indexDiretor);
            }
        }
    }
}
