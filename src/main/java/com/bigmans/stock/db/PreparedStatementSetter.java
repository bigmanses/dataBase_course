package com.bigmans.stock.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
interface PreparedStatementSetter{
    void setParams(PreparedStatement statement) throws SQLException;
}
