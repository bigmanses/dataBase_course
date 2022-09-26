package com.bigmans.stock.ui.addition;

import com.bigmans.stock.db.Prototype;
import com.bigmans.stock.domain.*;

import javax.swing.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class SaveUpdateData {
    JRadioButton supply;
    JRadioButton shipped;
    JRadioButton payment;
    JFrame frame;
    Prototype service;
    JDialog dialog;

    public SaveUpdateData(JRadioButton supply, JRadioButton shipped, JRadioButton payment, JFrame frame, Prototype service, JDialog dialog) {
        this.supply = supply;
        this.shipped = shipped;
        this.payment = payment;
        this.frame = frame;
        this.service = service;
        this.dialog = dialog;
    }

    public void saveClient(List<JTextField> texts) {
        Client client = new Client();
        client.setName(texts.get(0).getText());
        client.setPhone(texts.get(1).getText());
        client.setAddress(texts.get(2).getText());
        client.setFax(texts.get(3).getText());
        client.setScore(texts.get(4).getText());
        client.setNotes(texts.get(5).getText());
        service.create(client);
    }

    public void updateClient(List<JTextField> texts, int id) {
        Client client = new Client();
        client.setId(id);
        client.setName(texts.get(0).getText());
        client.setPhone(texts.get(1).getText());
        client.setAddress(texts.get(2).getText());
        client.setFax(texts.get(3).getText());
        client.setScore(texts.get(4).getText());
        client.setNotes(texts.get(5).getText());
        service.update(client);
    }


    public void saveManufacturer(List<JTextField> texts){
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(texts.get(0).getText());
        manufacturer.setAddress(texts.get(1).getText());
        manufacturer.setDirector(texts.get(2).getText());
        manufacturer.setAccountant(texts.get(3).getText());
        manufacturer.setRequisites(texts.get(4).getText());
        service.create(manufacturer);
    }

    public void updateManufacturer(List<JTextField> texts, int id){
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(id);
        manufacturer.setName(texts.get(0).getText());
        manufacturer.setAddress(texts.get(1).getText());
        manufacturer.setDirector(texts.get(2).getText());
        manufacturer.setAccountant(texts.get(3).getText());
        manufacturer.setRequisites(texts.get(4).getText());
        service.update(manufacturer);
    }

    public void saveProduct(List<JTextField> texts, int manufacturerId){
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

    public void updateProduct(List<JTextField> texts, int manufacturerId, int productId){
        Product product = new Product();
        try {
            product.setId(productId);
            product.setName(texts.get(0).getText());
            product.setCharacteristic(texts.get(1).getText());
            product.setPriceOne(Integer.parseInt(texts.get(2).getText()));
            product.setPackages(texts.get(3).getText());
            product.setBatchDelivery(texts.get(4).getText());
            product.setAmount(Integer.parseInt(texts.get(5).getText()));
            product.setManufacturerId(manufacturerId);
            service.update(product);
        }
        catch (NumberFormatException exception){
            dialog.setVisible(false);
            JOptionPane.showMessageDialog(frame, "Ошибка ввода данных, ожидался ввод целого числа " , "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    public void saveContract(List<JTextField> texts, List<Integer> ids){
        Contract contract = new Contract();
        try {
            contract.setDate_contract(Date.valueOf(LocalDate.parse((texts.get(0).getText()))));
            contract.setNumber((texts.get(1).getText()));
            contract.setAbout(texts.get(2).getText());
            contract.setProductId(ids.get(0));
            contract.setAmount(Integer.parseInt(texts.get(4).getText()));
            contract.setTerms((texts.get(5).getText()));
            contract.setClientId(ids.get(1));
            contract.setPrice(Integer.parseInt(texts.get(7).getText()));
            contract.setSale(supply.isSelected());
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

    public void updateContract(List<JTextField> texts, List<Integer> ids, int contractId){
        Contract contract = new Contract();
        try {
            contract.setId(contractId);
            contract.setDate_contract(Date.valueOf(LocalDate.parse((texts.get(0).getText()))));
            contract.setNumber((texts.get(1).getText()));
            contract.setAbout(texts.get(2).getText());
            contract.setProductId(ids.get(0));
            contract.setAmount(Integer.parseInt(texts.get(4).getText()));
            contract.setTerms((texts.get(5).getText()));
            contract.setClientId(ids.get(1));
            contract.setPrice(Integer.parseInt(texts.get(7).getText()));
            contract.setSale(supply.isSelected());
            service.update(contract);
        }
        catch (NumberFormatException exception){
            dialog.setVisible(false);
            JOptionPane.showMessageDialog(frame, "Ошибка ввода данных, ожидался ввод целого числа " , "Error", JOptionPane.ERROR_MESSAGE);
        }
        catch (DateTimeParseException exception){
            JOptionPane.showMessageDialog(frame, "Ошибка ввода даты, введите дату, как указано в примере " , "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void saveScore(List<JTextField> texts, Integer id){
        Score score = new Score();
        try {
            score.setName(texts.get(0).getText());
            score.setNumber(texts.get(1).getText());
            score.setContractId(id);
            score.setDate_score(Date.valueOf(LocalDate.parse((texts.get(3).getText()))));
            score.setSum(Integer.parseInt(texts.get(4).getText()));
            score.setShipment_status(shipped.isSelected());
            score.setPayment_status(payment.isSelected());
            service.create(score);
        }
        catch (NumberFormatException exception){
            dialog.setVisible(false);
            JOptionPane.showMessageDialog(frame, "Ошибка ввода данных, ожидался ввод целого числа " , "Error", JOptionPane.ERROR_MESSAGE);
        }
        catch (DateTimeParseException exception){
            JOptionPane.showMessageDialog(frame, "Ошибка ввода даты, введите дату, как указано в примере " , "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateScore(List<JTextField> texts, Integer id, int scoreId){
        Score score = new Score();
        try {
            score.setId(scoreId);
            score.setName(texts.get(0).getText());
            score.setNumber(texts.get(1).getText());
            score.setContractId(id);
            score.setDate_score(Date.valueOf(LocalDate.parse((texts.get(3).getText()))));
            score.setSum(Integer.parseInt(texts.get(4).getText()));
            score.setShipment_status(shipped.isSelected());
            score.setPayment_status(payment.isSelected());
            service.update(score);
        }
        catch (NumberFormatException exception){
            dialog.setVisible(false);
            JOptionPane.showMessageDialog(frame, "Ошибка ввода данных, ожидался ввод целого числа " , "Error", JOptionPane.ERROR_MESSAGE);
        }
        catch (DateTimeParseException exception){
            JOptionPane.showMessageDialog(frame, "Ошибка ввода даты, введите дату, как указано в примере " , "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
