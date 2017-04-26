package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by davidkulchar on 2017.04.26..
 */
public class ShoppingCartDaoMem implements ShoppingCartDao{

    private List<Product> DATA = new ArrayList<>();
    private static ShoppingCartDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ShoppingCartDaoMem() {
    }

    public static ShoppingCartDaoMem getInstance() {
        if (instance == null) {
            instance = new ShoppingCartDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Product product) {
        product.setId(DATA.size() + 1);
        DATA.add(product);
    }

    @Override
    public Product find(int id) {
        return DATA.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        DATA.remove(find(id));
    }

    @Override
    public List<Product> getAll() {
        return DATA;
    }

    @Override
    public Map getPaymentDetails() {
        Map payment = new HashMap();
        int quantity = 0;
        float fullPrice = 0;
        for (Product product: getAll()) {
            quantity++;
            fullPrice += product.getCatnipPrice();
        }
        payment.put("quantity", quantity);
        payment.put("catnipPrice", fullPrice);
        return payment;
    }

    @Override
    public int getListSize() {
        return getAll().size();
    }
}

