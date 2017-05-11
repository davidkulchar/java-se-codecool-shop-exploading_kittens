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
        ProductDao productDataStore = ProductDaoMem.getInstance();

        Map params = new HashMap<>();
        params.put("category", productCategoryDataStore.getAll());
        params.put("products", productDataStore.getAll());

        return new ModelAndView(params, "product/index");
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

    public static Object renderProductsBySupplier(Request req, Response res) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        SupplierDaoMem supplierDao = SupplierDaoMem.getInstance();

        Map params = new HashMap<>();
        Supplier supplier = supplierDao.find(req.params(":name"));
        params.put("supplier", supplier);
        params.put("products", productDataStore.getBy(supplier));
        return new ModelAndView(params, "product/index");
    }
}

