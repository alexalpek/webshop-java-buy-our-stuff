package com.codecool.shop.model;

import com.codecool.shop.dao.DaoController;
import com.codecool.shop.dao.LineItemDao;

import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Cart extends BaseModel {

    private final int accountId;
    private final Currency currency;

    public Cart(String currencyString, int accountId) {
        this.currency = Currency.getInstance(currencyString);
        this.accountId = accountId;
    }

    public Currency getCurrency() {
        return currency;
    }

    public List<LineItem> getItems() {
        return items();
    }

    public void add(Product product) {
        LineItemDao lineItemDao = DaoController.getLineItemDao();
        if (product.getDefaultCurrency() == currency) {
            Optional<LineItem> match;
            match = getItem(product);
            if (match.isPresent()) {
                LineItem item = match.get();
                lineItemDao.update(item, item.getQuantity() + 1);
            } else {
                lineItemDao.add(new LineItem(product, 1, this));
            }
        }
    }

    public void remove(Product product) {
        LineItemDao lineItemDao = DaoController.getLineItemDao();
        Optional<LineItem> match;
        match = getItem(product);
        if (match.isPresent()) {
            LineItem item = match.get();
            if (item.getQuantity() > 1) {
                lineItemDao.update(item, item.getQuantity() - 1);
            } else {
                lineItemDao.remove(item);
            }
        }
    }

    public BigDecimal getTotalPrice() {
        return items().stream().map(
                item -> item.getProduct().getDefaultPrice().multiply(new BigDecimal(item.getQuantity()))
        ).reduce(BigDecimal::add).orElse(new BigDecimal(0));
    }

    public BigDecimal getTotalPrice(int decimals) {
        return getTotalPrice().setScale(decimals, RoundingMode.HALF_DOWN);
    }

    public int size() {
        return items().stream().mapToInt(LineItem::getQuantity).sum();
    }

    public String toString() {
        return String.format(
                "items: %1$s, " +
                        "currency: %2$s",
                this.items(),
                this.currency);
    }

    private Optional<LineItem> getItem(Product product) {
        List<LineItem> items = DaoController.getLineItemDao().getBy(this);
        return items
                .stream()
                .filter(item -> item.getProduct().getId() == product.getId())
                .findFirst();
    }

    private List<LineItem> items() {
        return DaoController.getLineItemDao().getBy(this);
    }
}
