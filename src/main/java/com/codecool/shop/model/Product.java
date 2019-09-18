package com.codecool.shop.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

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

    public BigDecimal getDefaultPrice() {
        return defaultPrice;
    }

    public BigDecimal getDefaultPrice(int decimals) {
        return defaultPrice.setScale(decimals, RoundingMode.HALF_DOWN);
    }

    public void setDefaultPrice(BigDecimal defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public Currency getDefaultCurrency() {
        return defaultCurrency;
    }

    public void setDefaultCurrency(Currency defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

    public String getPrice() {
        return String.valueOf(this.getDefaultPrice(2)) + " " + this.defaultCurrency.toString();
    }

    public void setPrice(BigDecimal price, String currency) {
        this.defaultPrice = price;
        this.defaultCurrency = Currency.getInstance(currency);
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
        this.productCategory.addProduct(this);
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
        this.supplier.addProduct(this);
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
