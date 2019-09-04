package com.codecool.shop.cart;

import com.codecool.shop.model.Product;

import java.util.HashSet;
import java.util.Set;

public class Cart {

    private Set<Product> products = new HashSet<>();

    public void add(Product product) {
        products.add(product);
    }

    public void add(Set<Product> products) {
        this.products.addAll(products);
    }

    public void remove(Product product) {
        products.remove(product);
    }

    public void remove(Set<Product> products) {
        this.products.removeAll(products);
    }

    public float getSumOfProductPrices() {
        return (float) products.stream().mapToDouble(Product::getDefaultPrice).sum();
    }
}
