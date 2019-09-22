package com.codecool.shop.dao;

import com.codecool.shop.dao.DaoController;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;

public class DaoTest {

    @BeforeEach
    void initData() {
        DaoController.init();
    }

    @AfterAll
    static void clearData() {
        DaoController.clear();
    }
}
