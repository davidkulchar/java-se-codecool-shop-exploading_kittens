package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;

import java.util.List;

/**
 * ProductCategoryDao Interface
 */

public interface ProductCategoryDao {

    /**
     * add
     * @param category - adds a New ProductCategory to the Dao;
     */
    void add(ProductCategory category);

    /**
     *
     * @param id - integer; Id of a given productCategory;
     * @return the given ProductCategory;
     */
    ProductCategory find(int id);

    /**
     * find
     * @param name - String. Finds the given ProductCategory, by name;
     * @return the given
     */
    ProductCategory find(String name);

    /**
     * remove
     * @param id -integer; If of a given Product;
     * removes the given product;
     */
    void remove(int id);

    /**
     * getAllProcutsJSON
     * @return String, filled with a HashMap of all ProductCategory;
     */
    String getAllProductCategoryJSON();

    /**
     getAll
     * @return List of all ProductCategory;
     */
    List<ProductCategory> getAll();

}
