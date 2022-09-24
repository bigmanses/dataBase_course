package com.bigmans.stock.ui.allMenu;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.util.ArrayList;

public class MainMenu extends JFrame {

    JPanel jPanel = new JPanel(new GridBagLayout());
    ArrayList<JMenuItem> menus = new ArrayList<>();
    JMenuItem activeMenu;
    JButton exit = new JButton("Выход");
    Connection connection;
    JPanelClient menuClient;

    public MainMenu(Connection connection){
        this.connection = connection;
        initFrame();
        initMenu();
        initPanel();
        initClickMenu();
        initColor();
        setVisible(true);
    }

    private void initFrame(){
        setTitle("Склад (admin)");
        setSize(1000, 800);
        setMinimumSize(new Dimension(800, 600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(jPanel, BorderLayout.NORTH);
    }

    private void initMenu(){
        menus.add(new JMenuItem("Клиенты"));
        menus.add(new JMenuItem("Продукты"));
        menus.add(new JMenuItem("Контракты"));
        menus.add(new JMenuItem("Производители"));
        menus.add(new JMenuItem("Счета"));
    }

    private void initPanel(){
        jPanel.setPreferredSize(new Dimension(1000, 100));
        int i = 0;
        for(JMenuItem menuItem: menus) {
            jPanel.add(menuItem, new GridBagConstraints(i++, 0, 1, 100, 0.0, 0.9, GridBagConstraints.NORTH,
                    GridBagConstraints.HORIZONTAL, new Insets(2,2 ,2 ,2), 0, 5));
        }
        jPanel.add(exit, new GridBagConstraints(i++, 0, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2,2 ,2 ,2), 0, 0));
    }

    private void initClickMenu(){
        for(JMenuItem menuItem: menus){
            menuItem.addMouseListener(new MouseClick(menuItem, this));
        }
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setVisible(false);
                System.exit(0);
            }
        });
    }

    private void  initColor(){
        getContentPane().setBackground(Color.WHITE);
        jPanel.setBackground(Color.WHITE);
        for(JMenuItem menuItem: menus){
            menuItem.setBackground(Color.GRAY);
        }
    }

    public class MouseClick implements MouseListener {

        JMenuItem jMenu;
        JFrame jFrame;

        public MouseClick(JMenuItem jMenu, JFrame jFrame) {
            this.jMenu = jMenu;
            this.jFrame = jFrame;
        }

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            jMenu.setBackground(new Color(0, 255, 255));
            if(activeMenu!= null) {
                activeMenu.setBackground(Color.GRAY);
            }
            activeMenu = jMenu;
            menuClient = new JPanelClient(jFrame, connection);
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {

        }
    }


}
