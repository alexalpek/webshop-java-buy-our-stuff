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
        sb.append(this.getClass().getSimpleName()).append("={");
        for (Field field : this.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value;
            try {
                value = field.get(this);
                if (value != null) {
                    sb.append(field.getName()).append(": ");
                    sb.append(value).append(", ");
                }
            } catch (IllegalAccessException e) {

            }
        }
        sb.setLength(sb.length() - 2);
        sb.append("}");
        return sb.toString();
    }
}
