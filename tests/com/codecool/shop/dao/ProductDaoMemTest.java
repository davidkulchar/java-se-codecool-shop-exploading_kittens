package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by noncsi on 2017.05.11..
 */
class ProductDaoMemTest {

    private static final ProductDao implementation = ProductDaoMem.getInstance();

    @Test
    public void testIsGetAllWorking(){
        Supplier supplier = new Supplier("supplier", "this is a supplier");
        ProductCategory category = new ProductCategory("category", "department", "This is a category");
        Product product1 = new Product("product_1", 1, "EUR", "this is a product", category, supplier, "trans-cat.gif");
        Product product2 = new Product("product_2", 1, "EUR", "this is a product", category, supplier, "trans-cat.gif");
        ProductDao test = implementation;
        test.add(product1);
        test.add(product2);
        assertEquals(2,test.getAll().size());
        clearDao();
    }

    @Test
    void testGetInstanceSingleton(){
        ProductDao storage_1 = implementation;
        ProductDao storage_2 = implementation;
        assertEquals(storage_1.hashCode(), storage_2.hashCode());
        clearDao();
    }

    @Test
    void testIsAddMethodWorks() {
        Supplier supplier = new Supplier("supplier", "this is a supplier");
        ProductCategory category = new ProductCategory("category", "department", "This is a category");
        Product product = new Product("product", 1, "EUR", "this is a product", category, supplier, "trans-cat.gif");
        ProductDao prodDataStore = implementation;
        prodDataStore.add(product);
        assertEquals(1, prodDataStore.getAll().size());
        clearDao();
    }

    @Test
    void testIsRemoveMethodWorks() {
        ProductDao storage = implementation;
        int sizeBefore = storage.getAll().size();
        System.out.println("sizeBefore: " + sizeBefore);
        Supplier supplier = new Supplier("supplier", "this is a supplier");
        ProductCategory category = new ProductCategory("category", "department", "This is a category");
        Product product = new Product("product", 1, "EUR", "this is a product", category, supplier, "trans-cat.gif");
        storage.add(product);
        storage.remove(1);
        int sizeAfter = storage.getAll().size();
        System.out.println("sizeAfter: " + sizeAfter);
        System.out.println(storage.getAll().get(0).getName());
        System.out.println(storage.getAll().get(1).getName());
        assertEquals(sizeBefore, sizeAfter);
        clearDao();
    }

    private void clearDao(){
        ProductDao storage = implementation;
        List<Product> productList = storage.getAll();
        if (productList.size() > 0) {
            for (int i=0; i<productList.size(); i++) {
                storage.remove(productList.get(i).getId());
            }
        }
    }
}