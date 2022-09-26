package com.bigmans.stock.db;

import com.bigmans.stock.domain.Client;
import com.bigmans.stock.domain.Contract;
import com.bigmans.stock.domain.Product;
import com.bigmans.stock.domain.Score;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequestService {
    /** Соединение с нашей БД */
    @NotNull
    private final Connection connection;
    @Autowired
    private ContractService contractService;

    public RequestService(final Connection connection) {
        this.connection = connection;
    }

    /**
     * Чтение таблицы из БД -  номенклатура предлагаемой на продажу продукции
     * @return все продукты
     */
    public List<Product> getProductBuying() {
        ResultSet rs = null;
        List<Product> products = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQLRequest.GET_PRODUCT_FOR_BUYING.QUERY)) {
            rs = statement.executeQuery();
            while(rs.next()){
                Product result = new Product();
                result.setId(Integer.parseInt(rs.getString(1)));
                result.setName(rs.getString(2));
                result.setCharacteristic(rs.getString(3));
                result.setPriceOne(rs.getInt(4));
                result.setPackages(rs.getString(5));
                result.setBatchDelivery(rs.getString(6));
                result.setAmount(rs.getInt(7));
                result.setManufacturerId(rs.getInt(8));
                products.add(result);
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
        return products;
    }

    /**
     * Чтение таблицы из БД -  номенклатура  о неоплаченных счетах
     * @return все неоплаченные счета
     */
    public List<Score> getScoreNotPayment(){
        List<Score> scores = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement statement = connection.prepareStatement(SQLRequest.GET_SCORE_NOT_PAYMENT.QUERY)) {
            rs = statement.executeQuery();
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
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (Exception ignore){

            }
        }
        return scores;
    }

    /**
     * Чтение таблицы из БД -  номенклатура об оплате счетов и суммах оплаты
     * @return все счета
     */
    public List<Score> getScoreSumPayment(){
        ResultSet rs = null;
        List<Score> scores = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQLRequest.GET_SCORE_SUM_PAYMENT.QUERY)) {
            rs = statement.executeQuery();
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
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (Exception ignore){

            }
        }
        return scores;
    }

    /**
     * Чтение таблицы из БД -  список заказываемых товаров
     * @return все клиенты
     */
    public List<Product> getProductForOrder(){
        ResultSet rs = null;
        List<Product> products = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQLRequest.GET_PRODUCT_FOR_ORDER.QUERY)) {
            rs = statement.executeQuery();
            while(rs.next()){
                Product result = new Product();
                result.setId(Integer.parseInt(rs.getString(1)));
                result.setName(rs.getString(2));
                result.setCharacteristic(rs.getString(3));
                result.setPriceOne(rs.getInt(4));
                result.setPackages(rs.getString(5));
                result.setBatchDelivery(rs.getString(6));
                result.setAmount(rs.getInt(7));
                result.setManufacturerId(rs.getInt(8));
                result.getManufacturer().setName(rs.getString(9));
                products.add(result);
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
        return products;
    }

    /**
     * Чтение таблицы из БД -  список товаров на складе
     * @return все клиенты
     */
    public List<Product> getProductForStock(){
        List<Product> products = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement statement = connection.prepareStatement(SQLRequest.GET_PRODUCT_FOR_STOCK.QUERY)) {
            rs = statement.executeQuery();
            while(rs.next()){
                Product result = new Product();
                result.setId(Integer.parseInt(rs.getString(1)));
                result.setName(rs.getString(2));
                result.setCharacteristic(rs.getString(3));
                result.setPriceOne(rs.getInt(4));
                result.setPackages(rs.getString(5));
                result.setBatchDelivery(rs.getString(6));
                result.setAmount(rs.getInt(7));
                result.setManufacturerId(rs.getInt(8));
                result.getManufacturer().setName(rs.getString(9));
                products.add(result);
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
        return products;
    }

    /**
     * Чтение таблицы из БД -  номенклатура о поставленных и проданных товарах
     * @return все клиенты
     */
    public List<Product> getProductGiveAway(){
        List<Product> products = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement statement = connection.prepareStatement(SQLRequest.GET_PRODUCT_GIVE_AWAY.QUERY)) {
            rs = statement.executeQuery();
            while(rs.next()){
                Product result = new Product();
                result.setId(Integer.parseInt(rs.getString(1)));
                result.setName(rs.getString(2));
                result.setCharacteristic(rs.getString(3));
                result.setPriceOne(rs.getInt(4));
                result.setPackages(rs.getString(5));
                result.setBatchDelivery(rs.getString(6));
                result.setAmount(rs.getInt(7));
                result.setManufacturerId(rs.getInt(8));
                result.getManufacturer().setName(rs.getString(9));
                products.add(result);
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
        return products;
    }

    /**
     * Чтение таблицы из БД -  список клиентов по типу запрашиваемых товаров;
     * @return все клиенты
     */
    public List<Client> getClientForProduct(String nameProduct){
        List<Client> clients = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement statement = connection.prepareStatement(SQLRequest.GET_CLIENT_FOR_PRODUCT.QUERY)) {
            statement.setString(1, nameProduct);
            rs = statement.executeQuery();
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
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (Exception ignore){

            }
        }

        return clients;
    }

    /**
     * Чтение таблицы из БД -  номенклатура о заключенных договорах на поставку и продажу товара
     * @return все контракты
     */
    public List<Contract> getContractInfo(){
        return new ContractService(connection).read();
    }

    /**
     * Перечесление необходимых для работы с клиентом запросов
     */
    public enum SQLRequest {
        GET_PRODUCT_FOR_BUYING("SELECT* from product_for_buying"),
        GET_SCORE_NOT_PAYMENT("SELECT* from score_not_payment"),
        GET_SCORE_SUM_PAYMENT("SELECT* from score_sum_payment"),
        GET_PRODUCT_FOR_ORDER("SELECT* from product_for_order"),
        GET_PRODUCT_FOR_STOCK("SELECT* from product_for_stock"),
        GET_PRODUCT_GIVE_AWAY("SELECT* from product_give_away"),
        GET_CLIENT_FOR_PRODUCT("SELECT DISTINCT client.* FROM contract JOIN client ON contract.client = client.id\n" +
                "       JOIN product ON contract.product = product.id  JOIN manufacturer ON product.manufacturer = manufacturer.id\n" +
                "       WHERE product.name = (?) ");

        final String QUERY;

        SQLRequest(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
