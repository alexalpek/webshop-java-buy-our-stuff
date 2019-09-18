package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.*;

class ProductCategoryDaoJDBCTest extends DaoJDBCTest {

    @Test
    @DisplayName("Test add() method")
    void testAdd() {
        ProductCategory productCategory = new ProductCategory("test", "test", "test");
        ProductCategoryDao productCategoryDao = new ProductCategoryDaoJDBC();
        productCategoryDao.add(productCategory);
        Assertions.assertNotEquals(0, productCategory.getId());
        Assertions.assertNotNull(productCategory.getDescription());
    }

    @Test
    @DisplayName("Test find() method - valid id")
    void testFind_validId() {
        ProductCategoryDao productCategoryDao = new ProductCategoryDaoJDBC();
        ProductCategory result = productCategoryDao.find(1);
        Assertions.assertEquals(1, result.getId());
    }

    @Test
    @DisplayName("Test find() method - invalid id")
    void testFind_invalidId() {
        ProductCategoryDao productCategoryDao = new ProductCategoryDaoJDBC();
        ProductCategory result = productCategoryDao.find(-1);
        Assertions.assertNull(result);
    }
}