package com.codecool.shop.order;

import com.codecool.shop.model.Product;

import java.util.HashSet;
import java.util.Set;

public class Cart {

    private Set<LineItem> items = new HashSet<>();

    public void add(Product product) {
        if (items.stream().noneMatch(item -> item.getProduct() == product)) {
            items.add(new LineItem(product));
        } else {
            items
                .stream()
                .filter(item -> item.getProduct() == product)
                .forEach(LineItem::increaseQuantity);
        }
    }

    public void remove(Product product) {
        LineItem item = items
                            .stream()
                            .filter(i -> i.getProduct() == product)
                            .findFirst()
                            .orElse(null);
        if (item != null) {
            if (item.getQuantity() > 1) {
                item.decreaseQuantity();
            } else {
                items.remove(item);
            }
        }
    }

    public float getSumOfProductPrices() {
        return (float) items
                .stream()
                .mapToDouble(item -> item.getProduct().getDefaultPrice() * item.getQuantity())
                .sum();
    }
}
