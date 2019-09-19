package com.codecool.shop.dao;

import com.codecool.shop.util.DatabaseHelper;
import com.codecool.shop.dao.implementation.jdbc.*;
import com.codecool.shop.dao.implementation.mem.*;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import lombok.Getter;

import java.math.BigDecimal;

public class DaoController {

    private static final boolean USING_JDBC;

    @Getter private static ProductDao productDao;
    @Getter private static ProductCategoryDao productCategoryDao;
    @Getter private static SupplierDao supplierDao;
    @Getter private static CartDao cartDao;
    @Getter private static LineItemDao lineItemDao;

    static {
        String type = System.getenv("dao"); // TODO
        USING_JDBC = type == null || !type.equals("mem");

        if (USING_JDBC) {
            productDao = new ProductDaoJDBC();
            productCategoryDao = new ProductCategoryDaoJDBC();
            supplierDao = new SupplierDaoJDBC();
            cartDao = new CartDaoJDBC();
            lineItemDao = new LineItemDaoJDBC();
        } else {
            productDao = new ProductDaoMem();
            productCategoryDao = new ProductCategoryDaoMem();
            supplierDao = new SupplierDaoMem();
            cartDao = new CartDaoMem();
            lineItemDao = new LineItemDaoMem();
            initMemory();
        }

        printImplementationInfo();
    }

    public static void init() {
        clear();
        if (USING_JDBC) {
            DatabaseHelper.initDatabase();
        } else {
            initMemory();
        }
    }

    public static void clear() {
        if (USING_JDBC) {
            DatabaseHelper.clearDatabase();
        } else {
            clearMemory();
        }
    }

    private static void initMemory() {
        Cart cart = new Cart("USD", 1); //TODO
        cartDao.add(cart);

        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDao.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDao.add(lenovo);

        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDao.add(tablet);

        productDao.add(new Product("Amazon Fire", new BigDecimal(49.99), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDao.add(new Product("Lenovo IdeaPad Miix 700", new BigDecimal(479.99), "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDao.add(new Product("Amazon Fire HD 8", new BigDecimal(89.99), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
    }

    private static void clearMemory() {
        productDao = new ProductDaoMem();
        productCategoryDao = new ProductCategoryDaoMem();
        supplierDao = new SupplierDaoMem();
        cartDao = new CartDaoMem();
        lineItemDao = new LineItemDaoMem();
    }

    private static void printImplementationInfo() {
        if (USING_JDBC) {
            System.out.println("--- Using JDBC DAO implementations ---");
        } else {
            System.out.println("--- Using Mem DAO implementations ---");
        }
    }
}
