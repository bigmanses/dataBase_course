package com.bigmans.stock.db;

import com.bigmans.stock.domain.Client;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientService {

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
    public boolean create(@NotNull final Client client) {
        boolean result = false;

        try (PreparedStatement statement = connection.prepareStatement(SQLClient.INSERT.QUERY)) {
            statement.setString(1,client.getName());
            statement.setString(2, client.getPhone());
            statement.setString(3, client.getAddress());
            statement.setString(4, client.getFax());
            statement.setString(5, client.getScore());
            statement.setString(6, client.getNotes());
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Чтение таблицы из БД
     * @return все клиенты
     */
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
    public boolean update(@NotNull final Client client) {
        boolean result = false;

        try (PreparedStatement statement = connection.prepareStatement(SQLClient.UPDATE.QUERY)) {
            statement.setString(1, client.getName());
            statement.setString(2, client.getPhone());
            statement.setString(3, client.getAddress());
            statement.setInt(4, client.getId());
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Удаление строки из нашей таблицы в БД
     * @param client - наш экземпляр сущности, который мы хотим удалить
     * @return result -  флаг успешного редактирования строки в нашей таблице
     */
    public boolean delete(@NotNull final Client client) {
        boolean result = false;

        try (PreparedStatement statement = connection.prepareStatement(SQLClient.DELETE.QUERY)) {
            statement.setString(1, client.getName());
            statement.setString(2, client.getPhone());
            statement.setString(3, client.getAddress());
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
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
