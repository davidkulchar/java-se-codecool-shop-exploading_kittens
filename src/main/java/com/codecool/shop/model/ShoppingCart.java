package com.codecool.shop.model;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ShoppingCard --- currently under development
 */
public class ShoppingCart extends ShopList {
    private static final Logger logger = LoggerFactory.getLogger(ShoppingCart.class);

    public Map generatePaymentDetails() {
        Map payment = new HashMap();
        int quantity = 0;
        float fullPrice = 0;
        for (Product product: getProducts()) {
            quantity++;
            fullPrice += product.getHUFPrice();
        }
        payment.put("quantity", quantity);
        payment.put("catnipPrice", fullPrice);
        return payment;
    }
}
