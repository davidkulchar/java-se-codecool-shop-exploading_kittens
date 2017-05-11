package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by noncsi on 2017.05.11..
 */
class OrderDaoMemTest {

    @Test
    public void testOrdersAreExist(){
        OrderDao test = OrderDaoMem.getInstance();
        List orders = test.getAll();
        assertNotNull(orders);
    }

    @Test
    public void testIsIDBiggerThan0(){
        OrderDao test = OrderDaoMem.getInstance();
        assertThrows(IllegalArgumentException.class, ()-> {
            test.find(-10);
        });
    }
}