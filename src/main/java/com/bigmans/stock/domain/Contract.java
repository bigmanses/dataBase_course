package com.bigmans.stock.domain;

import com.bigmans.stock.db.Model;
import org.jetbrains.annotations.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contract implements Model {
    private int id;
    private java.sql.Date date_contract;
    private String number;
    private String about;
    private Product product;
    private int amount;
    private String terms;
    private Client client;
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

    public String getName() {
        return about;
    }

    public void setProductId(int id) {
        this.product = new Product();
        this.product.setId(id);
    }

    public void setClientId(int id) {
        this.client = new Client();
        this.client.setId(id);
    }

    public Contract(@NotNull int id, @NotNull Date date_contract, String about, @NotNull int amount, String terms, @NotNull int price, boolean isSale) {
        this.id = id;
        this.date_contract = date_contract;
        this.about = about;
        this.amount = amount;
        this.terms = terms;
        this.price = price;
        this.isSale = isSale;
    }
}
