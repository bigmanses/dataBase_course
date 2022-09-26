package com.bigmans.stock.ui;

public enum Act {
    ADD("Добавить"),
    DELETE("Удалить"),
    EDIT("Редактировать");

    private final String id;
    Act(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
