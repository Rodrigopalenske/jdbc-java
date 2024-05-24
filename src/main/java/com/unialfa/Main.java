package com.unialfa;

import com.unialfa.dao.FilmeDao;
import com.unialfa.view.FilmeForm;
import com.unialfa.view.MenuForm;

import javax.swing.*;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            //var form = new FilmeForm();
            var form = new MenuForm();
            form.setVisible(true);
        });
    }
}