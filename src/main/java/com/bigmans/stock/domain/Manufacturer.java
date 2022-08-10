package com.bigmans.stock.domain;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manufacturer {
    @NotNull
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private String director;
    private String accountant;
    @NotNull
    private String requisites;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Manufacturer)) return false;
        Manufacturer manufacturer = (Manufacturer) o;
        return name.equals(manufacturer.name) && director.equals(manufacturer.director) && address.equals(manufacturer.address);
    }
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + director.hashCode();
        result = 31 * result + address.hashCode();
        return result;
    }
}
