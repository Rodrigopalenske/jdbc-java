package com.unialfa;

import com.unialfa.dao.FilmeDao;
import com.unialfa.view.FilmeForm;

import javax.swing.*;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            var form = new FilmeForm();
            form.setVisible(true);
        });
    }
}