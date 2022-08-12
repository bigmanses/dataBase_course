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
    private String date_score;
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
}
