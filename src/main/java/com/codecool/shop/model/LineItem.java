package com.codecool.shop.model;

public class LineItem {

    private Product product;
    private int quantity;
    private int cartId;

    public LineItem(Product product, int quantity, Cart cart) {
        this.product = product;
        this.quantity = quantity;
        this.cartId = cart.getId();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getCartId() {
        return cartId;
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
