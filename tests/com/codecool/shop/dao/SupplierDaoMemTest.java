package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by noncsi on 2017.05.11..
 */
class SupplierDaoMemTest {

    @Test
    public void testIsIDBiggerThan0(){
        SupplierDao test = SupplierDaoMem.getInstance();
        assertThrows(IllegalArgumentException.class, ()-> {
            test.find(-10);
        });
    }

    @Test
    public void testIsRemoveIDBiggerThan0(){
        SupplierDao test = SupplierDaoMem.getInstance();
        assertThrows(IllegalArgumentException.class, ()-> {
            test.remove(-10);
        });
    }

    @Test
    public void testSuppliersAreExist(){
        SupplierDao test = SupplierDaoMem.getInstance();
        List suppliers = test.getAll();
        assertNotNull(suppliers);
    }
}