package com.bigmans.stock.ui.allMenu;

import com.bigmans.stock.db.*;
import com.bigmans.stock.ui.Act;
import com.bigmans.stock.ui.ActionId;
import com.bigmans.stock.ui.actions.DialogProvider;
import com.bigmans.stock.ui.actions.EssenceProvider;
import com.bigmans.stock.ui.tables.*;
import org.jetbrains.annotations.NotNull;

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

public class MainMenuClient extends  JFrame{

    /** Макет с помощью которого мы можем переключать наши таблицы*/
    CardLayout layout = new CardLayout();
    /** Панель, в которой хранятся все наши таблицы */
    final JPanel content = new JPanel(layout);
    /** Верхняя панель с кнопками */
    JPanel btnPanel = new JPanel(new GridLayout(1,5));
    /** Нижняя панель с кнопками */
    JPanel contentPanel = new JPanel(new GridLayout(1,1));
    /** Кнопки верхней панели */
    List<MainMenuAdmin.BtnWithName> btns = Arrays.asList(new MainMenuAdmin.BtnWithName("Продукты", ActionId.PRODUCT.getId()),
            new MainMenuAdmin.BtnWithName("Контракты", ActionId.CONTRACT.getId()), new MainMenuAdmin.BtnWithName("Счета", ActionId.SCORE.getId()));;
    /** Лист с нашими таблицами */
    List<JTable> tables = new ArrayList<>();
    /** Кнопки нижней панели */
    JButton dogovor = new JButton("Офромить договор");
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
    String nameUser;

    public MainMenuClient(Connection connection, String name) throws HeadlessException {
        super("Склад - " + "User: " + name);
        this.connection = connection;
        this.clientService = new ClientService(connection);
        this.manufacturerService = new ManufacturerService(connection);
        this.productService = new ProductService(connection);
        this.contractService = new ContractService(connection);
        this.scoreService = new ScoreService(connection);
        this.requestService = new RequestService(connection);
        this.nameUser = name;
        content.add("empty", new JPanel());
        content.add(ActionId.PRODUCT.getId(), createComponent(ActionId.PRODUCT.getId()));
        content.add(ActionId.CONTRACT.getId(), createComponent(ActionId.CONTRACT.getId()));
        content.add(ActionId.SCORE.getId(), createComponent(ActionId.SCORE.getId()));
        btns.forEach(b -> b.addActionListener(actionEvent -> {
                    System.out.println("click: " + ((JComponent)actionEvent.getSource()).getName());
                    layout.show(content, ((JComponent)actionEvent.getSource()).getName());
                    openPanel = ((JComponent)actionEvent.getSource()).getName();
                    dogovor.setEnabled(false);
                }
        ));

        tables.get(0).addMouseListener(new MouseClick(tables.get(0), dogovor,this));
        initPanels();
        initFrame();
    }



    private void initPanels(){
        for (int i = 0; i<3; i++){
            btnPanel.add(btns.get(i));
        }
        dogovor.setEnabled(false);
        contentPanel.add(dogovor);
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
        if(id.equals(ActionId.PRODUCT.getId())) {
            tableModel = new TableProduct(requestService.getProductBuying(), manufacturerService);
        }
        if(id.equals(ActionId.CONTRACT.getId())) {
            tableModel = new TableContract(contractService.getName(nameUser), clientService, productService);
        }
        if(id.equals(ActionId.SCORE.getId())) {
            tableModel = new TableScore(scoreService.getName(nameUser), contractService);
        }
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 400));
        tables.add(table);
        return scrollPane;
    }

    public void updateTable() {
        tables.get(0).setModel(new TableProduct(requestService.getProductBuying(), manufacturerService));
        tables.get(1).setModel(new TableContract(contractService.getName(nameUser), clientService, productService));
        tables.get(2).setModel(new TableScore(scoreService.getName(nameUser), contractService));
    }

    public class MouseClick implements MouseListener{
        JTable table;
        JButton button_dogovor;
        JFrame frame;

        public MouseClick(JTable table, JButton button_dogovor, JFrame frame) {
            this.table = table;
            this.button_dogovor = button_dogovor;
            this.frame = frame;
        }

        @Override
        public void mouseClicked(@NotNull MouseEvent e) {
            int row = table.rowAtPoint(e.getPoint());
            int column = table.columnAtPoint(e.getPoint());
            if (row >= 0 && column >= 0) {
                button_dogovor.setEnabled(true);
                button_dogovor.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        if(!button_dogovor.isEnabled());
                        else if(dialogProvider.createDialog(frame, contractService, table, row, nameUser) == 0) {
                            table.setModel(new TableContract(contractService.read(), clientService, productService));
                            updateTable();
                        }
                        button_dogovor.setEnabled(false);
                        }
                });
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
