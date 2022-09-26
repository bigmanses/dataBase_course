package com.bigmans.stock.ui;

public enum VersionType {
    SALE("Покупка"),
    SUPPLY("Поставка"),
    SHIPPED("Отгружено"),
    NO_SHIPPED("Не отгружено"),
    PAYMENT("Оплачено"),
    NO_PAYMENT("Не оплачено");

    String id;

    VersionType(String id) {
        this.id = id;
    }
    public String getId(){
        return id;
    }
}
