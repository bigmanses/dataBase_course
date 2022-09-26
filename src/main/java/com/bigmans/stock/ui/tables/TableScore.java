package com.bigmans.stock.ui.tables;

import com.bigmans.stock.db.ContractService;
import com.bigmans.stock.domain.Score;
import com.bigmans.stock.ui.VersionType;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TableScore extends AbstractTableModel {

    private final int columnCount = 7;
    private List<Score> scores = new ArrayList<>();
    private ContractService contractService;


    public TableScore(List<Score> scores,ContractService contractService){
        if(scores!=null) {
            this.scores = scores;
        }
        this.contractService = contractService;
    }
    @Override
    public int getRowCount() {
        return scores.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Score score = scores.get(i);
        switch (i1){
            case -1: return score.getId();
            case 0: return score.getName();
            case 1: return score.getNumber();
            case 2: return contractService.getId(score.getContract().getId()).getNumber();
            case 3: return score.getDate_score();
            case 4: return score.getSum();
            case 5: if(score.isShipment_status()) return VersionType.SHIPPED.getId(); else return VersionType.NO_SHIPPED.getId();
            case 6: if(score.isPayment_status()) return VersionType.PAYMENT.getId(); else return VersionType.NO_PAYMENT.getId();
            case 7: return score.getContract().getId();
        }
        return null;
    }


    @Override
    public void setValueAt(Object var1, int var2, int var3) {
        scores.add((Score) var1);
    }

    @Override
    public String getColumnName(int columnCount){
        switch (columnCount){
            case 0: return "имя";
            case 1: return "номер";
            case 2: return "номер договора";
            case 3: return "дата";
            case 4: return "сумма";
            case 5: return "отгрузка";
            case 6: return "оплата";
        }
        return "";

    }
}
