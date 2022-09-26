package com.bigmans.stock.ui.tables;

import com.bigmans.stock.db.ManufacturerService;
import com.bigmans.stock.domain.Product;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TableProduct extends AbstractTableModel {
    private final int columnCount = 7;
    private List<Product> products = new ArrayList<>();
    private ManufacturerService manufacturerService;


    public TableProduct(List<Product> products, ManufacturerService manufacturerService){
        this.manufacturerService = manufacturerService;
        this.products = products;
    }
    @Override
    public int getRowCount() {
        return products.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Product product = products.get(i);
        switch (i1){
            case -1: return product.getId();
            case 0: return product.getName();
            case 1: return product.getCharacteristic();
            case 2: return product.getPriceOne();
            case 3: return product.getPackages();
            case 4: return product.getBatchDelivery();
            case 5: return  product.getAmount();
            case 6: return manufacturerService.getId(product.getManufacturer().getId()).getName();
        }
        return null;
    }


    @Override
    public void setValueAt(Object var1, int var2, int var3) {
        products.add((Product) var1);
    }

    @Override
    public String getColumnName(int columnCount){
        switch (columnCount){
            case 0: return "имя";
            case 1: return "характеристики";
            case 2: return "цена за 1";
            case 3: return "количество";
            case 4: return "условия доставки";
            case 5: return "колличество";
            case 6: return "производитель";
        }
        return "";

    }
}
