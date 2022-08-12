package com.bigmans.stock.db;

import com.bigmans.stock.domain.Contract;
import com.bigmans.stock.domain.Score;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScoreService implements Prototype<Score> {
    /** Соединение с нашей БД */
    @NotNull
    private final Connection connection;

    public ScoreService(final Connection connection) {
        this.connection = connection;
    }

    /**
     * Создание и добавление строки в наш столбец
     * @param contract - новый экземпляр сущности, который мы хотим добавить в нашу базу
     * @return result - флаг успешного добавления строки в нашу таблицу
     */
    @Override
    public List<Integer> create(Contract contract) {
        List<Integer> ids = SqlUtils.execSqlWithReturningId(ContractService.SqlContract.INSERT.QUERY, (statement) -> {
            statement.setDate(1, contract.getDate_contract());
            statement.setString(2, contract.getAbout());
            statement.setInt(3, contract.getProduct().getId());
            statement.setInt(4, contract.getAmount());
            statement.setString(5, contract.getTerms());
            statement.setInt(6, contract.getClient().getId());
            statement.setInt(7, contract.getPrice());
            statement.setBoolean(8, contract.isSale());
        }, connection);
        return ids;
    }

    /**
     * Чтение таблицы из БД
     * @return все клиенты
     */
    @Override
    public List<Contract> read() {
        List<Contract> contracts = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(ContractService.SqlContract.GET.QUERY)) {
            final ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Contract result = new Contract();
                result.setId(Integer.parseInt(rs.getString(1)));
                result.setDate_contract(rs.getDate(2));
                result.setAbout(rs.getString(3));
                result.setProductId(rs.getInt(4));
                result.setAmount(rs.getInt(5));
                result.setTerms(rs.getString(6));
                result.setClientId(rs.getInt(7));
                result.setPrice(rs.getInt(8));
                result.setSale(rs.getBoolean(9));
                contracts.add(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contracts;
    }

    /**
     * Редактирование строки в таблице
     * @param contract - наш экземпляр сущности, который мы хотим отредактировать
     * @return result -  флаг успешного редактирования строки в нашей таблице
     */
    @Override
    public boolean update(Contract contract) {
        List<Integer> ids = SqlUtils.execSqlWithReturningId(ContractService.SqlContract.UPDATE.QUERY, (statement) -> {
            statement.setDate(1, contract.getDate_contract());
            statement.setInt(2, contract.getAmount());
            statement.setString(3, contract.getTerms());
            statement.setInt(4, contract.getPrice());
            statement.setInt(5, contract.getId());
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
        List<Integer> ids = SqlUtils.execSqlWithReturningId(ContractService.SqlContract.DELETE.QUERY, (statement) -> {
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
        INSERT("INSERT INTO contract VALUES (DEFAULT, (?), (?), (?), (?), (?), (?), (?), (?)) RETURNING id"),
        DELETE("DELETE FROM contract WHERE  date_contract = (?) AND  product = (?) AND client = (?) AND id = (?) RETURNING id"),
        UPDATE("UPDATE contract SET date_contract = (?), amount = (?), terms = (?), price = (?) WHERE id = (?) RETURNING id");

        final String QUERY;

        SqlContract(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}