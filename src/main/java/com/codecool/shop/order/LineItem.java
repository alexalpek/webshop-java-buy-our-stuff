package com.codecool.shop.order;

import com.codecool.shop.model.Product;

public class LineItem {

    private Product product;
    private int quantity;

    public LineItem(Product product) {
        this.product = product;
        this.quantity = 1;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity() {
        quantity++;
    }

    public void decreaseQuantity() {
        quantity--;
    }

    public Product getProduct() {
        return product;
    }
}
