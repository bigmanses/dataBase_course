package com.bigmans.stock.domain;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @NotNull
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String phone;
    @NotNull
    private String address;
    private String fax;
    @NotNull
    private String score;
    @NotNull
    private String notes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return name.equals(client.name) && phone.equals(client.phone) && address.equals(client.address) && score.equals(client.score);
    }
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + score.hashCode();
        return result;
    }


}
