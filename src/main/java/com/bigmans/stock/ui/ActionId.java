package com.bigmans.stock.ui;

public enum ActionId {
    CLIENT("client"),
    PRODUCT("product"),
    CONTRACT("contract"),
    MANUFACTURE("manufacture"),
    SCORE("score");

    private final String id;
    ActionId(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
