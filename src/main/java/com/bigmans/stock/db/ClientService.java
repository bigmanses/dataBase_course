package com.bigmans.stock.db;

import com.bigmans.stock.domain.Client;
import com.bigmans.stock.domain.Manufacturer;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientService implements Prototype<Client>{

    /** Соединение с нашей БД */
    @NotNull
    private final Connection connection;

    public ClientService(final Connection connection) {
        this.connection = connection;
    }

    /**
     * Создание и добавление строки в наш столбец
     * @param client - новый экземпляр сущности, который мы хотим добавить в нашу базу
     * @return result - флаг успешного добавления строки в нашу таблицу
     */
    @Override
    public  List<Integer> create(@NotNull final Client client) {
        List<Integer> ids = SqlUtils.execSqlWithReturningId(SQLClient.INSERT.QUERY, (statement) -> {
            statement.setString(1,client.getName());
            statement.setString(2, client.getPhone());
            statement.setString(3, client.getAddress());
            statement.setString(4, client.getFax());
            statement.setString(5, client.getScore());
            statement.setString(6, client.getNotes());
        }, connection);
        return ids;
    }

    /**
     * Чтение таблицы из БД
     * @return все клиенты
     */
    @Override
    public List<Client> read() {
        List<Client> clients = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQLClient.GET.QUERY)) {
            final ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Client result = new Client();
                result.setId(Integer.parseInt(rs.getString(1)));
                result.setName(rs.getString(2));
                result.setPhone(rs.getString(3));
                result.setAddress(rs.getString(4));
                result.setFax(rs.getString(5));
                result.setScore(rs.getString(6));
                result.setNotes(rs.getString(7));
                clients.add(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    /**
     * Редактирование строки в таблице
     * @param client - наш экземпляр сущности, который мы хотим отредактировать
     * @return result -  флаг успешного редактирования строки в нашей таблице
     */
    @Override
    public boolean update(@NotNull final Client client) {

        List<Integer> ids = SqlUtils.execSqlWithReturningId(SQLClient.UPDATE.QUERY, (statement) -> {
            statement.setString(1, client.getName());
            statement.setString(2, client.getPhone());
            statement.setString(3, client.getAddress());
            statement.setInt(4, client.getId());
        }, connection);
        return ids.size() > 0;
    }

    /**
     * Удаление строки из нашей таблицы в БД
     * @param client - наш экземпляр сущности, который мы хотим удалить
     * @return result -  флаг успешного редактирования строки в нашей таблице
     */
    @Override
    public boolean delete(@NotNull final Client client) {
        List<Integer> ids = SqlUtils.execSqlWithReturningId(SQLClient.DELETE.QUERY, (statement) -> {
            statement.setString(1, client.getName());
            statement.setString(2, client.getPhone());
            statement.setString(3, client.getAddress());
        }, connection);
        return ids.size() > 0;
    }

    /**
     * Перечесление необходимых для работы с клиентом запросов
     */
    public enum SQLClient {
        GET("SELECT* from client"),
        INSERT("INSERT INTO client VALUES (DEFAULT, (?), (?), (?), (?), (?), (?)) RETURNING id"),
        DELETE("DELETE FROM client WHERE  name = (?) AND phone = (?) AND address = (?) RETURNING id"),
        UPDATE("UPDATE client SET name = (?), phone = (?), address = (?) WHERE id = (?) RETURNING id");

        final String QUERY;

        SQLClient(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
