package com.unialfa.view;

import javax.swing.*;
import java.awt.*;

public class MenuForm extends JFrame{
    private JButton botaoFilme;
    private JButton botaoDiretor;
    private JLabel labelMenu;
    
    public MenuForm(){
        setTitle("Menu Filmes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 550);

        JPanel painelEntrada = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        labelMenu = new JLabel("Escolha onde deseja editar");
        constraints.gridx = 0;
        constraints.gridy = 0;
        painelEntrada.add(labelMenu, constraints);
        
        botaoFilme = new JButton("Filmes");
        botaoFilme.addActionListener(e -> irFormFilme());
        constraints.gridx = 0;
        constraints.gridy = 1;
        painelEntrada.add(botaoFilme, constraints);

        botaoDiretor = new JButton("Diretores");
        botaoDiretor.addActionListener(e -> irFormDiretor());
        constraints.gridx = 0;
        constraints.gridy = 2;
        painelEntrada.add(botaoDiretor, constraints);

        getContentPane().add(painelEntrada, BorderLayout.CENTER);

        setLocationRelativeTo(null);

    }

    private void irFormDiretor() {
        //setVisible(false);
        var form = new DiretorForm();
        form.setVisible(true);
    }

    private void irFormFilme() {
        //setVisible(false);
        var form = new FilmeForm();
        form.setVisible(true);
    }

}
