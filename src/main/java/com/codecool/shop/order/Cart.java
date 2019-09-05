package com.codecool.shop.order;

import com.codecool.shop.model.Product;

import java.util.Set;
import java.util.HashSet;
import java.util.Optional;

public class Cart {

    private Set<LineItem> items = new HashSet<>();

    public void add(Product product) {
        Optional<LineItem> match;
        match = items.stream().filter(item -> item.getProduct() == product).findFirst();
        if (match.isPresent()) {
            match.get().increaseQuantity();
        } else {
            items.add(new LineItem(product));
        }
    }

    public void remove(Product product) {
        Optional<LineItem> match;
        match = items.stream().filter(item -> item.getProduct() == product).findFirst();
        if (match.isPresent()) {
            LineItem item = match.get();
            if (item.getQuantity() > 1) {
                item.decreaseQuantity();
            } else {
                items.remove(item);
            }
        }
    }

    public float getSumOfProductPrices() {
        return (float) items.stream().mapToDouble(
                item -> item.getProduct().getDefaultPrice() * item.getQuantity()
        ).sum();
    }

    public String getSizeOfCart(){
        int sum = 0;
        for (LineItem item : items){
            sum += item.getQuantity();
        }
        return Integer.toString(sum);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "items=" + items +
                '}';
    }
}
