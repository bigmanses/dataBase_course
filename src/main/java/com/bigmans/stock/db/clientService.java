package com.bigmans.stock.db;

import com.bigmans.stock.domain.Client;

import java.util.List;

public class clientService {
    private List<Client> clients;
    public enum SQLClient {
        GET("SELECT* from Client"),
        INSERT("INSERT INTO client VALUES (DEFAULT, (?), (?), (?)) RETURNING id"),
        DELETE("DELETE FROM users WHERE id = (?) AND login = (?) AND password = (?) RETURNING id"),
        UPDATE("UPDATE users SET password = (?) WHERE id = (?) RETURNING id");

        String QUERY;

        SQLClient(String QUERY) {
            this.QUERY = QUERY;
        }
    }

}
