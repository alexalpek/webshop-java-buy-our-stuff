package com.codecool.shop.model;

import java.util.Currency;
import java.util.Set;
import java.util.HashSet;
import java.util.Optional;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Cart extends BaseModel {

    private Set<LineItem> items = new HashSet<>();
    private final Currency currency;

    public Cart(String currencyString) {
        this.currency = Currency.getInstance(currencyString);
    }

    public Currency getCurrency() {
        return currency;
    }

    public Set<LineItem> getItems() {
        return items;
    }

    public void add(Product product) {
        if (product.getDefaultCurrency() == currency) {
            Optional<LineItem> match;
            match = items.stream().filter(item -> item.getProduct() == product).findFirst();
            if (match.isPresent()) {
                match.get().increaseQuantity();
            } else {
                items.add(new LineItem(product));
            }
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

    public BigDecimal getTotalPrice() {
        return items.stream().map(
                item -> item.getProduct().getDefaultPrice().multiply(new BigDecimal(item.getQuantity()))
        ).reduce(BigDecimal::add).orElse(new BigDecimal(0));
    }

    public BigDecimal getTotalPrice(int decimals) {
        return getTotalPrice().setScale(decimals, RoundingMode.HALF_DOWN);
    }

    public int size() {
        return items.stream().mapToInt(LineItem::getQuantity).sum();
    }

    public String toString() {
        return String.format("%1$s={" +
                        "id: %2$d, " +
                        "currency: %3$s, " +
                        "items: %4$s}",
                this.getClass().getSimpleName(),
                this.id,
                this.currency,
                this.items
        );
    }
}
