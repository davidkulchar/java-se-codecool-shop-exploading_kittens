package com.codecool.shop.model;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ProductCategory Model Object, inherits from BaseModel.
 *
 * <P>Various attributes of Product, and related behaviour.
 *
 */

public class ProductCategory extends BaseModel {
    private static final Logger logger = LoggerFactory.getLogger(ProductCategory.class);
    private String department;
    private ArrayList<Product> products;

    /**
     * Constructor
     * creates empty ArrayList for the Procuts realted to it.
     * @reqparam name - String. Name of Object;
     * @reqparam department - String. Department of the given ProductCategory;
     * @reqparam description - String.
     */
    public ProductCategory(String name, String department, String description) {
        super(name, description);
        this.department = department;
        this.products = new ArrayList<>();
    }

    /**
     * Get and Set Methods:
     * getDepartment;
     * setDepartment;
     * getProducts;
     */
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
    public ArrayList getProducts() {
        return this.products;
    }

    /**
     * addProducts
     * @param product - ProductObject;
     * adds the given ProductObject to its products.
     */
    public void addProduct(Product product) {
        this.products.add(product);
    }


    /**
     *toString
     * @return a String with its attributes;
     */
    public String toString() {
        return String.format(
                "id: %1$d," +
                        "name: %2$s, " +
                        "department: %3$s, " +
                        "description: %4$s",
                this.id,
                this.name,
                this.department,
                this.description);
    }
}