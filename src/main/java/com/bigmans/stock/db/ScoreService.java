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
     * @param score - новый экземпляр сущности, который мы хотим добавить в нашу базу
     * @return result - флаг успешного добавления строки в нашу таблицу
     */
    @Override
    public List<Integer> create(Score score) {
        List<Integer> ids = SqlUtils.execSqlWithReturningId(SqlScore.INSERT.QUERY, (statement) -> {
            statement.setString(1, score.getName());
            statement.setString(2, score.getNumber());
            statement.setInt(3, score.getContract().getId());
            statement.setDate(4, score.getDate_score());
            statement.setInt(5, score.getSum());
            statement.setBoolean(6, score.isShipment_status());
            statement.setBoolean(7, score.isPayment_status());
        }, connection);
        return ids;
    }

    /**
     * Чтение таблицы из БД
     * @return все клиенты
     */
    @Override
    public List<Score> read() {
        List<Score> scores = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SqlScore.GET.QUERY)) {
            final ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Score result = new Score();
                result.setId(Integer.parseInt(rs.getString(1)));
                result.setName(rs.getString(2));
                result.setNumber(rs.getString(3));
                result.setContractId(rs.getInt(4));
                result.setDate_score(rs.getDate(5));
                result.setSum(rs.getInt(6));
                result.setShipment_status(rs.getBoolean(7));
                result.setPayment_status(rs.getBoolean(8));
                scores.add(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scores;
    }

    /**
     * Редактирование строки в таблице
     * @param score - наш экземпляр сущности, который мы хотим отредактировать
     * @return result -  флаг успешного редактирования строки в нашей таблице
     */
    @Override
    public boolean update(Score score) {
        List<Integer> ids = SqlUtils.execSqlWithReturningId(SqlScore.UPDATE.QUERY, (statement) -> {
            statement.setInt(1, score.getSum());
            statement.setBoolean(2, score.isShipment_status());
            statement.setBoolean(3, score.isPayment_status());
            statement.setInt(4, score.getId());
        }, connection);
        return ids.size() > 0;
    }

    /**
     * Удаление строки из нашей таблицы в БД
     * @param score - наш экземпляр сущности, который мы хотим удалить
     * @return result -  флаг успешного редактирования строки в нашей таблице
     */
    @Override
    public boolean delete(Score score) {
        List<Integer> ids = SqlUtils.execSqlWithReturningId(SqlScore.DELETE.QUERY, (statement) -> {
            statement.setString(1, score.getName());
            statement.setString(2, score.getNumber());
            statement.setInt(3, score.getContract().getId());
            statement.setInt(4, score.getId());
        }, connection);
        return ids.size() > 0;
    }

    /**
     * Перечесление необходимых для работы с клиентом запросов
     */
    public enum SqlScore {
        GET("SELECT* from score"),
        INSERT("INSERT INTO score VALUES (DEFAULT, (?), (?), (?), (?), (?), (?), (?)) RETURNING id"),
        DELETE("DELETE FROM score WHERE  name = (?) AND number = (?) AND contract = (?) AND id = (?) RETURNING id"),
        UPDATE("UPDATE score SET sum = (?), shipment_status = (?), payment_status = (?) WHERE id = (?) RETURNING id");

        final String QUERY;

        SqlScore(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
