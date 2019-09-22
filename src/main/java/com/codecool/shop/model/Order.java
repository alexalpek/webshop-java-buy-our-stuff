package com.codecool.shop.model;

public class Order {

    private Cart cart;
    private ShippingInfo shippingInfo;

    public Order(Cart cart, ShippingInfo shippingInfo) {
        this.cart = cart;
        this.shippingInfo = shippingInfo;
    }

    public String toString() {
        return String.format("%1$s={" +
                        "shippingInfo: %2$s, " +
                        "cart: %3$s}",
                getClass().getSimpleName(),
                shippingInfo,
                cart
        );
    }
}
