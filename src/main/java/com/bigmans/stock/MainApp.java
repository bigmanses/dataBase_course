package com.bigmans.stock;


import java.sql.*;
import java.util.List;
import java.util.Properties;

import com.bigmans.stock.db.*;
import com.bigmans.stock.domain.*;
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
                Prototype<Score> service = new ScoreService(connection);
                //Client client = new Client(10, "Маркус", "+71231313712", "Попа", "3234728", "1234675432", "Обычный");
                Product product = new Product(4, "Футболка большая", "Размер XXL", 2000, "10", "От 5", 10);
                //product.setManufacturerId(4);
//                Contract contract = new Contract(4, Date.valueOf("2022-08-13"), "Покупка 13 пар кроссовок",  13, "Заказ будет оплачен наличкой", 65000, true);
//                contract.setClientId(3);
//                contract.setProductId(1);
                Score score = new Score(3, "Счет", "77777", Date.valueOf("2022-07-11"), 30000, true, true);
                score.setContractId(5);
                print(service.read());
                service.delete(score);
                print(service.read());
                //manufacturerService.create(manufacturer);G


            } finally {
                connection.close();
            }
    }

    private static <T extends Model> void print(List<T> models){
        for(Model model: models){
            System.out.println("id:" + model.getId() + " name: " + model.getName());
        }
        System.out.println("///////////");
    }
}