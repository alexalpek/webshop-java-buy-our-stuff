package com.codecool.shop.model;


import com.codecool.shop.model.ShippingInfo;

import java.util.List;

public class Order {

    private Cart cart;
    private ShippingInfo shippingInfo;

    public Order(Cart cart, ShippingInfo shippingInfo) {
        this.cart = cart;
        this.shippingInfo = shippingInfo;
    }
}
