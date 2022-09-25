package com.bigmans.stock.ui;

public enum Act {
    ADD("add"),
    DELETE("delete");

    private final String id;
    Act(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
