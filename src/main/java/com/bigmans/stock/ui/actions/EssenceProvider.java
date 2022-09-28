package com.bigmans.stock.ui.actions;

import com.bigmans.stock.domain.*;
import com.bigmans.stock.ui.VersionType;

import javax.swing.*;
import java.sql.Date;

public class EssenceProvider {
    static public Client getClient(JTable table, int row){
        Client client = new Client();
        client.setName((String) table.getModel().getValueAt(row,0));
        client.setPhone((String) table.getModel().getValueAt(row,1));
        client.setAddress((String) table.getModel().getValueAt(row,2));
        client.setFax((String) table.getModel().getValueAt(row,3));
        client.setScore((String) table.getModel().getValueAt(row,4));
        client.setNotes((String) table.getModel().getValueAt(row,5));
        return client;
    }

    static public Manufacturer getManufacturer(JTable table, int row){
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName((String) table.getModel().getValueAt(row,0));
        manufacturer.setAddress((String) table.getModel().getValueAt(row,1));
        manufacturer.setDirector((String) table.getModel().getValueAt(row,2));
        manufacturer.setAccountant((String) table.getModel().getValueAt(row,3));
        manufacturer.setRequisites((String) table.getModel().getValueAt(row,4));
        return manufacturer;
    }

    static public Product getProduct(JTable table, int row){
        Product product = new Product();
        product.setName((String) table.getModel().getValueAt(row,0));
        product.setCharacteristic((String) table.getModel().getValueAt(row,1));
        product.setPriceOne((Integer) table.getModel().getValueAt(row,2));
        product.setPackages((String) table.getModel().getValueAt(row,3));
        product.setBatchDelivery((String) table.getModel().getValueAt(row,4));
        product.setAmount((Integer) table.getModel().getValueAt(row,5));
        product.setManufacturerId((Integer) table.getModel().getValueAt(row,-1));
        product.getManufacturer().setName((String) table.getModel().getValueAt(row,6));
        return product;
    }

    static public Contract getContract(JTable table, int row){
        Contract contract = new Contract();
        contract.setId((Integer) table.getModel().getValueAt(row,-1));
        contract.setDate_contract((Date) (table.getModel().getValueAt(row,0)));
        contract.setNumber((String) table.getModel().getValueAt(row,1));
        contract.setAbout((String) table.getModel().getValueAt(row,2));
        contract.setProductId((Integer) table.getModel().getValueAt(row,9));
        contract.getProduct().setName((String) table.getModel().getValueAt(row,3));
        contract.setAmount((Integer) table.getModel().getValueAt(row,4));
        contract.setTerms((String) table.getModel().getValueAt(row,5));
        contract.setClientId((Integer) table.getModel().getValueAt(row,10));
        contract.getClient().setName((String) table.getModel().getValueAt(row,6));
        contract.setPrice((Integer) table.getModel().getValueAt(row,7));
        contract.setSale(table.getModel().getValueAt(row, 8).equals(VersionType.SUPPLY.getId()));
        return contract;
    }

    static public Score getScore(JTable table, int row){
        Score score = new Score();
        score.setName((String) table.getModel().getValueAt(row,0));
        score.setNumber((String) table.getModel().getValueAt(row,1));
        score.setContractId((Integer) table.getModel().getValueAt(row, 7));
        score.getContract().setNumber((String) table.getModel().getValueAt(row, 2));
        score.setDate_score((Date) (table.getModel().getValueAt(row,3)));
        score.setSum((Integer) table.getModel().getValueAt(row,4));
        score.setShipment_status(table.getModel().getValueAt(row,5).equals(VersionType.SHIPPED.getId()));
        score.setPayment_status(table.getModel().getValueAt(row,6).equals(VersionType.PAYMENT.getId()));
        return score;
    }
}
