package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by noncsi on 2017.05.11..
 */
class ProductCategoryDaoMemTest {

    @Test
    public void testProductsAreExist(){
        ProductCategoryDao test = ProductCategoryDaoMem.getInstance();
        List categories = test.getAll();
        assertNotNull(categories);
    }

    @Test
    public void testIsIDBiggerThan0() {
        ProductCategoryDaoMem test = ProductCategoryDaoMem.getInstance();
        assertThrows(IllegalArgumentException.class,()-> {
            test.find(-10);
        });
    }

    @Test
    public void testIsGetAllWorking(){
        ProductCategory category1 = new ProductCategory("category_1", "department", "This is a category");
        ProductCategory category2 = new ProductCategory("category_2", "department", "This is a category");
        ProductCategoryDao test = ProductCategoryDaoMem.getInstance();
        test.add(category1);
        test.add(category2);
        assertEquals(2,test.getAll().size());
    }
}