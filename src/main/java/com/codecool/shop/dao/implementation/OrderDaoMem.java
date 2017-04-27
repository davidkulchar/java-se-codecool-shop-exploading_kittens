package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by davidkulchar on 2017.04.26..
 */
public class OrderDaoMem implements OrderDao {

    private ArrayList<LineItem> DATA = new ArrayList<>();
    private static OrderDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private OrderDaoMem() {
    }

    public static OrderDaoMem getInstance() {
        if (instance == null) {
            instance = new OrderDaoMem();
        }
        return instance;
    }


    @Override
    public void add(Product product, int quantity) {
        if (DATA.contains(product)){
            find(product.getId()).setQuantity(quantity);
        } else {
            product.setId(DATA.size() + 1);
            LineItem newItem = new LineItem(product, quantity);
            DATA.add(newItem);
        }
    }

    @Override
    public LineItem find(int id) {
        return DATA.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        DATA.remove(find(id));
    }

    @Override
    public List<LineItem> getAll() {
        return DATA;
    }

    @Override
    public Map getPaymentDetails() {
        Map payment = new HashMap();
        int quantity = 0;
        float fullPrice = 0;
        for (LineItem item: getAll()) {
            quantity+= item.getQuantity();
            fullPrice += item.getProduct().getCatnipPrice();
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

