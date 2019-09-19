package com.codecool.shop.dao;

import com.codecool.shop.model.Cart;
import com.codecool.shop.model.LineItem;

import java.util.List;

public interface LineItemDao {

    void add(LineItem lineItem);

    void remove(LineItem lineItem);

    void update(LineItem lineItem, int quantity);

    List<LineItem> getBy(Cart cart);
}
