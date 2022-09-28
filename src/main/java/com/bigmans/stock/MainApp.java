package com.bigmans.stock;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import com.bigmans.stock.db.*;
import com.bigmans.stock.domain.User;
import com.bigmans.stock.ui.addition.CheckCorrect;
import com.bigmans.stock.ui.allMenu.MainMenuAdmin;
import com.bigmans.stock.ui.allMenu.MainMenuClient;

import javax.swing.*;

public class MainApp {

    //    public static void main(String[] args) {
//        SwingUtilities.invokeLater(()-> {
//            FlatLightLaf.setup();
//            new MainFrame().setVisible(true);
//        });
//    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // РАБОТА С БАЗОЙ ДАННЫХ POSTGRESQL ЧЕРЕЗ JDBC
            // Адрес нашей базы данных "tsn_pg_demo" на локальном компьютере (localhost)

            JFrame frame = new JFrame("Вход");
            frame.setLayout(new GridBagLayout());
            frame.setSize(300, 200);
            frame.setLocationRelativeTo(null);
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/stock";

            // Создание свойств соединения с базой данных
            Properties authorization = new Properties();
            authorization.put("user", "postgres"); // Зададим имя пользователя БД
            authorization.put("password", "postgres"); // Зададим пароль доступа в БД

            // Создание соединения с базой данных
            Connection connection = DriverManager.getConnection(url, authorization);
            UserService userService = new UserService(connection);
            createDialog(userService);
            //new MainMenuClient(connection, users.get(0).getName());
    }

    private static void createDialog(UserService service) {
        JFrame frame = new JFrame("Вход");
        frame.setLayout(new GridBagLayout());
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.add(new Label("логин"), new GridBagConstraints(0, 1, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        frame.add(new JTextField(10), new GridBagConstraints(1, 1, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

        frame.add(new Label("пароль"), new GridBagConstraints(0, 2, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        frame.add(new JPasswordField(10), new GridBagConstraints(1, 2, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        Button button = new Button("Войти");
        frame.add(button, new GridBagConstraints(1, 3, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        frame.setVisible(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                List<JTextField> text = new ArrayList<>();
                CheckCorrect checkCorrect = new CheckCorrect(service);
                boolean error = true;
                if(!checkCorrect.checkCorrect(text, frame)){
                    for(User user: service.read()){
                        if(user.getPassword().equals(text.get(1).getText())){
                            frame.setVisible(false);
                            error = false;
                           if(user.getName().equals("Администратор")){
                               new MainMenuAdmin(service.getConnect(), user.getName());
                           }
                           else
                               new MainMenuClient(service.getConnect(), user.getName());
                        }
                    }
                    if(error){
                        JOptionPane.showMessageDialog(frame, "Такого пользователя нет в Базе", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }


}