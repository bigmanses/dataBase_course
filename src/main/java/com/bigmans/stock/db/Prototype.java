package com.bigmans.stock.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Prototype<Entity> {
    List<Integer> create(Entity model);
    List<Entity> read();
    boolean update(Entity model);
    boolean delete(Entity model);
    Entity getId(int id) throws SQLException;
    Connection getConnect();
}
