package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by noncsi on 2017.05.11..
 */
class ProductDaoMemTest {

    @Test
    void testIsAddMethodWorks() {
        Supplier supplier = new Supplier("supplier", "this is a supplier");
        ProductCategory category = new ProductCategory("category", "department", "This is a category");
        Product product = new Product("product", 1, "EUR", "this is a product", category, supplier, "trans-cat.gif");
        ProductDao test = ProductDaoMem.getInstance();
        test.add(product);
    }

    @Test
    void testIsRemoveMethodWorks() {
        Supplier supplier = new Supplier("supplier", "this is a supplier");
        ProductCategory category = new ProductCategory("category", "department", "This is a category");
        Product product = new Product("product", 1, "EUR", "this is a product", category, supplier, "trans-cat.gif");
        ProductDao test = ProductDaoMem.getInstance();
        test.add(product);
        test.remove(1);
    }

    @Test
    public void testIsIDBiggerThan0(){
        ProductDao test = ProductDaoMem.getInstance();
        assertThrows(IllegalArgumentException.class, ()-> {
            test.find(-10);
        });
    }
}