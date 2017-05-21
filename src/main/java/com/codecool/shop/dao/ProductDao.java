package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ProductDao Interface
 */
public interface ProductDao {

    /**
     * add
     * @param product - Product object;
     * adds a new Product to Dao
     */
    void add(Product product);

    /**
     *find;
     * @param id - integer; Id of a given Product;
     * @return the given Product;
     */
    Product find(int id);

    /**
     *remove
     * @param id - integer; Id of a given Product;
     * @return the given ProductCategory;
     */
    void remove(int id);

    /**
     * @return List of Products;
     */
    List<Product> getAll();

    /**
     *getBy
     * @param supplier - Supplier Object;
     * @return  List of Products related the given supplier;
     */
    List<Product> getBy(Supplier supplier);

    /**
     *getBy
     * @param productCategory - ProductCategory Object;
     * @return  List of Products related the given productCategory;
     */
    List<Product> getBy(ProductCategory productCategory);

    /**
     *getALlProductsJSON
     * @return  String, containing a JSON with all Products;
     */
    String getAllProductsJSON();

}
