package com.codecool.shop.dao;

import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.List;
import java.util.Map;

/**
 * Created by davidkulchar on 2017.04.26..
 */

/**
 * OrderDaoInterface
 */


public interface OrderDao {

    /**
     * add
     * Adds a New Project to the Given Order.
     * @param product -Products;
     * @param quantity - int;
     */
    void add(Product product, int quantity);

    /**
     * find
     * @param id -integer; Id of a given Product;
     * @return LineItem;
     */
    LineItem find(int id);

    /**
     * remove
     * @param id -integer; If of a given Product;
     * removes the given product;
     */
    void remove(int id);

    /**
     * getAllProcutsJSON
     * @return String, filled with a HashMap of all Products;
     */
    String getAllProductsJSON();

    /**
     *
     * @return List of all LineItems;
     */
    List<LineItem> getAll();
}
