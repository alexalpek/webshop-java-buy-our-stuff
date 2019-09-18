package com.codecool.shop.model;

public class Order {

    private Cart cart;
    private ShippingInfo shippingInfo;

    public Order(Cart cart, ShippingInfo shippingInfo) {
        this.cart = cart;
        this.shippingInfo = shippingInfo;
    }
}
