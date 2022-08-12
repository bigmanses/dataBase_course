package com.bigmans.stock.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SqlUtils {

    public static List<Integer> execSqlWithReturningId(String query, PreparedStatementSetter statementSetter, Connection connection) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statementSetter.setParams(statement);
            List<Integer> ids = new ArrayList<>();
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                ids.add(rs.getInt(1));
            }
            return ids;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }


}

