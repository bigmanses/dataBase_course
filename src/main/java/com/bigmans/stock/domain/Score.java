package com.bigmans.stock.domain;

import com.bigmans.stock.db.Model;
import org.jetbrains.annotations.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Score implements Model {
    @NotNull
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String number;
    @NotNull
    private Contract contract;
    @NotNull
    private java.sql.Date date_score;
    @NotNull
    private int sum;
    private boolean shipment_status;
    private boolean payment_status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Score)) return false;
        Score score = (Score) o;
        return name.equals(score.name) && number.equals(score.number) && contract.equals(score.contract);
    }
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + number.hashCode();
        result = 31 * result + contract.hashCode();
        return result;
    }

    public Score(@NotNull int id, @NotNull String name, @NotNull String number, @NotNull java.sql.Date date_score, @NotNull int sum, boolean shipment_status, boolean payment_status) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.date_score = date_score;
        this.sum = sum;
        this.shipment_status = shipment_status;
        this.payment_status = payment_status;
    }

    public void setContractId(int id) {
        this.contract = new Contract();
        this.contract.setId(id);
    }
}
