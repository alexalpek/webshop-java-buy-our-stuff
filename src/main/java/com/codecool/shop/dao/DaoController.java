package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.jdbc.*;

public class DaoController {

    private static final SupplierDao SUPPLIER_DAO;
    private static final ProductCategoryDao PRODUCT_CATEGORY_DAO;
    private static final ProductDao PRODUCT_DAO;
    private static final CartDao CART_DAO;
    private static final LineItemDao LINE_ITEM_DAO;

    static {
        SUPPLIER_DAO = new SupplierDaoJDBC();
        PRODUCT_CATEGORY_DAO = new ProductCategoryDaoJDBC();
        PRODUCT_DAO = new ProductDaoJDBC();
        CART_DAO = new CartDaoJDBC();
        LINE_ITEM_DAO = new LineItemDaoJDBC();
    }

    public static SupplierDao getSupplierDao(){
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
