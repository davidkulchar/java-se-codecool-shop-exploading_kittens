package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import spark.Request;
import spark.Response;
import spark.ModelAndView;

import java.util.*;

public class ProductController {

    public static ModelAndView renderHomePage(Request req, Response res) {
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoJdbc.getInstance();
        ProductDao productDataStore = ProductDaoJdbc.getInstance();

        Map params = new HashMap<>();
        params.put("category", productCategoryDataStore.getAll());
        params.put("products", productDataStore.getAll());

        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderProductsByCategory(Request req, Response res) {
        ProductDao productDataStore = ProductDaoJdbc.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoJdbc.getInstance();

        Map params = new HashMap<>();
        ProductCategory category = productCategoryDataStore.find(req.params(":name"));
        params.put("category", category);
        params.put("products", productDataStore.getBy(category));
        return new ModelAndView(params, "product/index");
    }

    public static Object renderProductsBySupplier(Request req, Response res) {
        ProductDao productDataStore = ProductDaoJdbc.getInstance();
        SupplierDao supplierDao = SupplierDaoJdbc.getInstance();

        Map params = new HashMap<>();
        System.out.println(req.params(":name"));
        Supplier supplier = supplierDao.find(req.params(":name"));
        params.put("supplier", supplier);
        params.put("products", productDataStore.getBy(supplier));
        return new ModelAndView(params, "product/index");
    }
}

