package com.bigmans.stock;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.bigmans.stock.db.*;
import com.bigmans.stock.domain.*;
import com.bigmans.stock.ui.allMenu.MainMenu;
import com.formdev.flatlaf.FlatLightLaf;

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

            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/stock";

            // Создание свойств соединения с базой данных
            Properties authorization = new Properties();
            authorization.put("user", "postgres"); // Зададим имя пользователя БД
            authorization.put("password", "postgres"); // Зададим пароль доступа в БД

            // Создание соединения с базой данных
            Connection connection = DriverManager.getConnection(url, authorization);
            new MainMenu(connection);
    }

    private static <T extends Model> void print(T model){

            System.out.println("id:" + model.getId() + " name: " + model.getName());

        System.out.println("///////////");
    }
}