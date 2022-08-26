package com.bigmans.stock.db;

import com.bigmans.stock.domain.Manufacturer;
import com.bigmans.stock.domain.Product;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements Prototype<Product> {
    /** Соединение с нашей БД */
    @NotNull
    private final Connection connection;

    public ProductService(final Connection connection) {
        this.connection = connection;
    }

    /** Поиск продукта по id
     * @param id - id продукта
     * @return найденный продукт
     */
    @Override
    public Product getId(int id) {
        try (PreparedStatement statement = connection.prepareStatement(SqlProduct.GET_ID.QUERY)) {
            statement.setInt(1, id);
            final ResultSet rs = statement.executeQuery();
            rs.next();
            return createOneProduct(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Создание и добавление строки в наш столбец
     * @param product - новый экземпляр сущности, который мы хотим добавить в нашу базу
     * @return result - флаг успешного добавления строки в нашу таблицу
     */
    @Override
    public List<Integer> create(@NotNull final Product product) {
        List<Integer> ids = SqlUtils.execSqlWithReturningId(SqlProduct.INSERT.QUERY, (statement) -> {
            statement.setString(1, product.getName());
            statement.setString(2, product.getCharacteristic());
            statement.setInt(3, product.getPriceOne());
            statement.setString(4, product.getPackages());
            statement.setString(5, product.getBatchDelivery());
            statement.setInt(6, product.getAmount());
            statement.setInt(7, product.getManufacturer().getId());
        }, connection);
        return ids;
    }

    /**
     * Чтение таблицы из БД
     * @return все клиенты
     */
    @Override
    public List<Product> read() {
        List<Product> products = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SqlProduct.GET.QUERY)) {
            final ResultSet rs = statement.executeQuery();
            while(rs.next()){
                products.add(createOneProduct(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    /** Создание одного продукта
     * @param rs - результат, полученный из базы
     * @return result - наш созданный продукт
     * */
    private Product createOneProduct(ResultSet rs) throws SQLException {
        Product result = new Product();
        result.setId(Integer.parseInt(rs.getString(1)));
        result.setName(rs.getString(2));
        result.setCharacteristic(rs.getString(3));
        result.setPriceOne(rs.getInt(4));
        result.setPackages(rs.getString(5));
        result.setBatchDelivery(rs.getString(6));
        result.setAmount(rs.getInt(7));
        result.setManufacturerId(rs.getInt(8));
        return result;
    }

    /**
     * Редактирование строки в таблице
     * @param product - наш экземпляр сущности, который мы хотим отредактировать
     * @return result -  флаг успешного редактирования строки в нашей таблице
     */
    @Override
    public boolean update(@NotNull final Product product) {

        List<Integer> ids = SqlUtils.execSqlWithReturningId(SqlProduct.UPDATE.QUERY, (statement) -> {
            statement.setString(1, product.getName());
            statement.setString(2, product.getCharacteristic());
            statement.setInt(3, product.getPriceOne());
            statement.setInt(4, product.getAmount());
            statement.setInt(5, product.getId());
        }, connection);
        return ids.size() > 0;
    }

    /**
     * Удаление строки из нашей таблицы в БД
     * @param product - наш экземпляр сущности, который мы хотим удалить
     * @return result -  флаг успешного редактирования строки в нашей таблице
     */
    @Override
    public boolean delete(@NotNull final Product product) {
        List<Integer> ids = SqlUtils.execSqlWithReturningId(SqlProduct.DELETE.QUERY, (statement) -> {
            statement.setString(1, product.getName());
            statement.setInt(2, product.getManufacturer().getId());
            statement.setInt(3, product.getPriceOne());
            statement.setInt(4, product.getId());
        }, connection);
        return ids.size() > 0;
    }

    /**
     * Перечесление необходимых для работы с клиентом запросов
     */
    public enum SqlProduct {
        GET("SELECT* from product"),
        GET_ID("SELECT* from product WHERE product.id = (?)" ),
        INSERT("INSERT INTO product VALUES (DEFAULT, (?), (?), (?), (?), (?), (?), (?)) RETURNING id"),
        DELETE("DELETE FROM product WHERE  name = (?) AND  manufacturer = (?) AND priceOne = (?) AND id = (?) RETURNING id"),
        UPDATE("UPDATE product SET name = (?), characteristic = (?), priceOne = (?), amount = (?) WHERE id = (?) RETURNING id");

        final String QUERY;

        SqlProduct(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
