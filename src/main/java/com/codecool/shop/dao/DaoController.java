package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.jdbc.*;
import com.codecool.shop.dao.implementation.mem.*;

public class DaoController {

    private static final ProductDao PRODUCT_DAO;
    private static final ProductCategoryDao PRODUCT_CATEGORY_DAO;
    private static final SupplierDao SUPPLIER_DAO;
    private static final CartDao CART_DAO;
    private static final LineItemDao LINE_ITEM_DAO;

    static {
        boolean JDBC = true; // TODO: change hardcoded value

        if (JDBC) {
            PRODUCT_DAO = new ProductDaoJDBC();
            PRODUCT_CATEGORY_DAO = new ProductCategoryDaoJDBC();
            SUPPLIER_DAO = new SupplierDaoJDBC();
            CART_DAO = new CartDaoJDBC();
            LINE_ITEM_DAO = new LineItemDaoJDBC();
        } else {
            PRODUCT_DAO = new ProductDaoMem();
            PRODUCT_CATEGORY_DAO = new ProductCategoryDaoMem();
            SUPPLIER_DAO = new SupplierDaoMem();
            CART_DAO = new CartDaoMem();
            LINE_ITEM_DAO = new LineItemDaoMem();
        }

        System.out.println("--- Using " + (JDBC ? "JDBC" : "Mem")
                + " DAO implementations ---");
    }

    public static SupplierDao getSupplierDao() {
        return SUPPLIER_DAO;
    }

    public static ProductCategoryDao getProductCategoryDao() {
        return PRODUCT_CATEGORY_DAO;
    }

    public static ProductDao getProductDao() {
        return PRODUCT_DAO;
    }

    public static CartDao getCartDao() {
        return CART_DAO;
    }

    public static LineItemDao getLineItemDao() {
        return LINE_ITEM_DAO;
    }
}
