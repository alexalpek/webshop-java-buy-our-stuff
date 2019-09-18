package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SupplierDaoJDBCTest extends DaoJDBCTest {

    @Test
    @DisplayName("Test add() method")
    void testAdd() {
        Supplier supplier = new Supplier("test", "test");
        SupplierDao supplierDao = new SupplierDaoJDBC();
        supplierDao.add(supplier);
        Assertions.assertNotEquals(0, supplier.getId());
    }

    @Test
    @DisplayName("Test find() method - valid id")
    void testFind_validId() {
        SupplierDao supplierDao = new SupplierDaoJDBC();
        Supplier result = supplierDao.find(2);
        Assertions.assertEquals(2, result.getId());
    }

    @Test
    @DisplayName("Test find() method - invalid id")
    void testFind_invalidId() {
        SupplierDao supplierDao = new SupplierDaoJDBC();
        Supplier result = supplierDao.find(-1);
        Assertions.assertNull(result);
    }
}