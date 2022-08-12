package com.bigmans.stock.domain;

import com.bigmans.stock.db.Model;
import org.jetbrains.annotations.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Model {
    @NotNull
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String characteristic;
    @NotNull
    private int priceOne;
    private String packages;
    private String batchDelivery;
    @NotNull
    private int amount;
    @NotNull
    private Manufacturer manufacturer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return name.equals(product.name) && characteristic.equals(product.characteristic);
    }
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + characteristic.hashCode();
        return result;
    }

    public void setManufacturerId(int id) {
        this.manufacturer = new Manufacturer();
        this.manufacturer.setId(id);
    }

    public Product(@NotNull int id, @NotNull String name, @NotNull String characteristic, @NotNull int priceOne, String packages, String batchDelivery, @NotNull int amount) {
        this.id = id;
        this.name = name;
        this.characteristic = characteristic;
        this.priceOne = priceOne;
        this.packages = packages;
        this.batchDelivery = batchDelivery;
        this.amount = amount;
    }
}
