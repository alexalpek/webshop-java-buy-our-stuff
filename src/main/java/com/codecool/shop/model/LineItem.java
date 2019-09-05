package com.codecool.shop.model;

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

    public String toString() {
        return String.format("%1$s={" +
                        "quantity: %2$d, " +
                        "product: %3$s}",
                this.getClass().getSimpleName(),
                this.quantity,
                this.product);
    }
}
