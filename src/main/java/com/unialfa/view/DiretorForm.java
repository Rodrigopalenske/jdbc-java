package com.unialfa.view;

import com.unialfa.model.Diretor;
import com.unialfa.service.DiretorService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.sql.Date;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    private JButton botaoVoltar;

    private JTable tabelaDiretor;


    public DiretorForm(){
        service = new DiretorService();

        setTitle("Diretor");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(700, 650);

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

        botaoVoltar = new JButton("Voltar");
        botaoVoltar.addActionListener(e -> executarVoltar());
        constraints.gridx = 0;
        constraints.gridy = 6;
        painelEntrada.add(botaoVoltar, constraints);

        botaoCancelar = new JButton("Cancelar");
        botaoCancelar.addActionListener(e -> limparCampos());
        constraints.gridx = 1;
        constraints.gridy = 6;
        painelEntrada.add(botaoCancelar, constraints);

        botaoSalvar = new JButton("Salvar");
        botaoSalvar.addActionListener(e ->
            executarSalvar()
        );
        constraints.gridx = 2;
        constraints.gridy = 6;
        painelEntrada.add(botaoSalvar, constraints);

        botaoExcluir = new JButton("Excluir");
        botaoExcluir.addActionListener(e -> executarDeletar());
        constraints.gridx = 3;
        constraints.gridy = 6;
        painelEntrada.add(botaoExcluir, constraints);

        JPanel painelSaida = new JPanel(new BorderLayout());

        tabelaDiretor = new JTable();
        tabelaDiretor.setModel(carregarDadosLocadoras());
        tabelaDiretor.getSelectionModel().addListSelectionListener(e -> selecionarDiretor(e));
        tabelaDiretor.setDefaultEditor(Object.class, null);

        JScrollPane scrollPane = new JScrollPane(tabelaDiretor);
        painelSaida.add(scrollPane, BorderLayout.CENTER);

        getContentPane().add(painelEntrada, BorderLayout.NORTH);
        getContentPane().add(painelSaida, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    private void executarVoltar() {
        setVisible(false);
        var form = new MenuForm();
        form.setVisible(true);
    }

    private Diretor construirDiretor(){
        if (campoNomeDiretor.getText().isEmpty()) throw new RuntimeException("Campo nome do diretor não pode ser vazio");
        if (campoNomeDiretor.getText().isBlank()) throw new RuntimeException("Campo nome do diretor não pode ser espaço");

        if (campoNacionalidade.getText().isEmpty()) throw new RuntimeException("Campo nacionalidade não pode ser vazio");
        if (campoNacionalidade.getText().isBlank()) throw new RuntimeException("Campo nacionalidade não pode ser espaço");

        if (campoDataNascimento.getText().isEmpty()) throw new RuntimeException("Campo data de nascimento não pode ser vazio");
        if (campoDataNascimento.getText().isBlank()) throw new RuntimeException("Campo data de nascimento não pode ser espaço");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var dataNascimento = campoDataNascimento.getText();
        var data = LocalDate.parse(dataNascimento, formatter);

        if (campoPremiacao.getText().isEmpty()) throw new RuntimeException("Campo quantidade de premiações não pode ser vazio");
        if (campoPremiacao.getText().isBlank()) throw new RuntimeException("Campo quantidade de premiações não pode ser espaço");
        var validaQuantPremiacao = Integer.parseInt(campoPremiacao.getText());
        if (validaQuantPremiacao < 0) throw new RuntimeException("Campo quantidade de premiações não pode ser menor que zero");

        if (campoDataInicioCarreira.getText().isEmpty()) throw new RuntimeException("Campo data de início de carreira não pode ser vazio");
        if (campoDataInicioCarreira.getText().isBlank()) throw new RuntimeException("Campo data de início de carreira não pode ser espaço");

        var dataInicioCarreira = campoDataNascimento.getText();
        data = LocalDate.parse(dataInicioCarreira, formatter);
        System.out.println(data);

        return campoId.getText().isEmpty()
                ? new Diretor(campoNomeDiretor.getText(), campoNacionalidade.getText(), Date.valueOf(LocalDate.parse(campoDataNascimento.getText(), formatter)), Integer.parseInt(campoPremiacao.getText()), Date.valueOf(LocalDate.parse(campoDataInicioCarreira.getText(), formatter)))
                : new Diretor(Integer.parseInt(campoId.getText()), campoNomeDiretor.getText(), campoNacionalidade.getText(), Date.valueOf(LocalDate.parse(campoDataNascimento.getText(), formatter)), Integer.parseInt(campoPremiacao.getText()), Date.valueOf(LocalDate.parse(campoDataInicioCarreira.getText(), formatter)));
    }

    private TableModel carregarDadosLocadoras() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nome");
        model.addColumn("Nacionalidade");
        model.addColumn("Data Nascimento");
        model.addColumn("Quant. Prêmiações");
        model.addColumn("Data Inicio de Carreira");

        service.listarDiretores().forEach(diretor ->
                model.addRow(new Object[]{
                        diretor.getId(),
                        diretor.getNome(),
                        diretor.getNacionalidade(),
                        formatarData(diretor.getDataNascimento().toString()),
                        diretor.getPremiacao().toString(),
                        formatarData(diretor.getDataInicioCarreira().toString())
                })
        );

        return model;
    }

    private String formatarData(String dataOriginal){
        try {
            DateTimeFormatter formatarEntrada = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter formatarSaida = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(dataOriginal, formatarEntrada);

            // Formata a data no novo formato
            String outputDateString = date.format(formatarSaida);

            return outputDateString;
        } catch (Exception e) {
            return dataOriginal;
        }
    }

    private void selecionarDiretor(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int selectedRow = tabelaDiretor.getSelectedRow();
            if (selectedRow != -1) {
                var id = (Integer) tabelaDiretor.getValueAt(selectedRow, 0);
                campoId.setText(id.toString());

                var nome = (String) tabelaDiretor.getValueAt(selectedRow, 1);
                campoNomeDiretor.setText(nome);

                var nacionalidade = (String) tabelaDiretor.getValueAt(selectedRow, 2);
                campoNacionalidade.setText(nacionalidade);

                System.out.println();
                var dataNascimento = (String) tabelaDiretor.getValueAt(selectedRow, 3);
                campoDataNascimento.setText(formatarData(dataNascimento));
                //campoDataNascimento.setText(dataNascimento);

                var premiacao = (String) tabelaDiretor.getValueAt(selectedRow, 4);
                campoPremiacao.setText(premiacao);

                var dataInicioCarreira = (String) tabelaDiretor.getValueAt(selectedRow, 5);
                campoDataInicioCarreira.setText(formatarData(dataInicioCarreira));
                //campoDataInicioCarreira.setText(dataInicioCarreira);
            }
        }
    }

    private void executarDeletar() {
        service.deletar(construirDiretor());
        limparCampos();
        tabelaDiretor.setModel(carregarDadosLocadoras());
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
        try{
            service.salvar(construirDiretor());
            limparCampos();

            tabelaDiretor.setModel(carregarDadosLocadoras());
        } catch (NumberFormatException en) {
            JOptionPane.showMessageDialog(this,"A quantidade de premiações deve ser um número");
        } catch (DateTimeException ed){
            JOptionPane.showMessageDialog(this, "As datas devem estar no formato (dia/mes/ano)");
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}
