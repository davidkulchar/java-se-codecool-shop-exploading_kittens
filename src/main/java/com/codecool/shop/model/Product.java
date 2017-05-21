package com.codecool.shop.model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Currency;


/**
 * Product Model Object, inherits from BaseModel.
 *
 * <P>Various attributes of Product, and related behaviour.
 *
*/

public class Product extends BaseModel {
    private static final Logger logger = LoggerFactory.getLogger(BaseModel.class);
    private float defaultPrice;
    private Currency defaultCurrency;
    private ProductCategory productCategory;
    private Supplier supplier;
    public String pic;
    public String priceTag;



    /**
     * Constructor.
     *Sets the following parameters:
     * @param name (required) name of the product. String.
     * @param description (required) description of the given Product. String.
     * @param defaultPrice (required) purchase price of the guitar. float.
     * @param currencyString (required) code of the given Currency. String.
     * @param productCategory (required) sets the products category. ProductCategory object.
     * @param pic (required) filepath of the picture of product. String.
     * @param supplier (required) sets the supplier. Supplier object.
     */
    public Product(String name, float defaultPrice, String currencyString, String description, ProductCategory productCategory, Supplier supplier, String pic) {
        super(name, description);
        this.setPrice(defaultPrice, currencyString);
        this.setSupplier(supplier);
        this.setProductCategory(productCategory);
        this.setPic(pic);
        this.priceTag = getPrice();
        logger.info("Created Procuct: {}", this.name);
    }

    /**
     * Getter and Setter functions:
     * getDefaultPrice;
     * setDefaultPrice;
     * getDefaultCurrency;
     * setDefaultCurrency;
     * getPrice;
     * setPrice;
     * getProductCategory;
     * getSupplier;
     * setPic;
     * getPic;
     */
    public float getDefaultPrice() {
        return defaultPrice;
    }
    public void setDefaultPrice(float defaultPrice) {
        this.defaultPrice = defaultPrice;
    }
    public Currency getDefaultCurrency() {
        return defaultCurrency;
    }
    public void setDefaultCurrency(Currency defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }
    public String getPrice() {
        return String.valueOf(this.defaultPrice) + " " + this.defaultCurrency.toString();
    }
    public void setPrice(float price, String currency) {
        this.defaultPrice = price;
        this.defaultCurrency = Currency.getInstance(currency);
    }
    public ProductCategory getProductCategory() {
        return productCategory;
    }
    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
        this.productCategory.addProduct(this);
    }
    public Supplier getSupplier() {
        return supplier;
    }
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
//        this.supplier.addProduct(this);
    }


    /**
     * toString
     * @return returns a string, with all attributes of the given Object.
     */
    @Override
    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "defaultPrice: %3$f, " +
                        "defaultCurrency: %4$s, " +
                        "productCategory: %5$s, " +
                        "supplier: %6$s",
                this.id,
                this.name,
                this.defaultPrice,
                this.defaultCurrency.toString(),
                this.productCategory.getName(),
                this.supplier.getName());
    }


    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPic() {
        return pic;
    }

    /**
     * getHUFPrice
     * @return the Price in HUF of the Given Product;
     */
    public float getHUFPrice() {
        if (defaultCurrency == Currency.getInstance("HUF")){
            return defaultPrice;
        } else if (defaultCurrency == Currency.getInstance("USD")) {
            return (280*defaultPrice);
        } else if (defaultCurrency == Currency.getInstance("EUR")) {
            return (310*defaultPrice);
        }
        return 0;
    }
}
