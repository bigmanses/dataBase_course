package com.bigmans.stock.ui.tables;

import com.bigmans.stock.domain.Client;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TableClients extends AbstractTableModel {

    private final int columnCount = 6;
    private List<Client> clients = new ArrayList<>();


    public TableClients(List<Client> clients){
        this.clients = clients;
    }
    @Override
    public int getRowCount() {
        return clients.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Client client = clients.get(i);
        switch (i1){
            case 0: return client.getName();
            case 1: return client.getPhone();
            case 2: return client.getAddress();
            case 3: return client.getFax();
            case 4: return client.getScore();
            case 5: return client.getNotes();
        }
        return null;
    }


    @Override
    public void setValueAt(Object var1, int var2, int var3) {
        clients.add((Client) var1);
    }

    @Override
    public String getColumnName(int columnCount){
        switch (columnCount){
            case 0: return "name";
            case 1: return "phone";
            case 2: return "address";
            case 3: return "fax";
            case 4: return "score";
            case 5: return "notes";
        }
        return "";

    }
}
