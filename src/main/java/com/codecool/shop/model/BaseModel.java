package com.codecool.shop.model;


import java.lang.reflect.Field;

public abstract class BaseModel {

    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (Field field : this.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(this);
                if (value != null) {
                    sb.append(field.getName() + ":" + value + ", ");
                }
            } catch (IllegalAccessException e) {

            }
        }
        return sb.toString();
    }

}
