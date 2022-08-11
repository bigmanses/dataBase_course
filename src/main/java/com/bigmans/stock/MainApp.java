package com.bigmans.stock;


import java.sql.*;
import java.util.List;
import java.util.Properties;

import com.bigmans.stock.db.ClientService;
import com.bigmans.stock.db.ManufacturerService;
import com.bigmans.stock.domain.Client;
import com.bigmans.stock.domain.Manufacturer;
import com.bigmans.stock.ui.MainFrame;
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

            try {
                ManufacturerService manufacturerService = new ManufacturerService(connection);
                Manufacturer manufacturer = new Manufacturer(-1, "Vasia", "Новгород", "Мансур", "София", "25352437215472");
                print(manufacturerService.read());
                manufacturerService.create(manufacturer);


            } finally {
                connection.close();
            }
    }

    private static void print(List<Manufacturer> clients){
        for(Manufacturer manufacturer: clients){
            System.out.println("id:" + manufacturer.getId() + " name: " + manufacturer.getName());
        }
        System.out.println("///////////");
    }
}