package com.bigmans.stock.ui.actions;

import com.bigmans.stock.db.ClientService;
import com.bigmans.stock.db.Prototype;
import com.bigmans.stock.domain.Client;
import com.bigmans.stock.ui.Act;
import com.bigmans.stock.ui.ActionId;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DialogProvider {
    /** Интерфейс сервисов для работы с БД */
    private Prototype service;
    /** Наше диалоговое окно */
    private JDialog dialog;
    final String noText = new String("Введите данные");
    public int createDialog(String key, JFrame frame, Prototype prototype, String act) {
        this.service = prototype;
        if(act.equals(Act.ADD.getId())) {
            this.dialog = new JDialog(frame, "add", true);
            if (key.equals(ActionId.CLIENT.getId())) {
                JDialog dialog = getClientDialog();
                listenDialogClient(dialog);
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
        System.out.println("add");
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

    private void listenDialogClient(JDialog dialog){
        Component[] components = dialog.getContentPane().getComponents();
        Button button = (Button) dialog.getContentPane().getComponents()[components.length - 1];
        button.addActionListener(actionEvent1 -> {
            boolean error = false;
            List<JTextField> texts = new ArrayList<>();
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
            if(!error){
                saveClient(texts);
                dialog.setVisible(false);
            }
        });
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

