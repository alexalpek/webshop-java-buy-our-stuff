package com.codecool.shop.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

@Getter @Setter
public class Product extends ProductModel {

    private BigDecimal defaultPrice;
    private Currency defaultCurrency;
    private ProductCategory productCategory;
    private Supplier supplier;


    public Product(String name, BigDecimal defaultPrice, String currencyString, String description, ProductCategory productCategory, Supplier supplier) {
        super(name, description);
        this.setPrice(defaultPrice, currencyString);
        this.setSupplier(supplier);
        this.setProductCategory(productCategory);
    }

    public BigDecimal getDefaultPrice(int decimals) {
        return defaultPrice.setScale(decimals, RoundingMode.HALF_DOWN);
    }

    public String getPrice() {
        return String.valueOf(this.getDefaultPrice(2)) + " " + this.defaultCurrency.toString();
    }

    public void setPrice(BigDecimal price, String currency) {
        this.defaultPrice = price;
        this.defaultCurrency = Currency.getInstance(currency);
    }

    @Override
    public String toString() {
        return String.format("%1$s={" +
                        "id: %2$d, " +
                        "name: %3$s, " +
                        "defaultPrice: %4$s, " +
                        "defaultCurrency: %5$s, " +
                        "productCategory: %6$s, " +
                        "supplier: %7$s}",
                this.getClass().getSimpleName(),
                this.id,
                this.name,
                this.getPrice(),
                this.defaultCurrency,
                this.productCategory.getName(),
                this.supplier.getName());
    }
}
