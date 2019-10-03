package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryDaoTest extends DaoTest {

    @Test
    void testAdd() {
        ProductCategory productCategory = new ProductCategory("test", "test", "test");
        ProductCategoryDao productCategoryDao = DaoController.getProductCategoryDao();
        productCategoryDao.add(productCategory);
        Assertions.assertNotEquals(0, productCategory.getId());
    }

    @Test
    void testFind_validId() {
        int id = 1;
        ProductCategoryDao productCategoryDao = DaoController.getProductCategoryDao();
        ProductCategory result = productCategoryDao.find(id);
        Assertions.assertEquals(id, result.getId());
    }

    @Test
    void testFind_invalidId() {
        ProductCategoryDao productCategoryDao = DaoController.getProductCategoryDao();
        Assertions.assertThrows(DataNotFoundException.class, () -> productCategoryDao.find(-1));
    }

    @Test
    void testRemove() {
        int id = 1;
        ProductCategoryDao productCategoryDao = DaoController.getProductCategoryDao();
        ProductCategory productCategory = productCategoryDao.find(id);
        productCategoryDao.remove(id);
        Assertions.assertNotNull(productCategory);
        Assertions.assertThrows(DataNotFoundException.class, () -> productCategoryDao.find(id));
    }

    @Test
    void testGetAll() {
        int productCategoryCount = 2;
        ProductCategoryDao productCategoryDao = DaoController.getProductCategoryDao();
        List<ProductCategory> productCategories = productCategoryDao.getAll();
        Assertions.assertEquals(productCategoryCount, productCategories.size());
    }
}