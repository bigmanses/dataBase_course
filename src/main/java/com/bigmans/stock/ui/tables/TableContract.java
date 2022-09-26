package com.bigmans.stock.ui.tables;

import com.bigmans.stock.db.ClientService;
import com.bigmans.stock.db.ProductService;
import com.bigmans.stock.domain.Contract;
import com.bigmans.stock.ui.VersionType;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TableContract extends AbstractTableModel {

    private final int columnCount = 9;
    private List<Contract> contracts = new ArrayList<>();
    private ClientService clientService;
    private ProductService productService;


    public TableContract(List<Contract> contracts, ClientService clientService, ProductService productService){
        if(contracts != null) {
            this.clientService = clientService;
        }
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
            case -1: return contract.getId();
            case 0: return contract.getDate_contract();
            case 1: return contract.getNumber();
            case 2: return contract.getAbout();
            case 3: return productService.getId(contract.getProduct().getId()).getName();
            case 4: return contract.getAmount();
            case 5: return contract.getTerms();
            case 6: return clientService.getId(contract.getClient().getId()).getName();
            case 7: return contract.getPrice();
            case 8: if(contract.isSale()) return VersionType.SUPPLY.getId(); else return VersionType.SALE.getId();
            case 9: return contract.getProduct().getId();
            case 10: return contract.getClient().getId();
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
            case 1: return "номер";
            case 2: return "о контракте";
            case 3: return "продукт";
            case 4: return "количество";
            case 5: return "условия контракта";
            case 6: return "покупатель";
            case 7: return "цена";
            case 8: return "поставка/покупка";
        }
        return "";

    }
}
