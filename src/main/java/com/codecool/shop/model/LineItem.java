package com.codecool.shop.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class LineItem {

    @Setter
    private int quantity;

    private Product product;
    private int cartId;

    public LineItem(Product product, int quantity, Cart cart) {
        this.product = product;
        this.quantity = quantity;
        this.cartId = cart.getId();
    }

    public String toString() {
        return String.format("%1$s={" +
                        "quantity: %2$d, " +
                        "product: %3$s}",
                getClass().getSimpleName(),
                quantity,
                product
        );
    }
}
