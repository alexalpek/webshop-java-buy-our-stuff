package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class SupplierDaoTest extends DaoTest {

    @Test
    void testAdd() {
        Supplier supplier = new Supplier("test", "test");
        SupplierDao supplierDao = DaoController.getSupplierDao();
        supplierDao.add(supplier);
        Assertions.assertNotEquals(0, supplier.getId());
    }

    @Test
    void testFind_validId() {
        SupplierDao supplierDao = DaoController.getSupplierDao();
        Supplier result = supplierDao.find(2);
        Assertions.assertEquals(2, result.getId());
    }

    @Test
    void testFind_invalidId() {
        SupplierDao supplierDao = DaoController.getSupplierDao();
        Assertions.assertThrows(DataNotFoundException.class, () -> supplierDao.find(-1));
    }

    @Test
    void testRemove() {
        int id = 1;
        SupplierDao supplierDao = DaoController.getSupplierDao();
        Supplier supplier = supplierDao.find(id);
        supplierDao.remove(id);
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(DataNotFoundException.class, () -> supplierDao.find(id));
    }

    @Test
    void testGetAll() {
        int supplierCount = 4;
        SupplierDao supplierDao = DaoController.getSupplierDao();
        List<Supplier> suppliers = supplierDao.getAll();
        Assertions.assertEquals(supplierCount, suppliers.size());
    }
}