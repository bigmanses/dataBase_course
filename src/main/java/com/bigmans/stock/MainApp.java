package com.bigmans.stock;

import com.bigmans.stock.ui.MainFrame;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
public class MainApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> {
            FlatLightLaf.setup();
            new MainFrame().setVisible(true);
        });
    }
}
