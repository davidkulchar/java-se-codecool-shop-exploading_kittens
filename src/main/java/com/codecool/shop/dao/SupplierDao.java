package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;

import java.util.List;

public interface SupplierDao {

    void add(Supplier supplier);
    Supplier find(int id);
    Supplier find(String name);
    String getAllSupplierJSON();
    void remove(int id);
    List<Supplier> getAll();
}
