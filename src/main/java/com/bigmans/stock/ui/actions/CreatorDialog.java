package com.bigmans.stock.ui.actions;

import com.bigmans.stock.domain.*;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class CreatorDialog {

    public static JDialog getClientDialog(JDialog dialog) {
        dialog.setLayout(new GridBagLayout());
        dialog.setSize(500, 500);
        dialog.setLocationRelativeTo(null);
        dialog.add(new Label("name"), new GridBagConstraints(0, 0, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField(10), new GridBagConstraints(1, 0, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new Label("phone"), new GridBagConstraints(0, 2, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField(10), new GridBagConstraints(1, 2, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("address"), new GridBagConstraints(0, 3, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField(10), new GridBagConstraints(1, 3, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("fax"), new GridBagConstraints(0, 4, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,1 ,0,1), 0, 0));
        dialog.add(new JTextField(10), new GridBagConstraints(1, 4, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("score"), new GridBagConstraints(0, 5, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,1 ,0,1), 0, 0));
        dialog.add(new JTextField(10), new GridBagConstraints(1, 5, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("notes"), new GridBagConstraints(0, 6, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,1 ,0,1), 0, 0));
        dialog.add(new JTextField(10), new GridBagConstraints(1, 6, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        Button button = new Button("Сохранить");
        dialog.add(button, new GridBagConstraints(1, 7, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        return dialog;
    }

    public static JDialog getClientDialog(JDialog dialog, Client client) {
        dialog.setLayout(new GridBagLayout());
        dialog.setSize(500, 500);
        dialog.setLocationRelativeTo(null);
        dialog.add(new Label("name"), new GridBagConstraints(0, 0, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField(client.getName()), new GridBagConstraints(1, 0, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new Label("phone"), new GridBagConstraints(0, 2, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField(client.getPhone()), new GridBagConstraints(1, 2, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("address"), new GridBagConstraints(0, 3, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField(client.getAddress()), new GridBagConstraints(1, 3, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("fax"), new GridBagConstraints(0, 4, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,1 ,0,1), 0, 0));
        dialog.add(new JTextField(client.getFax()), new GridBagConstraints(1, 4, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("score"), new GridBagConstraints(0, 5, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,1 ,0,1), 0, 0));
        dialog.add(new JTextField(client.getScore()), new GridBagConstraints(1, 5, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("notes"), new GridBagConstraints(0, 6, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,1 ,0,1), 0, 0));
        dialog.add(new JTextField(client.getNotes()), new GridBagConstraints(1, 6, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        Button button = new Button("Сохранить");
        dialog.add(button, new GridBagConstraints(1, 7, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        return dialog;
    }

    public static JDialog getManufacturerDialog(JDialog dialog) {
        dialog.setLayout(new GridBagLayout());
        dialog.setSize(500, 500);
        dialog.setLocationRelativeTo(null);
        dialog.add(new Label("имя"), new GridBagConstraints(0, 0, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField(10), new GridBagConstraints(1, 0, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new Label("адрес"), new GridBagConstraints(0, 2, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField(10), new GridBagConstraints(1, 2, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("директор"), new GridBagConstraints(0, 3, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField(10), new GridBagConstraints(1, 3, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("бухгалтер"), new GridBagConstraints(0, 4, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,1 ,0,1), 0, 0));
        dialog.add(new JTextField(10), new GridBagConstraints(1, 4, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("реквезиты"), new GridBagConstraints(0, 5, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,1 ,0,1), 0, 0));
        dialog.add(new JTextField(10), new GridBagConstraints(1, 5, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        Button button = new Button("Сохранить");
        dialog.add(button, new GridBagConstraints(1, 7, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        return dialog;
    }

    public static JDialog getManufacturerDialog(JDialog dialog, Manufacturer manufacturer) {
        dialog.setLayout(new GridBagLayout());
        dialog.setSize(500, 500);
        dialog.setLocationRelativeTo(null);
        dialog.add(new Label("имя"), new GridBagConstraints(0, 0, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField(manufacturer.getName()), new GridBagConstraints(1, 0, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new Label("адрес"), new GridBagConstraints(0, 2, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField(manufacturer.getAddress()), new GridBagConstraints(1, 2, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("директор"), new GridBagConstraints(0, 3, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField(manufacturer.getDirector()), new GridBagConstraints(1, 3, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("бухгалтер"), new GridBagConstraints(0, 4, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,1 ,0,1), 0, 0));
        dialog.add(new JTextField(manufacturer.getAccountant()), new GridBagConstraints(1, 4, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("реквезиты"), new GridBagConstraints(0, 5, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,1 ,0,1), 0, 0));
        dialog.add(new JTextField(manufacturer.getRequisites()), new GridBagConstraints(1, 5, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        Button button = new Button("Сохранить");
        dialog.add(button, new GridBagConstraints(1, 7, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        return dialog;
    }

    public static JDialog getProductDialog(JDialog dialog) {
        dialog.setLayout(new GridBagLayout());
        dialog.setSize(500, 500);
        dialog.setLocationRelativeTo(null);
        dialog.add(new Label("имя"), new GridBagConstraints(0, 0, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField(10), new GridBagConstraints(1, 0, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new Label("характеристики"), new GridBagConstraints(0, 1, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField(10), new GridBagConstraints(1, 1, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("цена за 1 "), new GridBagConstraints(0, 2, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField(10), new GridBagConstraints(1, 2, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("упаковка"), new GridBagConstraints(0, 3, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,1 ,0,1), 0, 0));
        dialog.add(new JTextField(10), new GridBagConstraints(1, 3, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("поставка"), new GridBagConstraints(0, 4, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,1 ,0,1), 0, 0));
        dialog.add(new JTextField(10), new GridBagConstraints(1, 4, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new Label("количество"), new GridBagConstraints(0, 5, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField(10), new GridBagConstraints(1, 5, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new Label("призводитель"), new GridBagConstraints(0, 6, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField(10), new GridBagConstraints(1, 6, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        Button button = new Button("Сохранить");
        dialog.add(button, new GridBagConstraints(1, 7, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        return dialog;
    }

    public static JDialog getProductDialog(JDialog dialog, Product product) {
        dialog.setLayout(new GridBagLayout());
        dialog.setSize(500, 500);
        dialog.setLocationRelativeTo(null);
        dialog.add(new Label("имя"), new GridBagConstraints(0, 0, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField(product.getName()), new GridBagConstraints(1, 0, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new Label("характеристики"), new GridBagConstraints(0, 1, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField(product.getCharacteristic()), new GridBagConstraints(1, 1, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("цена за 1 "), new GridBagConstraints(0, 2, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField(String.valueOf(product.getPriceOne())), new GridBagConstraints(1, 2, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("упаковка"), new GridBagConstraints(0, 3, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,1 ,0,1), 0, 0));
        dialog.add(new JTextField(product.getPackages()), new GridBagConstraints(1, 3, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("поставка"), new GridBagConstraints(0, 4, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,1 ,0,1), 0, 0));
        dialog.add(new JTextField(product.getBatchDelivery()), new GridBagConstraints(1, 4, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new Label("количество"), new GridBagConstraints(0, 5, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField(String.valueOf(product.getAmount())), new GridBagConstraints(1, 5, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new Label("призводитель"), new GridBagConstraints(0, 6, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField(product.getManufacturer().getName()), new GridBagConstraints(1, 6, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        Button button = new Button("Сохранить");
        dialog.add(button, new GridBagConstraints(1, 7, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        return dialog;
    }

    public static JDialog getContractDialog(JDialog dialog, JRadioButton postavka, JRadioButton sale) {
        dialog.setLayout(new GridBagLayout());
        dialog.setSize(500, 500);
        postavka.setSelected(true);
        dialog.setLocationRelativeTo(null);
        dialog.add(new Label("дата"), new GridBagConstraints(0, 0, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField("2022-07-08", 10), new GridBagConstraints(1, 0, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("номер"), new GridBagConstraints(0, 1, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField(10), new GridBagConstraints(1, 1, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("о контракте"), new GridBagConstraints(0, 2, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField(10), new GridBagConstraints(1, 2, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("продукт"), new GridBagConstraints(0, 3, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField(10), new GridBagConstraints(1, 3, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("количество"), new GridBagConstraints(0, 4, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,1 ,0,1), 0, 0));
        dialog.add(new JTextField(10), new GridBagConstraints(1, 4, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("условия контракта"), new GridBagConstraints(0, 5, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,1 ,0,1), 0, 0));
        dialog.add(new JTextField(10), new GridBagConstraints(1, 5, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("покупатель"), new GridBagConstraints(0, 6, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField(10), new GridBagConstraints(1, 6, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("цена"), new GridBagConstraints(0, 7, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField(10), new GridBagConstraints(1, 7, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        ButtonGroup group = new ButtonGroup();
        group.add(postavka);
        group.add(sale);
        dialog.add(postavka, new GridBagConstraints(0, 8, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(sale, new GridBagConstraints(1, 8, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        Button button = new Button("Сохранить");
        dialog.add(button, new GridBagConstraints(1, 9, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        return dialog;
    }

    public static JDialog getContractDialog(JDialog dialog, JRadioButton postavka, JRadioButton sale, Contract contract) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dialog.setLayout(new GridBagLayout());
        dialog.setSize(500, 500);
        postavka.setSelected(true);
        dialog.setLocationRelativeTo(null);
        dialog.add(new Label("дата"), new GridBagConstraints(0, 0, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField(dateFormat.format(contract.getDate_contract())), new GridBagConstraints(1, 0, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("номер"), new GridBagConstraints(0, 1, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField(contract.getNumber()), new GridBagConstraints(1, 1, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("о контракте"), new GridBagConstraints(0, 2, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField(contract.getAbout()), new GridBagConstraints(1, 2, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("продукт"), new GridBagConstraints(0, 3, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField(contract.getProduct().getName()), new GridBagConstraints(1, 3, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("количество"), new GridBagConstraints(0, 4, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,1 ,0,1), 0, 0));
        dialog.add(new JTextField(String.valueOf(contract.getAmount())), new GridBagConstraints(1, 4, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("условия контракта"), new GridBagConstraints(0, 5, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,1 ,0,1), 0, 0));
        dialog.add(new JTextField(contract.getTerms()), new GridBagConstraints(1, 5, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("покупатель"), new GridBagConstraints(0, 6, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField(contract.getClient().getName()), new GridBagConstraints(1, 6, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("цена"), new GridBagConstraints(0, 7, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField(String.valueOf(contract.getPrice())), new GridBagConstraints(1, 7, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        ButtonGroup group = new ButtonGroup();
        group.add(postavka);
        group.add(sale);
        dialog.add(postavka, new GridBagConstraints(0, 8, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(sale, new GridBagConstraints(1, 8, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        Button button = new Button("Сохранить");
        dialog.add(button, new GridBagConstraints(1, 9, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        return dialog;
    }

    public static JDialog getScoreDialog(JDialog dialog, JRadioButton shipped, JRadioButton noShipped, JRadioButton pay, JRadioButton noPay) {
        dialog.setLayout(new GridBagLayout());
        dialog.setSize(500, 500);
        shipped.setSelected(true);
        pay.setSelected(true);
        dialog.setLocationRelativeTo(null);
        dialog.add(new Label("имя"), new GridBagConstraints(0, 0, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField( 10), new GridBagConstraints(1, 0, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("номер"), new GridBagConstraints(0, 1, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField(10), new GridBagConstraints(1, 1, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("номер контракта"), new GridBagConstraints(0, 2, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField(10), new GridBagConstraints(1, 2, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("дата"), new GridBagConstraints(0, 3, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField("2022-07-08", 10), new GridBagConstraints(1, 3, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("сумма"), new GridBagConstraints(0, 4, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,1 ,0,1), 0, 0));
        dialog.add(new JTextField(10), new GridBagConstraints(1, 4, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        ButtonGroup ship = new ButtonGroup();
        ship.add(shipped);
        ship.add(noShipped);
        dialog.add(shipped, new GridBagConstraints(0, 5, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(noShipped, new GridBagConstraints(1, 5, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        ButtonGroup pays = new ButtonGroup();
        pays.add(pay);
        pays.add(noPay);
        dialog.add(pay, new GridBagConstraints(0, 6, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(noPay, new GridBagConstraints(1, 6, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        Button button = new Button("Сохранить");
        dialog.add(button, new GridBagConstraints(1, 7, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        return dialog;
    }

    public static JDialog getScoreDialog(JDialog dialog, JRadioButton shipped, JRadioButton noShipped, JRadioButton pay, JRadioButton noPay, Score score) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dialog.setLayout(new GridBagLayout());
        dialog.setSize(500, 500);
        shipped.setSelected(true);
        pay.setSelected(true);
        dialog.setLocationRelativeTo(null);
        dialog.add(new Label("имя"), new GridBagConstraints(0, 0, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField( score.getName()), new GridBagConstraints(1, 0, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("номер"), new GridBagConstraints(0, 1, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField(score.getNumber()), new GridBagConstraints(1, 1, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("номер контракта"), new GridBagConstraints(0, 2, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField(score.getContract().getNumber()), new GridBagConstraints(1, 2, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("дата"), new GridBagConstraints(0, 3, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField(dateFormat.format(score.getDate_score())), new GridBagConstraints(1, 3, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("сумма"), new GridBagConstraints(0, 4, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,1 ,0,1), 0, 0));
        dialog.add(new JTextField(String.valueOf(score.getSum())), new GridBagConstraints(1, 4, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        ButtonGroup ship = new ButtonGroup();
        ship.add(shipped);
        ship.add(noShipped);
        dialog.add(shipped, new GridBagConstraints(0, 5, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(noShipped, new GridBagConstraints(1, 5, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        ButtonGroup pays = new ButtonGroup();
        pays.add(pay);
        pays.add(noPay);
        dialog.add(pay, new GridBagConstraints(0, 6, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(noPay, new GridBagConstraints(1, 6, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        Button button = new Button("Сохранить");
        dialog.add(button, new GridBagConstraints(1, 7, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        return dialog;
    }
}
