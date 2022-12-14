package com.bigmans.stock.db;

import com.bigmans.stock.domain.Contract;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContractService implements Prototype<Contract> {

    /** Соединение с нашей БД */
    @NotNull
    private final Connection connection;

    public ContractService(final Connection connection) {
        this.connection = connection;
    }

    /**
     * Создание и добавление строки в наш столбец
     * @param contract - новый экземпляр сущности, который мы хотим добавить в нашу базу
     * @return result - флаг успешного добавления строки в нашу таблицу
     */
    @Override
    public List<Integer> create(Contract contract) {
        List<Integer> ids = SqlUtils.execSqlWithReturningId(SqlContract.INSERT.QUERY, (statement) -> {
            statement.setDate(1, contract.getDate_contract());
            statement.setString(2, contract.getNumber());
            statement.setString(3, contract.getAbout());
            statement.setInt(4, contract.getProduct().getId());
            statement.setInt(5, contract.getAmount());
            statement.setString(6, contract.getTerms());
            statement.setInt(7, contract.getClient().getId());
            statement.setInt(8, contract.getPrice());
            statement.setBoolean(9, contract.isSale());
        }, connection);
        return ids;
    }

    /** Поиск контрактов по id
     * @param id - id клиента
     * @return найденный контракт
     */
    @Override
    public Contract getId(int id) {
        ResultSet rs = null;
        try (PreparedStatement statement = connection.prepareStatement(SqlContract.GET_ID.QUERY)) {
            statement.setInt(1, id);
            rs = statement.executeQuery();
            rs.next();
            return createOneContract(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (Exception ignore){

            }
        }
        return null;
    }

    @Override
    public List<Contract> getName(String name) {
        ResultSet rs = null;
        try (PreparedStatement statement = connection.prepareStatement(SqlContract.GET_NAME.QUERY)) {
            statement.setString(1, name);
            rs = statement.executeQuery();
            List<Contract> contracts = new ArrayList<>();
            while(rs.next()) {
                contracts.add(createOneContract(rs));
            }
            return contracts;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (Exception ignore){

            }
        }
        return null;
    }

    @Override
    public Connection getConnect() {
        return connection;
    }

    /**
     * Чтение таблицы из БД
     * @return все клиенты
     */
    @Override
    public List<Contract> read() {
        ResultSet rs = null;
        List<Contract> contracts = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SqlContract.GET.QUERY)) {
            rs = statement.executeQuery();
            while(rs.next()){
                contracts.add(createOneContract(rs));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (Exception ignore){

            }
        }
        return contracts;
    }

    /** Создание одного контракта
     * @param rs - результат, полученный из базы
     * @return result - наш созданный контракт
     * */
    private Contract createOneContract(ResultSet rs) throws SQLException {
        Contract result = new Contract();
        result.setId(Integer.parseInt(rs.getString(1)));
        result.setDate_contract(rs.getDate(2));
        result.setNumber(rs.getString(3));
        result.setAbout(rs.getString(4));
        result.setProductId(rs.getInt(5));
        result.setAmount(rs.getInt(6));
        result.setTerms(rs.getString(7));
        result.setClientId(rs.getInt(8));
        result.setPrice(rs.getInt(9));
        result.setSale(rs.getBoolean(10));
        return result;
    }

    /**
     * Редактирование строки в таблице
     * @param contract - наш экземпляр сущности, который мы хотим отредактировать
     * @return result -  флаг успешного редактирования строки в нашей таблице
     */
    @Override
    public boolean update(Contract contract) {
        List<Integer> ids = SqlUtils.execSqlWithReturningId(SqlContract.UPDATE.QUERY, (statement) -> {
            statement.setDate(1, contract.getDate_contract());
            statement.setString(2, contract.getNumber());
            statement.setString(3, contract.getAbout());
            statement.setInt(4, contract.getProduct().getId());
            statement.setInt(5, contract.getAmount());
            statement.setString(6, contract.getTerms());
            statement.setInt(7, contract.getClient().getId());
            statement.setInt(8, contract.getPrice());
            statement.setBoolean(9, contract.isSale());
            statement.setInt(10, contract.getId());
        }, connection);
        return ids.size() > 0;
    }

    /**
     * Удаление строки из нашей таблицы в БД
     * @param contract - наш экземпляр сущности, который мы хотим удалить
     * @return result -  флаг успешного редактирования строки в нашей таблице
     */
    @Override
    public boolean delete(Contract contract) {
        List<Integer> ids = SqlUtils.execSqlWithReturningId(SqlContract.DELETE.QUERY, (statement) -> {
            statement.setDate(1, contract.getDate_contract());
            statement.setInt(2, contract.getProduct().getId());
            statement.setInt(3, contract.getClient().getId());
            statement.setInt(4, contract.getId());
        }, connection);
        return ids.size() > 0;
    }

    /**
     * Перечесление необходимых для работы с клиентом запросов
     */
    public enum SqlContract {
        GET("SELECT* from contract"),
        GET_ID("SELECT* from contract WHERE contract.id = (?)" ),
        GET_NAME("select contract.*  from contract JOIN client ON contract.client = client.id where client.name = (?)" ),
        INSERT("INSERT INTO contract VALUES (DEFAULT, (?), (?), (?), (?), (?), (?), (?), (?), (?)) RETURNING id"),
        DELETE("DELETE FROM contract WHERE  date_contract = (?) AND  product = (?) AND client = (?) AND id = (?) RETURNING id"),
        UPDATE("UPDATE contract SET date_contract = (?), number = (?), about = (?), product = (?), amount = (?), terms = (?), client = (?), price = (?), isSale = (?) WHERE id = (?) RETURNING id");

        final String QUERY;

        SqlContract(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
