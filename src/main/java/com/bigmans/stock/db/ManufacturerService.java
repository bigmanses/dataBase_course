package com.bigmans.stock.db;

import com.bigmans.stock.domain.Manufacturer;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class ManufacturerService {
    /** Соединение с нашей БД */
    @NotNull
    private final Connection connection;

    public ManufacturerService(final Connection connection) {
        this.connection = connection;
    }

    /**
     * Создание и добавление строки в наш столбец
     * @param manufacturer - новый экземпляр сущности, который мы хотим добавить в нашу базу
     * @return result - флаг успешного добавления строки в нашу таблицу
     */
    public boolean create(@NotNull final Manufacturer manufacturer) {
        List<Integer> ids = execSqlWithReturningId(SqlManufacturer.INSERT.QUERY, (statement) -> {
            statement.setString(1, manufacturer.getName());
            statement.setString(2, manufacturer.getAddress());
            statement.setString(3, manufacturer.getDirector());
            statement.setString(4, manufacturer.getAccountant());
            statement.setString(5, manufacturer.getRequisites());
        });
        return ids.size() > 0;
    }

    public List<Integer> execSqlWithReturningId(String query, PreparedStatementSetter statementSetter) {
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

    @FunctionalInterface
    interface PreparedStatementSetter{
        void setParams(PreparedStatement statement) throws SQLException;
    }

    /**
     * Чтение таблицы из БД
     * @return все клиенты
     */
    public List<Manufacturer> read() {
        List<Manufacturer> manufacturers = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SqlManufacturer.GET.QUERY)) {
            final ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Manufacturer result = new Manufacturer();
                result.setId(Integer.parseInt(rs.getString(1)));
                result.setName(rs.getString(2));
                result.setAddress(rs.getString(3));
                result.setDirector(rs.getString(4));
                result.setAccountant(rs.getString(5));
                result.setRequisites(rs.getString(6));
                manufacturers.add(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return manufacturers;
    }

    /**
     * Редактирование строки в таблице
     * @param manufacturer - наш экземпляр сущности, который мы хотим отредактировать
     * @return result -  флаг успешного редактирования строки в нашей таблице
     */
    public boolean update(@NotNull final Manufacturer manufacturer) {
//        boolean result = false;
//
//        try (PreparedStatement statement = connection.prepareStatement(SqlManufacturer.UPDATE.QUERY)) {
//            statement.setString(1, manufacturer.getName());
//            statement.setString(2, manufacturer.getAddress());
//            statement.setString(3, manufacturer.getDirector());
//            statement.setInt(4, manufacturer.getId());
//            result = statement.executeQuery().next();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        List<Integer> ids = execSqlWithReturningId(SqlManufacturer.UPDATE.QUERY, (statement) -> {
            statement.setString(1, manufacturer.getName());
            statement.setString(2, manufacturer.getAddress());
            statement.setString(3, manufacturer.getDirector());
            statement.setInt(4, manufacturer.getId());
        });
        return ids.size() > 0;
    }

    /**
     * Удаление строки из нашей таблицы в БД
     * @param manufacturer - наш экземпляр сущности, который мы хотим удалить
     * @return result -  флаг успешного редактирования строки в нашей таблице
     */
    public boolean delete(@NotNull final Manufacturer manufacturer) {
        boolean result = false;

        try (PreparedStatement statement = connection.prepareStatement(SqlManufacturer.DELETE.QUERY)) {
            statement.setString(1, manufacturer.getName());
            statement.setString(2, manufacturer.getAddress());
            statement.setString(3, manufacturer.getDirector());
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Перечесление необходимых для работы с клиентом запросов
     */
    public enum SqlManufacturer {
        GET("SELECT* from manufacturer"),
        INSERT("INSERT INTO manufacturer VALUES (DEFAULT, (?), (?), (?), (?), (?)) RETURNING id"),
        DELETE("DELETE FROM manufacturer WHERE  name = (?) AND address = (?) AND director = (?) RETURNING id"),
        UPDATE("UPDATE manufacturer SET name = (?), address = (?), director = (?) WHERE id = (?) RETURNING id");

        final String QUERY;

        SqlManufacturer(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
