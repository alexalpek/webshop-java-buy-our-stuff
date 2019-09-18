package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.DaoController;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class ProductDaoJDBCTest extends DaoJDBCTest {

    @Test
    void add() {
        ProductCategoryDao productCategoryDataStore = DaoController.getProductCategoryDao();
        SupplierDao supplierDataStore = DaoController.getSupplierDao();

        Supplier supplier =  supplierDataStore.find(1);
        ProductCategory productCategory =  productCategoryDataStore.find(1);

        Product product = new Product("test", new BigDecimal(499.99), "USD",
                "test", productCategory, supplier);
        ProductDao productDaoDao = new ProductDaoJDBC();
        productDaoDao.add(product);
        Assertions.assertNotEquals(0,product.getId());
        Assertions.assertNotEquals(0,product.getProductCategory().getId());
        Assertions.assertNotEquals(0,product.getId());
    }

    @Test
    void testFind(){
        ProductDao productDao = new ProductDaoJDBC();
        Product result = productDao.find(1);
        Assertions.assertEquals(1, result.getId());
    }
}