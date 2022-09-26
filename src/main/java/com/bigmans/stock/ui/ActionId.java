package com.bigmans.stock.ui;

public enum ActionId {
    CLIENT("client"),
    PRODUCT("product"),
    CONTRACT("contract"),
    MANUFACTURE("manufacture"),
    SCORE("score"),
    PRODUCT_BUYING("product_buying"),
    PRODUCT_FOR_ORDER("product_for_order"),
    PRODUCT_FOR_STOCK("product_for_stock"),
    PRODUCT_GIVE_AWAY("product_give_away"),
    SCORE_NOT_PAYMENT("score_not_payment"),
    SCORE_SUM_PAYMENT("score_sum_payment"),
    CLIENT_FOR_PRODUCT("client_for_product");

    private final String id;
    ActionId(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
