package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.*;
import com.google.gson.Gson;

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
        LineItem newItem = new LineItem(product, quantity);
        DATA.add(newItem);
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
    public String getAllProductsJSON(){
        Gson gson = new Gson();
        List<Map> productList = new ArrayList<>();

        for (LineItem ln: DATA) {
            Map product = new HashMap();
            product.put("name", ln.getProduct().getName());
            product.put("priceTag", ln.getProduct().getPrice());
            product.put("id", ln.getProduct().getId());
            product.put("quantity", ln.getQuantity());
            productList.add(product);
        }
        Map payment = getPaymentDetails();
        Map fullMap = new HashMap();
        fullMap.put("quantity", payment.get("quantity"));
        fullMap.put("catnipPrice", payment.get("catnipPrice"));
        fullMap.put("items", productList);
        System.out.println(fullMap);
        return gson.toJson(fullMap);
    }

    @Override
    public int getListSize() {
        return getAll().size();
    }
}

