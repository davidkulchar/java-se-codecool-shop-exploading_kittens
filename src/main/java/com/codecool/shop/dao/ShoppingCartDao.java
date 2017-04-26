package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.List;
import java.util.Map;

/**
 * Created by davidkulchar on 2017.04.26..
 */
public interface ShoppingCartDao {
    void add(Product product);
    Product find(int id);
    void remove(int id);
    int getListSize();

    List<Product> getAll();
    Map getPaymentDetails();
}
