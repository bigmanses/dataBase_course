package com.bigmans.stock.domain;

import org.jetbrains.annotations.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
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
}
