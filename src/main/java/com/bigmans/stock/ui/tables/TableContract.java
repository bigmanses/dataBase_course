package com.bigmans.stock.ui.tables;

import com.bigmans.stock.db.ClientService;
import com.bigmans.stock.db.ManufacturerService;
import com.bigmans.stock.db.ProductService;
import com.bigmans.stock.domain.Contract;
import com.bigmans.stock.domain.Product;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TableContract extends AbstractTableModel {

    private final int columnCount = 8;
    private List<Contract> contracts = new ArrayList<>();
    private ClientService clientService;
    private ProductService productService;


    public TableContract(List<Contract> contracts, ClientService clientService, ProductService productService){
        this.clientService = clientService;
        this.contracts = contracts;
        this.productService = productService;
    }
    @Override
    public int getRowCount() {
        return contracts.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Contract contract = contracts.get(i);
        switch (i1){
            case 0: return contract.getDate_contract();
            case 1: return contract.getAbout();
            case 2: return productService.getId(contract.getProduct().getId()).getName();
            case 3: return contract.getAmount();
            case 4: return contract.getTerms();
            case 5: return clientService.getId(contract.getClient().getId()).getName();
            case 6: return contract.getPrice();
            case 7: if(contract.isSale()) return "Поставка"; else return "Покупка";
            case 8: return contract.getProduct().getId();
            case 9: return contract.getClient().getId();
        }
        return null;
    }


    @Override
    public void setValueAt(Object var1, int var2, int var3) {
        contracts.add((Contract) var1);
    }

    @Override
    public String getColumnName(int columnCount){
        switch (columnCount){
            case 0: return "дата";
            case 1: return "о контракте";
            case 2: return "продукт";
            case 3: return "количество";
            case 4: return "условия контракта";
            case 5: return "покупатель";
            case 6: return "цена";
            case 7: return "поставка/покупка";
        }
        return "";

    }
}
