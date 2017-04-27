package com.codecool.shop.model;

import com.codecool.shop.model.Product;

import java.util.HashMap;
import java.util.Map;

/**
 Describes a quantity of an article to purchase.
 */
public class LineItem
{

    private int quantity;
    /**
     Constructs an item from the product and quantity.
     @param aProduct the product
     @param aQuantity the item quantity
     */
    public LineItem(Product aProduct, int aQuantity)
    {
        theProduct = aProduct;
        quantity = aQuantity;
    }

    /**
     Computes the total cost of this line item.
     @return the total price
     */
    public double getTotalPrice()
    {
        return theProduct.getCatnipPrice() * quantity;
    }

    /**
     Formats this item.
     @return a formatted string of this item
     */
    public String format()
    {
        return String.format("%-30s%8.2f%5d%8.2f",
                theProduct.getDescription(), theProduct.getPrice(),
                quantity, getTotalPrice());
    }

    private Product theProduct;

    public int getQuantity() {
        return quantity;
    }

    public int getId() {
        return theProduct.id;
    }

    public void setQuantity(int quant) {
        quantity += quant;
    }

    public Product getProduct() {
        return theProduct;
    }

    public HashMap getDetails(){
        HashMap<String, String> details = new HashMap();
        details.put("productId", String.valueOf(getProduct().id));
        details.put("product", getProduct().name);
        details.put("quantity", String.valueOf(quantity));
        details.put("totalPrice", String.valueOf(getTotalPrice()));
        return details;

    }
}