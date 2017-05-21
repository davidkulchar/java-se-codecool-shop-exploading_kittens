package com.codecool.shop.model;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by davidkulchar on 2017.04.26..
 */
public abstract class ShopList{
    private ArrayList<Product> products = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(ShopList.class);

    // Get Method
    public ArrayList<Product> getProducts(){
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(int id, int i) {
        int e = 0;
        while (e < i) {
            for (Product prod: products) {
                if (id == prod.id) {
                    products.remove(prod);
                    e++;
                }
            }
        }
    }
    public int getListSize() {
        return products.size();
    }

}
