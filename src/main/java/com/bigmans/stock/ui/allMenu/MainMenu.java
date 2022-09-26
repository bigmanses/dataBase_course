package com.bigmans.stock.ui.allMenu;

import com.bigmans.stock.db.*;
import com.bigmans.stock.domain.Client;
import com.bigmans.stock.ui.Act;
import com.bigmans.stock.ui.ActionId;
import com.bigmans.stock.ui.actions.DialogProvider;
import com.bigmans.stock.ui.actions.EssenceProvider;
import com.bigmans.stock.ui.addition.CheckCorrect;
import com.bigmans.stock.ui.tables.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainMenu extends JFrame {
    /** Макет с помощью которого мы можем переключать наши таблицы*/
    CardLayout layout = new CardLayout();
    /** Панель, в которой хранятся все наши таблицы */
    final JPanel content = new JPanel(layout);
    /** Верхняя панель с кнопками */
    JPanel btnPanel = new JPanel(new GridLayout(1,5));
    /** Нижняя панель с кнопками */
    JPanel contentPanel = new JPanel(new GridLayout(2,4));
    /** Кнопки верхней панели */
    List<BtnWithName> btns = Arrays.asList(new BtnWithName("Клиенты", ActionId.CLIENT.getId()),  new BtnWithName("Продукты", ActionId.PRODUCT.getId()),
            new BtnWithName("Контракты", ActionId.CONTRACT.getId()), new BtnWithName("Производители", ActionId.MANUFACTURE.getId()),
            new BtnWithName("Счета", ActionId.SCORE.getId()), new BtnWithName("Наличие", ActionId.PRODUCT_BUYING.getId()),
            new BtnWithName("Возможные", ActionId.PRODUCT_FOR_ORDER.getId()), new BtnWithName("Склад", ActionId.PRODUCT_FOR_STOCK.getId()),
            new BtnWithName("Купленные", ActionId.PRODUCT_GIVE_AWAY.getId()), new BtnWithName("Неоплаченные", ActionId.SCORE_NOT_PAYMENT.getId()),
            new BtnWithName("Информация", ActionId.SCORE_SUM_PAYMENT.getId()), new BtnWithName("Поиск по товару", ActionId.CLIENT_FOR_PRODUCT.getId()));
    /** Лист с нашими таблицами */
    List<JTable> tables = new ArrayList<>();
    /** Кнопки нижней панели */
    JButton add = new JButton("Добавить");
    JButton deleteBtn = new JButton("Удалить");
    JButton editBtn = new JButton("Редактировать");
    /** Сущность для связи с БД */
    Connection connection;
    /** Сервис для работы с клиентами через БД */
    ClientService clientService;
    ManufacturerService manufacturerService;
    ProductService productService;
    ContractService contractService;
    ScoreService scoreService;
    RequestService requestService;
    /** Поставщик диалогового окна */
    DialogProvider dialogProvider = new DialogProvider();
    /** Флаг действующей панелии */
    String openPanel;

    public MainMenu(Connection connection) throws HeadlessException {
        super("Склад");
        this.connection = connection;
        this.clientService = new ClientService(connection);
        this.manufacturerService = new ManufacturerService(connection);
        this.productService = new ProductService(connection);
        this.contractService = new ContractService(connection);
        this.scoreService = new ScoreService(connection);
        this.requestService = new RequestService(connection);
        content.add("empty", new JPanel());
        Arrays.stream(ActionId.values())
              .forEach(a->content.add(a.getId(), createComponent(a.getId())));

        btns.forEach(b -> b.addActionListener(actionEvent -> {
            System.out.println("click: " + ((JComponent)actionEvent.getSource()).getName());
            if(b.getText().equals("Поиск по товару")) searchClient();
            layout.show(content, ((JComponent)actionEvent.getSource()).getName());
            openPanel = ((JComponent)actionEvent.getSource()).getName();
            add.setEnabled(true);
            deleteBtn.setEnabled(false);
            editBtn.setEnabled(false);
            checkButton();
        }
        ));

        add.addActionListener(actionEvent -> {
            if(openPanel.equals(ActionId.CLIENT.getId())) {
                dialogProvider.createDialog(openPanel, this, clientService, Act.ADD.getId(), null, null);
                tables.get(0).setModel(new TableClients(clientService.read()));
                updateTable();
            } else if(openPanel.equals(ActionId.PRODUCT.getId())) {
                dialogProvider.createDialog(openPanel, this, productService, Act.ADD.getId(), null, null);
                tables.get(1).setModel(new TableProduct(productService.read(), manufacturerService));
                updateTable();
            } else if(openPanel.equals(ActionId.CONTRACT.getId())) {
                dialogProvider.createDialog(openPanel, this, contractService, Act.ADD.getId(), null, null);
                tables.get(2).setModel(new TableContract(contractService.read(), clientService, productService));
                updateTable();
            } else if(openPanel.equals(ActionId.MANUFACTURE.getId())){
                dialogProvider.createDialog(openPanel, this, manufacturerService, Act.ADD.getId(), null, null);
                tables.get(3).setModel(new TableManufacturer(manufacturerService.read()));
                updateTable();
            } else if(openPanel.equals(ActionId.SCORE.getId())){
                dialogProvider.createDialog(openPanel, this, scoreService, Act.ADD.getId(), null, null);
                tables.get(4).setModel(new TableScore(scoreService.read(), contractService));
                updateTable();
            }
            deleteBtn.setEnabled(false);
        });

        tables.get(0).addMouseListener(new MouseClick(tables.get(0), deleteBtn, editBtn,this));
        tables.get(1).addMouseListener(new MouseClick(tables.get(1), deleteBtn, editBtn, this));
        tables.get(2).addMouseListener(new MouseClick(tables.get(2), deleteBtn, editBtn, this));
        tables.get(3).addMouseListener(new MouseClick(tables.get(3), deleteBtn, editBtn, this));
        tables.get(4).addMouseListener(new MouseClick(tables.get(4), deleteBtn, editBtn, this));
        initPanels();
        initFrame();
    }



    private void initPanels(){
        for (int i = 0; i<5; i++){
            btnPanel.add(btns.get(i));
        }
        add.setEnabled(false);
        deleteBtn.setEnabled(false);
        editBtn.setEnabled(false);
        for (int i = 5; i < 12; i++) {
            contentPanel.add(btns.get(i));
            btns.get(i).setEnabled(false);
        }
        contentPanel.add(add);
        contentPanel.add(deleteBtn);
        contentPanel.add(editBtn);
    }

    private void initFrame(){
        getContentPane().add(content, BorderLayout.CENTER);
        getContentPane().add(contentPanel, BorderLayout.SOUTH);
        getContentPane().add(btnPanel, BorderLayout.NORTH);
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    private JComponent createComponent(String id){
        AbstractTableModel tableModel = null;
        if(id.equals(ActionId.CLIENT.getId())) {
            tableModel = new TableClients(clientService.read());
        }
        if(id.equals(ActionId.MANUFACTURE.getId())) {
            tableModel = new TableManufacturer(manufacturerService.read());
        }
        if(id.equals(ActionId.PRODUCT.getId())) {
            tableModel = new TableProduct(productService.read(), manufacturerService);
        }
        if(id.equals(ActionId.CONTRACT.getId())) {
            tableModel = new TableContract(contractService.read(), clientService, productService);
        }
        if(id.equals(ActionId.SCORE.getId())) {
            tableModel = new TableScore(scoreService.read(), contractService);
        }
        if(id.equals(ActionId.PRODUCT_BUYING.getId())) {
            tableModel = new TableProduct(requestService.getProductBuying(), manufacturerService);
        }
        if(id.equals(ActionId.PRODUCT_FOR_ORDER.getId())) {
            tableModel = new TableProduct(requestService.getProductForOrder(), manufacturerService);
        }
        if(id.equals(ActionId.PRODUCT_FOR_STOCK.getId())) {
            tableModel = new TableProduct(requestService.getProductForStock(), manufacturerService);
        }
        if(id.equals(ActionId.PRODUCT_GIVE_AWAY.getId())) {
            tableModel = new TableProduct(requestService.getProductGiveAway(), manufacturerService);
        }
        if(id.equals(ActionId.SCORE_NOT_PAYMENT.getId())) {
            tableModel = new TableScore(requestService.getScoreNotPayment(), contractService);
        }
        if(id.equals(ActionId.SCORE_SUM_PAYMENT.getId())) {
            tableModel = new TableScore(requestService.getScoreSumPayment(), contractService);
        }
        if(id.equals(ActionId.CLIENT_FOR_PRODUCT.getId())) {
            tableModel = new TableClients(requestService.getClientForProduct("поп"));
        }
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 400));
        tables.add(table);
        return scrollPane;
    }

    static class BtnWithName extends JButton{
        BtnWithName(String title, String id){
            super(title);
            setName(id);
        }
    }

    private void checkButton(){
        if(openPanel.equals(ActionId.PRODUCT.getId())){
            for (int i = 5; i<9; i++) {
                btns.get(i).setEnabled(true);
            }
            for (int i = 9; i<12; i++) {
                btns.get(i).setEnabled(false);
            }
        }
        if(openPanel.equals(ActionId.CLIENT.getId())){
            for (int i = 5; i<11; i++) {
                btns.get(i).setEnabled(false);
            }
            btns.get(11).setEnabled(true);
        }
        if(openPanel.equals(ActionId.SCORE.getId())){
            for (int i = 5; i<9; i++) {
                btns.get(i).setEnabled(false);
            }
            for (int i = 9; i<11; i++) {
                btns.get(i).setEnabled(true);
            }
            btns.get(11).setEnabled(false);
        }
        if(openPanel.equals(ActionId.CONTRACT.getId()) || openPanel.equals(ActionId.MANUFACTURE.getId())){
            for (int i = 5; i<12; i++) {
                btns.get(i).setEnabled(false);
            }
        }
    }

    public void updateTable() {
        tables.get(5).setModel(new TableProduct(requestService.getProductBuying(), manufacturerService));
        tables.get(6).setModel(new TableProduct(requestService.getProductForOrder(), manufacturerService));
        tables.get(7).setModel(new TableProduct(requestService.getProductForStock(), manufacturerService));
        tables.get(8).setModel(new TableProduct(requestService.getProductGiveAway(), manufacturerService));
        tables.get(9).setModel(new TableScore(requestService.getScoreNotPayment(), contractService));
        tables.get(10).setModel(new TableScore(requestService.getScoreSumPayment(), contractService));
        tables.get(11).setModel(new TableClients(requestService.getClientForProduct("поп")));
    }

    private void searchClient(){
        JDialog dialog = new JDialog(this, "поиск", true);
        dialog.setLayout(new GridBagLayout());
        dialog.setSize(300, 100);
        dialog.setLocationRelativeTo(null);
        dialog.add(new Label("продукт"), new GridBagConstraints(0, 10, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        dialog.add(new JTextField(10), new GridBagConstraints(1, 10, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        Button search = new Button("Найти");
        dialog.add(search, new GridBagConstraints(1, 11, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0 ,0 ,0), 0, 0));
        CheckCorrect checkCorrect = new CheckCorrect(clientService, dialog);
        Component[] components = dialog.getContentPane().getComponents();
        search.addActionListener(actionEvent1 -> {
            java.util.List<JTextField> texts = new ArrayList<>();
            if(!checkCorrect.checkCorrect(texts)){
                dialog.setVisible(false);
                tables.get(11).setModel(new TableClients(requestService.getClientForProduct((((JTextField) components[1]).getText()))));
            }
        });
        dialog.setVisible(true);
        dialog.setVisible(false);
    }


    public class MouseClick implements MouseListener{
        JTable table;
        JButton button_delete;
        JButton button_edit;
        JFrame frame;

        public MouseClick(JTable table, JButton button, JButton button_edit, JFrame frame) {
            this.table = table;
            this.button_delete = button;
            this.button_edit = button_edit;
            this.frame = frame;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            int row = table.rowAtPoint(e.getPoint());
            int column = table.columnAtPoint(e.getPoint());
            if (row >= 0 && column >= 0) {
                button_delete.setEnabled(true);
                button_edit.setEnabled(true);
                button_delete.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        if(!button_delete.isEnabled());
                        else if(dialogProvider.createDialog(openPanel, frame, clientService, Act.DELETE.getId(), null, null) == 0){
                            if(openPanel.equals(ActionId.CLIENT.getId())) {
                                clientService.delete(EssenceProvider.getClient(table, row));
                                table.setModel(new TableClients(clientService.read()));
                                updateTable();
                            } else if(openPanel.equals(ActionId.MANUFACTURE.getId())){
                                manufacturerService.delete(EssenceProvider.getManufacturer(table, row));
                                table.setModel(new TableManufacturer(manufacturerService.read()));
                                updateTable();
                            } else if(openPanel.equals(ActionId.PRODUCT.getId())){
                                productService.delete(EssenceProvider.getProduct(table, row));
                                table.setModel(new TableProduct(productService.read(), manufacturerService));
                                updateTable();
                            } else if(openPanel.equals(ActionId.CONTRACT.getId())){
                                contractService.delete(EssenceProvider.getContract(table, row));
                                table.setModel(new TableContract(contractService.read(), clientService, productService));
                                updateTable();
                            } else if(openPanel.equals(ActionId.SCORE.getId())){
                                scoreService.delete(EssenceProvider.getScore(table, row));
                                table.setModel(new TableScore(scoreService.read(), contractService));
                                updateTable();
                            }
                        }
                        button_delete.setEnabled(false);
                        button_edit.setEnabled(false);
                    }
                });
                button_edit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        if(!button_edit.isEnabled());
                            else if(openPanel.equals(ActionId.CLIENT.getId()) && dialogProvider.createDialog(openPanel, frame, clientService, Act.EDIT.getId(), table, row) == 0) {
                                table.setModel(new TableClients(clientService.read()));
                                updateTable();
                            } else if(openPanel.equals(ActionId.MANUFACTURE.getId()) && dialogProvider.createDialog(openPanel, frame, manufacturerService, Act.EDIT.getId(), table, row) == 0){
                                table.setModel(new TableManufacturer(manufacturerService.read()));
                                updateTable();
                            } else if(openPanel.equals(ActionId.PRODUCT.getId()) && dialogProvider.createDialog(openPanel, frame, productService, Act.EDIT.getId(), table, row) == 0){
                                table.setModel(new TableProduct(productService.read(), manufacturerService));
                                updateTable();
                            } else if(openPanel.equals(ActionId.CONTRACT.getId()) && dialogProvider.createDialog(openPanel, frame, contractService, Act.EDIT.getId(), table, row) == 0){
                                table.setModel(new TableContract(contractService.read(), clientService, productService));
                                updateTable();
                            } else if(openPanel.equals(ActionId.SCORE.getId()) && dialogProvider.createDialog(openPanel, frame, scoreService, Act.EDIT.getId(), table, row) == 0){
                                table.setModel(new TableScore(scoreService.read(), contractService));
                                updateTable();
                            }
                        button_delete.setEnabled(false);
                        button_edit.setEnabled(false);
                    }
                });
            }
            button_delete.setEnabled(true);
            button_edit.setEnabled(true);
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
