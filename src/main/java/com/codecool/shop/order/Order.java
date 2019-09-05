package com.codecool.shop.order;


import java.util.List;

public class Order {

    private Cart cart;
    private List<String> orderInfo;

    public Order(Cart cart, List<String> orderInfo) {
        this.cart = cart;
        this.orderInfo = orderInfo;
    }
}
