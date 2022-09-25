package com.bigmans.stock.ui.actions;

import com.bigmans.stock.db.ClientService;
import com.bigmans.stock.db.ManufacturerService;
import com.bigmans.stock.db.ProductService;
import com.bigmans.stock.db.Prototype;
import com.bigmans.stock.domain.Client;
import com.bigmans.stock.domain.Contract;
import com.bigmans.stock.domain.Manufacturer;
import com.bigmans.stock.domain.Product;
import com.bigmans.stock.ui.Act;
import com.bigmans.stock.ui.ActionId;
import io.swagger.models.auth.In;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DialogProvider {
    /** Интерфейс сервисов для работы с БД */
    private Prototype service;
    /** Наше диалоговое окно */
    private JDialog dialog;
    private JFrame frame;
    private final JRadioButton postavka = new JRadioButton("Поставка");
    private final JRadioButton sale = new JRadioButton("Покупка");
    final String noText = new String("Введите данные");
    public int createDialog(String key, JFrame frame, Prototype prototype, String act) {
        this.service = prototype;
        this.frame = frame;
        if(act.equals(Act.ADD.getId())) {
            this.dialog = new JDialog(frame, "add", true);
            if (key.equals(ActionId.CLIENT.getId())) {
                JDialog dialog = getClientDialog();
                listenDialogClient(dialog);
                dialog.setVisible(true);
            }
            if(key.equals(ActionId.MANUFACTURE.getId())){
                JDialog dialog = getManufacturerDialog();
                listenDialogManufacturer(dialog);
                dialog.setVisible(true);
            }
            if(key.equals(ActionId.PRODUCT.getId())){
                JDialog dialog = getProductDialog();
                listenDialogProduct(dialog);
                dialog.setVisible(true);
            }
            if (key.equals(ActionId.CONTRACT.getId())) {
                JDialog dialog = getContractDialog();
                listenDialogContract(dialog);
                dialog.setVisible(true);
            }
        } else if (act.equals(Act.DELETE.getId())){
            int answer = JOptionPane.showOptionDialog(frame, "Вы точно хотите удалить элемент?", "delete",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {"Да", "Нет"}, "да");
        return answer;
        }
        return -1;
    }

    private JDialog getClientDialog() {
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

    private JDialog getManufacturerDialog() {
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

        dialog.add(new Label("о товаре"), new GridBagConstraints(0, 5, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,1 ,0,1), 0, 0));
        dialog.add(new JTextField(10), new GridBagConstraints(1, 5, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        Button button = new Button("Сохранить");
        dialog.add(button, new GridBagConstraints(1, 7, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        return dialog;
    }

    private JDialog getProductDialog() {
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

    private JDialog getContractDialog() {
        dialog.setLayout(new GridBagLayout());
        dialog.setSize(500, 500);
        dialog.setLocationRelativeTo(null);
        dialog.add(new Label("дата"), new GridBagConstraints(0, 0, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField("2022-07-08", 10), new GridBagConstraints(1, 0, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new Label("о контракте"), new GridBagConstraints(0, 1, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField(10), new GridBagConstraints(1, 1, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("продукт"), new GridBagConstraints(0, 2, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField(10), new GridBagConstraints(1, 2, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("количество"), new GridBagConstraints(0, 3, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,1 ,0,1), 0, 0));
        dialog.add(new JTextField(10), new GridBagConstraints(1, 3, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        dialog.add(new Label("условия контракта"), new GridBagConstraints(0, 4, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,1 ,0,1), 0, 0));
        dialog.add(new JTextField(10), new GridBagConstraints(1, 4, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new Label("покупатель"), new GridBagConstraints(0, 5, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField(10), new GridBagConstraints(1, 5, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new Label("цена"), new GridBagConstraints(0, 6, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField(10), new GridBagConstraints(1, 6, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        ButtonGroup group = new ButtonGroup();
        group.add(postavka);
        group.add(sale);
        dialog.add(postavka, new GridBagConstraints(0, 7, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(sale, new GridBagConstraints(1, 7, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));

        Button button = new Button("Сохранить");
        dialog.add(button, new GridBagConstraints(1, 8, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        return dialog;
    }



    private void listenDialogManufacturer(JDialog dialog){
        Component[] components = dialog.getContentPane().getComponents();
        Button button = (Button) dialog.getContentPane().getComponents()[components.length - 1];
        button.addActionListener(actionEvent1 -> {
            List<JTextField> texts = new ArrayList<>();
           checkCorrect(texts);
            if(!checkCorrect(texts)){
                saveManufacturer(texts);
                dialog.setVisible(false);
            }
        });
    }

    private void listenDialogProduct(JDialog dialog){
        Component[] components = dialog.getContentPane().getComponents();
        Button button = (Button) dialog.getContentPane().getComponents()[components.length - 1];
        button.addActionListener(actionEvent1 -> {
            List<JTextField> texts = new ArrayList<>();
            checkCorrect(texts);
            if(!checkCorrect(texts)){
                if(checkManufacturer(texts.get(texts.size()-1)) != -1) {
                    saveProduct(texts, checkManufacturer(texts.get(texts.size()-1)));
                } else{
                    dialog.setVisible(false);
                    JOptionPane.showMessageDialog(frame, "Такого производителя нет в Базе", "Error", JOptionPane.ERROR_MESSAGE);
                }
                dialog.setVisible(false);
            }
        });
    }

    private int checkManufacturer(JTextField text) {
        ManufacturerService manufacturerService = new ManufacturerService(service.getConnect());
        List<Manufacturer> manufacturers = manufacturerService.read();
        for (Manufacturer manufacturer: manufacturers){
            if (manufacturer.getName().equals((String)text.getText()))
                return manufacturer.getId();
        }
        return -1;
    }

    private void listenDialogClient(JDialog dialog){
        Component[] components = dialog.getContentPane().getComponents();
        Button button = (Button) dialog.getContentPane().getComponents()[components.length - 1];
        button.addActionListener(actionEvent1 -> {
            List<JTextField> texts = new ArrayList<>();
            if(!checkCorrect(texts)){
                saveClient(texts);
                dialog.setVisible(false);
            }
        });
    }

    private void listenDialogContract(JDialog dialog){
        Component[] components = dialog.getContentPane().getComponents();
        Button button = (Button) dialog.getContentPane().getComponents()[components.length - 1];
        button.addActionListener(actionEvent1 -> {
            List<JTextField> texts = new ArrayList<>();
            checkCorrect(texts);
            if(!checkCorrect(texts)){
                if(checkContract(texts.get(2), texts.get(5)).size() > 1) {
                    saveContract(texts,checkContract(texts.get(2), texts.get(5)));
                } else{
                    dialog.setVisible(false);
                    JOptionPane.showMessageDialog(frame, "Такого производителя/продукта нет в Базе", "Error", JOptionPane.ERROR_MESSAGE);
                }
                dialog.setVisible(false);
            }
        });
    }

    private List<Integer> checkContract(JTextField product, JTextField client) {
        ProductService productService = new ProductService(service.getConnect());
        ClientService clientService = new ClientService(service.getConnect());
        List<Product> products = productService.read();
        List<Client> clients = clientService.read();
        List<Integer> ids = new ArrayList<>();
        for (Product product1: products){
            if (product1.getName().equals((String)product.getText()))
                ids.add(product1.getId());
        }
        for (Client client1: clients){
            if (client1.getName().equals((String)client.getText()))
                ids.add(client1.getId());
        }
        return ids;
    }

    private boolean checkCorrect(List<JTextField> texts){
        boolean error = false;
        for (Component component : dialog.getContentPane().getComponents()) {
            if (component instanceof JTextField) {
                if(Objects.equals(((JTextField) component).getText(), "") || Objects.equals(((JTextField) component).getText(), noText)){
                    component.setBackground(Color.RED);
                    ((JTextField) component).setText("Введите данные");
                    texts.add((JTextField) component);
                    component.addMouseListener(new MouseListenerClick());
                    error = true;
                } else{
                    component.setBackground(Color.WHITE);
                }
                texts.add((JTextField) component);
            }
        }
        return error;
    }
    private void saveClient(List<JTextField> texts) {
        Client client = new Client();
        client.setName(texts.get(0).getText());
        client.setPhone(texts.get(1).getText());
        client.setAddress(texts.get(2).getText());
        client.setFax(texts.get(3).getText());
        client.setScore(texts.get(4).getText());
        client.setNotes(texts.get(5).getText());
        service.create(client);
    }

    private void saveManufacturer(List<JTextField> texts){
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(texts.get(0).getText());
        manufacturer.setAddress(texts.get(1).getText());
        manufacturer.setDirector(texts.get(2).getText());
        manufacturer.setAccountant(texts.get(3).getText());
        manufacturer.setRequisites(texts.get(4).getText());
        service.create(manufacturer);
    }

    private void saveProduct(List<JTextField> texts, int manufacturerId){
        Product product = new Product();
        try {
            product.setName(texts.get(0).getText());
            product.setCharacteristic(texts.get(1).getText());
            product.setPriceOne(Integer.parseInt(texts.get(2).getText()));
            product.setPackages(texts.get(3).getText());
            product.setBatchDelivery(texts.get(4).getText());
            product.setAmount(Integer.parseInt(texts.get(5).getText()));
            product.setManufacturerId(manufacturerId);
            service.create(product);
        }
        catch (NumberFormatException exception){
            dialog.setVisible(false);
            JOptionPane.showMessageDialog(frame, "Ошибка ввода данных, ожидался ввод целого числа " , "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    private void saveContract(List<JTextField> texts, List<Integer> ids){
        Contract contract = new Contract();
        try {
            contract.setDate_contract(Date.valueOf(LocalDate.parse((texts.get(0).getText()))));
            contract.setAbout(texts.get(1).getText());
            contract.setProductId(ids.get(0));
            contract.setAmount(Integer.parseInt(texts.get(3).getText()));
            contract.setTerms((texts.get(4).getText()));
            contract.setClientId(ids.get(1));
            contract.setPrice(Integer.parseInt(texts.get(6).getText()));
            if(postavka.isSelected()) contract.setSale(true);
            else contract.setSale(false);
            service.create(contract);
        }
        catch (NumberFormatException exception){
            dialog.setVisible(false);
            JOptionPane.showMessageDialog(frame, "Ошибка ввода данных, ожидался ввод целого числа " , "Error", JOptionPane.ERROR_MESSAGE);
        }
        catch (DateTimeParseException exception){
            JOptionPane.showMessageDialog(frame, "Ошибка ввода даты, введите дату, как указано в примере " , "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public class MouseListenerClick implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            if(mouseEvent.getComponent().getBackground().equals(Color.RED)){
                mouseEvent.getComponent().setBackground(Color.WHITE);
            }
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

