package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import com.google.common.collect.Lists;
import spark.Request;
import spark.Response;
import spark.ModelAndView;

import java.util.*;

public class ProductController {

    public static ModelAndView renderHomePage(Request req, Response res) {
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

        Map params = new HashMap<>();
        params.put("category", productCategoryDataStore.getAll());
        params.put("products", newProducts());
        return new ModelAndView(params, "product/index");
    }

    private static List<Product> newProducts(){
        ProductDao productDataStore = ProductDaoMem.getInstance();
        List<Product> newProducts = new ArrayList<>();
        List<Product> reverseProducts = Lists.reverse(productDataStore.getAll());

        for (int i = 0; i < 3; i++){
            newProducts.add(reverseProducts.get(i));
        }
        return newProducts;
    }

    public static ModelAndView renderProductsByCategory(Request req, Response res) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

        Map params = new HashMap<>();
        ProductCategory category = productCategoryDataStore.find(req.params(":name"));
        params.put("category", category);
        params.put("products", productDataStore.getBy(category));
        return new ModelAndView(params, "product/index");
    }
}

