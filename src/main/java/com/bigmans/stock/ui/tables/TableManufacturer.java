package com.bigmans.stock.ui.tables;

import com.bigmans.stock.domain.Client;
import com.bigmans.stock.domain.Manufacturer;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TableManufacturer extends AbstractTableModel {
    private final int columnCount = 5;
    private List<Manufacturer> manufacturers = new ArrayList<>();


    public TableManufacturer(List<Manufacturer> manufacturers){
        this.manufacturers = manufacturers;
    }
    @Override
    public int getRowCount() {
        return manufacturers.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Manufacturer manufacturer = manufacturers.get(i);
        switch (i1){
            case 0: return manufacturer.getName();
            case 1: return manufacturer.getAddress();
            case 2: return manufacturer.getDirector();
            case 3: return manufacturer.getAccountant();
            case 4: return manufacturer.getRequisites();
        }
        return null;
    }


    @Override
    public void setValueAt(Object var1, int var2, int var3) {
        manufacturers.add((Manufacturer) var1);
    }

    @Override
    public String getColumnName(int columnCount){
        switch (columnCount){
            case 0: return "имя";
            case 1: return "адрес";
            case 2: return "директор";
            case 3: return "бухгалтер";
            case 4: return "реквизиты";
        }
        return "";

    }
}
