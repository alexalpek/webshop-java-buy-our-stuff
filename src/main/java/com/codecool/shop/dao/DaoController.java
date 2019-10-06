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
    @Getter private static UserDao userDao;

    static {
        String type = System.getenv("dao");
        USING_JDBC = type == null || !type.equals("mem");

        if (USING_JDBC) {
            productDao = new ProductDaoJDBC();
            productCategoryDao = new ProductCategoryDaoJDBC();
            supplierDao = new SupplierDaoJDBC();
            cartDao = new CartDaoJDBC();
            lineItemDao = new LineItemDaoJDBC();
            userDao = new UserDaoJDBC();
        } else {
            productDao = new ProductDaoMem();
            productCategoryDao = new ProductCategoryDaoMem();
            supplierDao = new SupplierDaoMem();
            cartDao = new CartDaoMem();
            lineItemDao = new LineItemDaoMem();
            userDao = new UserDaoMem();
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
        Cart cart = new Cart("USD");
        cartDao.add(cart);

        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDao.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDao.add(lenovo);
        Supplier dell = new Supplier("Dell", "Computers");
        supplierDao.add(dell);
        Supplier apple = new Supplier("Apple", "Apple Inc. is an American multinational technology company that designs, develops, and sells consumer electronics, computer software, and online services.");
        supplierDao.add(apple);

        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDao.add(tablet);
        ProductCategory laptop = new ProductCategory("Laptop", "Hardware", "A laptop computer, commonly shortened to laptop, or called a notebook computer, is a small, portable personal computer typically having a thin LCD or LED computer screen display.");
        productCategoryDao.add(laptop);
        ProductCategory smartphone = new ProductCategory("Smartphone", "Hardware", "Smartphone is a mobile phone that performs many of the functions of a computer, typically having a touchscreen interface, Internet access, and an operating system capable of running downloaded apps.");
        productCategoryDao.add(smartphone);

        productDao.add(new Product("Amazon Fire", new BigDecimal(49.99), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDao.add(new Product("Lenovo IdeaPad Miix 700", new BigDecimal(479.99), "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDao.add(new Product("Amazon Fire HD 8", new BigDecimal(89.99), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        productDao.add(new Product("Dell ChromeBook 11", new BigDecimal(92.86), "USD", "11-inch ChromeBook laptop built to roll with the punches of family life. Featuring the speed, simplicity and security of Chrome in a portable design.", laptop, dell));
        productDao.add(new Product("iPhone 11", new BigDecimal(699.00), "USD", "iPhone 11 is Apple's latest lower cost smartphone for 2019.", smartphone, apple));

        userDao.add("Barna", "123456");
    }

    private static void clearMemory() {
        productDao = new ProductDaoMem();
        productCategoryDao = new ProductCategoryDaoMem();
        supplierDao = new SupplierDaoMem();
        cartDao = new CartDaoMem();
        lineItemDao = new LineItemDaoMem();
        userDao = new UserDaoMem();
    }

    private static void printImplementationInfo() {
        if (USING_JDBC) {
            System.out.println("--- Using JDBC DAO implementations ---");
        } else {
            System.out.println("--- Using Mem DAO implementations ---");
        }
    }
}
