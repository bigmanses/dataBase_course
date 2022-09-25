package com.bigmans.stock.ui.allMenu;

import com.bigmans.stock.db.ClientService;
import com.bigmans.stock.domain.Client;
import com.bigmans.stock.ui.Act;
import com.bigmans.stock.ui.ActionId;
import com.bigmans.stock.ui.actions.DialogProvider;
import com.bigmans.stock.ui.tables.TableClients;

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
    JPanel contentPanel = new JPanel(new GridLayout(1,3));
    /** Кнопки верхней панели */
    List<BtnWithName> btns = Arrays.asList(new BtnWithName("Клиенты", ActionId.CLIENT.getId()),  new BtnWithName("Продукты", ActionId.PRODUCT.getId()),
            new BtnWithName("Контракты", ActionId.CONTRACT.getId()), new BtnWithName("Производители", ActionId.MANUFACTURE.getId()),
            new BtnWithName("Счета", ActionId.SCORE.getId()));
    /** Лист с нашими таблицами */
    List<JTable> tables = new ArrayList<>();
    /** Кнопки нижней панели */
    JButton add = new JButton("add");
    JButton deleteBtn = new JButton("delete");
    JButton editBtn = new JButton("redact");
    /** Сущность для связи с БД */
    Connection connection;
    /** Сервис для работы с клиентами через БД */
    ClientService clientService;
    /** Поставщик диалогового окна */
    DialogProvider dialogProvider = new DialogProvider();
    /** Флаг действующей панелии */
    String openPanel = new String();

    public MainMenu(Connection connection) throws HeadlessException {
        super("Склад");
        this.connection = connection;
        this.clientService = new ClientService(connection);
        content.add("empty", new JPanel());
        Arrays.stream(ActionId.values())
              .forEach(a->content.add(a.getId(), createComponent(a.getId())));

        btns.forEach(b -> b.addActionListener(actionEvent -> {
            System.out.println("click: " + ((JComponent)actionEvent.getSource()).getName());
            layout.show(content, ((JComponent)actionEvent.getSource()).getName());
            openPanel = ((JComponent)actionEvent.getSource()).getName();
            add.setEnabled(true);}
        ));

        add.addActionListener(actionEvent -> {
            if(openPanel.equals(ActionId.CLIENT.getId())) {
                dialogProvider.createDialog(openPanel, this, clientService, Act.ADD.getId());
            }
            tables.get(0).setModel(new TableClients(clientService.read()));
            deleteBtn.setEnabled(false);
        });

        tables.get(0).addMouseListener(new MouseClick(tables.get(0), deleteBtn, this));
        initPanels();
        initFrame();
    }



    private void initPanels(){
        for (BtnWithName btnWithName: btns){
            btnPanel.add(btnWithName);
        }
        add.setEnabled(false);
        deleteBtn.setEnabled(false);
        editBtn.setEnabled(false);
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

    public class MouseClick implements MouseListener{
        JTable table;
        JButton button;
        JFrame frame;

        public MouseClick(JTable table, JButton button, JFrame frame) {
            this.table = table;
            this.button = button;
            this.frame = frame;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            int row = table.rowAtPoint(e.getPoint());
            int column = table.columnAtPoint(e.getPoint());
            if (row >= 0 && column >= 0) {
                button.setEnabled(true);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        if(!button.isEnabled());
                        else if(dialogProvider.createDialog(openPanel, frame, clientService, Act.DELETE.getId()) == 0){
                            if(openPanel.equals(ActionId.CLIENT.getId())) {
                                clientService.delete(getClient(table, row));
                            }
                        }
                        deleteBtn.setEnabled(false);
                        table.setModel(new TableClients(clientService.read()));
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

    private Client getClient(JTable table, int row){
        Client client = new Client();
        client.setName((String) table.getModel().getValueAt(row,0));
        client.setPhone((String) table.getModel().getValueAt(row,1));
        client.setAddress((String) table.getModel().getValueAt(row,2));
        client.setFax((String) table.getModel().getValueAt(row,3));
        client.setScore((String) table.getModel().getValueAt(row,4));
        client.setNotes((String) table.getModel().getValueAt(row,5));
        return client;

    }
}
