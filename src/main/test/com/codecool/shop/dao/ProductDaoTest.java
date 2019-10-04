package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

class ProductDaoTest extends DaoTest {

    @Test
    void testAdd() {
        ProductCategoryDao productCategoryDataStore = DaoController.getProductCategoryDao();
        SupplierDao supplierDataStore = DaoController.getSupplierDao();

        Supplier supplier = supplierDataStore.find(1);
        ProductCategory productCategory = productCategoryDataStore.find(1);

        Product product = new Product("test", new BigDecimal(499.99), "USD",
                "test", productCategory, supplier);
        ProductDao productDao = DaoController.getProductDao();
        productDao.add(product);
        Assertions.assertNotEquals(0, product.getId());
        Assertions.assertNotEquals(0, product.getProductCategory().getId());
        Assertions.assertNotEquals(0, product.getId());
    }

    @Test
    void testFind_validId() {
        ProductDao productDao = DaoController.getProductDao();
        Product result = productDao.find(1);
        Assertions.assertEquals(1, result.getId());
    }

    @Test
    void testFind_invalidId() {
        ProductDao productDao = DaoController.getProductDao();
        Assertions.assertThrows(DataNotFoundException.class, () -> productDao.find(-1));
    }

    @Test
    void testRemove() {
        int id = 1;
        ProductDao productDao = DaoController.getProductDao();
        Product product = productDao.find(id);
        productDao.remove(id);
        Assertions.assertNotNull(product);
        Assertions.assertThrows(DataNotFoundException.class, () -> productDao.find(id));
    }

    @Test
    void testGetAll() {
        int productCount = 5;
        ProductDao productDao = DaoController.getProductDao();
        List<Product> products = productDao.getAll();
        Assertions.assertEquals(productCount, products.size());
    }

    @Test
    void testGetBy_supplier() {
        int productsByAmazon = 2;
        int amazonId = 1;
        ProductDao productDao = DaoController.getProductDao();
        SupplierDao supplierDao = DaoController.getSupplierDao();
        Supplier amazon = supplierDao.find(amazonId);
        List<Product> products = productDao.getBy(amazon);
        Assertions.assertEquals(productsByAmazon, products.size());
    }

    @Test
    void testGetBy_productCategory() {
        int tabletProducts = 3;
        int tabletId = 1;
        ProductDao productDao = DaoController.getProductDao();
        ProductCategoryDao productCategoryDao = DaoController.getProductCategoryDao();
        ProductCategory tablet = productCategoryDao.find(tabletId);
        List<Product> products = productDao.getBy(tablet);
        Assertions.assertEquals(tabletProducts, products.size());
    }
}