package com.bigmans.stock.db;

import com.bigmans.stock.domain.Manufacturer;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManufacturerService implements Prototype<Manufacturer>{
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
    @Override
    public  List<Integer>  create(@NotNull final Manufacturer manufacturer) {
        List<Integer> ids = SqlUtils.execSqlWithReturningId(SqlManufacturer.INSERT.QUERY, (statement) -> {
            statement.setString(1, manufacturer.getName());
            statement.setString(2, manufacturer.getAddress());
            statement.setString(3, manufacturer.getDirector());
            statement.setString(4, manufacturer.getAccountant());
            statement.setString(5, manufacturer.getRequisites());
        }, connection);
        return ids;
    }

    /**
     * Чтение таблицы из БД
     * @return все клиенты
     */
    @Override
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
    @Override
    public boolean update(@NotNull final Manufacturer manufacturer) {

        List<Integer> ids = SqlUtils.execSqlWithReturningId(SqlManufacturer.UPDATE.QUERY, (statement) -> {
            statement.setString(1, manufacturer.getName());
            statement.setString(2, manufacturer.getAddress());
            statement.setString(3, manufacturer.getDirector());
            statement.setInt(4, manufacturer.getId());
        }, connection);
        return ids.size() > 0;
    }

    /**
     * Удаление строки из нашей таблицы в БД
     * @param manufacturer - наш экземпляр сущности, который мы хотим удалить
     * @return result -  флаг успешного редактирования строки в нашей таблице
     */
    @Override
    public boolean delete(@NotNull final Manufacturer manufacturer) {
        List<Integer> ids = SqlUtils.execSqlWithReturningId(SqlManufacturer.DELETE.QUERY, (statement) -> {
            statement.setString(1, manufacturer.getName());
            statement.setString(2, manufacturer.getAddress());
            statement.setString(3, manufacturer.getDirector());
            statement.setInt(4, manufacturer.getId());
        }, connection);
        return ids.size() > 0;
    }

    /**
     * Перечесление необходимых для работы с клиентом запросов
     */
    public enum SqlManufacturer {
        GET("SELECT* from manufacturer"),
        INSERT("INSERT INTO manufacturer VALUES (DEFAULT, (?), (?), (?), (?), (?)) RETURNING id"),
        DELETE("DELETE FROM manufacturer WHERE  name = (?) AND address = (?) AND director = (?) AND id = (?) RETURNING id"),
        UPDATE("UPDATE manufacturer SET name = (?), address = (?), director = (?) WHERE id = (?) RETURNING id");

        final String QUERY;

        SqlManufacturer(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
