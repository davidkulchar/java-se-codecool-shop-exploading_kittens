package com.codecool.shop.model;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Supplier Model Object, inherits from BaseModel.
 *
 * <P>Various attributes of Product, and related behaviour.
 *
 * Products -- ArrayList, contains Product objects realted to the given Supplier
 */


public class Supplier extends BaseModel {
    private static final Logger logger = LoggerFactory.getLogger(Supplier.class);
    private ArrayList<Product> products;


    /**
     *
     * @reqparam name -- Stting. Name of Supplier.
     * @param description -- String.
     */
    public Supplier(String name, String description) {
        super(name, description);
        this.products = new ArrayList<>();
    }

    /**
     * setProducts
     * @param products -- ArrayList of Products.
     * Sets products;
     */
    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    /**
     * getProducts
     * @return returns the ArrayList of products;
     */
    public ArrayList getProducts() {
        return this.products;
    }


    /**
     * addProduct;
     * @param product - Product Objects;
     * adds a new Product to products;
     */
    public void addProduct(Product product) {
        this.products.add(product);
    }


    /**
     * toString
     * @return String - Fromatted String of the given Object;
     */
    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "description: %3$s",
                this.id,
                this.name,
                this.description
        );
    }
}