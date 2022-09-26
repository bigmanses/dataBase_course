package com.bigmans.stock.ui.addition;

import com.bigmans.stock.db.*;
import com.bigmans.stock.domain.Client;
import com.bigmans.stock.domain.Contract;
import com.bigmans.stock.domain.Manufacturer;
import com.bigmans.stock.domain.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CheckCorrect {
    private Prototype service;
   JDialog dialog;
    final String noText = new String("Введите данные");

    public CheckCorrect(Prototype service, JDialog dialog) {
        this.service = service;
        this.dialog = dialog;
    }

    public int checkManufacturer(JTextField text) {
        ManufacturerService manufacturerService = new ManufacturerService(service.getConnect());
        List<Manufacturer> manufacturers = manufacturerService.read();
        for (Manufacturer manufacturer: manufacturers){
            if (manufacturer.getName().equals((String)text.getText()))
                return manufacturer.getId();
        }
        return -1;
    }

    public boolean checkCorrect(List<JTextField> texts){
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

    public List<Integer> checkContract(JTextField product, JTextField client) {
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

    public int checkScore(JTextField contract) {
        ContractService contractService = new ContractService(service.getConnect());
        List<Contract> contracts = contractService.read();
        List<Integer> ids = new ArrayList<>();
        for (Contract contract1: contracts){
            if (contract1.getNumber().equals((String)contract.getText()))
                return  contract1.getId();
        }
        return -1;
    }

    public class MouseListenerClick implements MouseListener {

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
