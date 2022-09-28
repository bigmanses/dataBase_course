package com.bigmans.stock.db;

import com.bigmans.stock.domain.Client;
import com.bigmans.stock.domain.User;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService implements Prototype<User> {
    /**
     * Соединение с нашей БД
     */
    @NotNull
    private final Connection connection;

    public UserService(final Connection connection) {
        this.connection = connection;
    }

    /**
     * Создание и добавление строки в наш столбец
     *
     * @param user - новый экземпляр сущности, который мы хотим добавить в нашу базу
     * @return result - флаг успешного добавления строки в нашу таблицу
     */
    @Override
    public List<Integer> create(@NotNull final User user) {
      return null;
    }


    /**
     * Поиск клиентов по id
     *
     * @param id - id клиента
     * @return найденный клиент
     */
    @Override
    public User getId(int id) {
        return null;
    }

    @Override
    public List<User> getName(String name) {
        return null;
    }

    @Override
    public Connection getConnect() {
        return connection;
    }

    /**
     * Чтение таблицы из БД
     *
     * @return все клиенты
     */
    @Override
    public List<User> read() {
        ResultSet rs = null;
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQLUsers.GET.QUERY)) {
            rs = statement.executeQuery();
            while (rs.next()) {
                users.add(createOneUser(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (Exception e) {

            }
        }
        return users;
    }

    /**
     * Создание одного клиента
     *
     * @param rs - результат, полученный из базы
     * @return result - наш созданный клиент
     */
    private User createOneUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(Integer.parseInt(rs.getString(1)));
        user.setLogin(rs.getString(2));
        user.setPassword(rs.getString(3));
        user.setName(rs.getString(4));
        return user;
    }

    /**
     * Редактирование строки в таблице
     *
     * @param client - наш экземпляр сущности, который мы хотим отредактировать
     * @return result -  флаг успешного редактирования строки в нашей таблице
     */
    @Override
    public boolean update(@NotNull final User client) {
        return false;
    }

    /**
     * Удаление строки из нашей таблицы в БД
     *
     * @param client - наш экземпляр сущности, который мы хотим удалить
     * @return result -  флаг успешного редактирования строки в нашей таблице
     */
    @Override
    public boolean delete(@NotNull final User client) {
        return false;
    }

    /**
     * Перечесление необходимых для работы с клиентом запросов
     */
    public enum SQLUsers {
        GET("SELECT* from users");

        final String QUERY;

        SQLUsers(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
