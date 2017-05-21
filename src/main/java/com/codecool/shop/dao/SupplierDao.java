package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;

import java.util.List;

/**
 * SupplierDao
 */
public interface SupplierDao {

    /**
     *add
     * @param supplier - Supplier Object;
     * @return  List of Products related the given supplier;
     */
    void add(Supplier supplier);

    /**
     *getBy
     * @param id - integer; Id of a given Supplier;
     * @return the given supplier;
     */
    Supplier find(int id);

    /**
     *getBy
     * @param name - String; name of a given Supplier;
     * @return the given supplier;
     */
    Supplier find(String name);

    /**
     *getAllSupplierJSON
     * @return A String, containing a JSON, filled with Suppliers;
     */
    String getAllSupplierJSON();

    /**
     *remove
     * @param id - integer; Id of a given Supplier;
     * removes the given supplier;
     */
    void remove(int id);

    /**
     *getBy
     * @return a List containing the all suppliers;
     */
    List<Supplier> getAll();
}
