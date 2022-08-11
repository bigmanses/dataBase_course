package com.bigmans.stock.domain;

import org.jetbrains.annotations.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contract {
    @NotNull
    private int id;
    @NotNull
    private String date_contract;
    private String about;
    @NotNull
    private Product product;
    @NotNull
    private int amount;
    private String terms;
    @NotNull
    private Client client;
    @NotNull
    private int price;
    private boolean isSale;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contract)) return false;
        Contract contract = (Contract) o;
        return date_contract.equals(contract.date_contract) && product.equals(contract.product) && client.equals(contract.client);
    }
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + date_contract.hashCode();
        result = 31 * result + product.hashCode();
        result = 31 * result + client.hashCode();
        return result;
    }
}
