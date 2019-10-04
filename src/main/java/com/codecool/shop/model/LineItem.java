package com.codecool.shop.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class LineItem extends BaseModel {

    @Setter
    private int quantity;

    private Product product;
    private int cartId;

    public LineItem(Product product, int cartId, int quantity) {
        this.product = product;
        this.cartId = cartId;
        this.quantity = quantity;
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
