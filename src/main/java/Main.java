import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.*;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        // default server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        // populate some data for the memory storage
        populateData();

        // Always start with more specific routes
        get("/hello", (req, res) -> "Hello World");

        // Always add generic routes to the end
        get("/", ProductController::renderHomePage, new ThymeleafTemplateEngine());
        // Equivalent with above
        get("/index", (Request req, Response res) -> {
           return new ThymeleafTemplateEngine().render( ProductController.renderHomePage(req, res) );
        });

        get("/categories/:name", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render( ProductController.renderProductsByCategory(req, res) );
        });

        get("/cat/:name", (Request req, Response res) -> {
            ProductDaoMem productDaoMem = ProductDaoMem.getInstance();
            return productDaoMem.getProductsByCategoryJSON(req.params(":name"));
        });

        get("/sup/:name", (Request req, Response res) -> {
            ProductDaoMem productDaoMem = ProductDaoMem.getInstance();
            return productDaoMem.getProductsBySupplierJSON(req.params(":name"));
        });


        get("/get_products", (Request req, Response res) -> {
            ProductDaoMem productDaoMem = ProductDaoMem.getInstance();
            return productDaoMem.getAllProductsJSON();
        });

        get("/get_cart", (Request req, Response res) -> {
            OrderDaoMem orderDaoMem = OrderDaoMem.getInstance();
            return orderDaoMem.getAllProductsJSON();
        });

        get("/addToCart/:id", (Request req, Response res) -> {
            ProductDaoMem productDaoMem = ProductDaoMem.getInstance();
            OrderDaoMem.getInstance().add(productDaoMem.find(Integer.parseInt(req.params(":id"))), 1);
            System.out.println(OrderDaoMem.getInstance().getAll());
            return new ThymeleafTemplateEngine().render( ProductController.renderHomePage(req, res) );
        });

//        get("/shopCar", (Request req, Response res) -> {
//            List<LineItem> container = OrderDaoMem.getInstance().getAll();
//
//        });

        // Add this line to your project to enable the debug screen
        enableDebugScreen();
    }

    public static void populateData() {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up new suppliers
        Supplier getSadCat = new Supplier("GetSadCat", "Cat shelter");
        supplierDataStore.add(getSadCat);
        Supplier hereIBomb = new Supplier("HereIBomb", "Explosives");
        supplierDataStore.add(hereIBomb);
        Supplier starkIndustry = new Supplier("Stark Industry", "Explosives");
        supplierDataStore.add(starkIndustry);


        //setting up new product categories
        ProductCategory cat = new ProductCategory("Cats", "Animals", "Cute and fluffy furballs");
        ProductCategory explosives = new ProductCategory("Explosives", "War stuff", "asd");
        productCategoryDataStore.add(cat);
        productCategoryDataStore.add(explosives);

        //setting up products and printing it
        productDataStore.add(new Product("Transcendence cat", 21, "EUR", "wut", cat, getSadCat, "trans-cat.gif"));
        productDataStore.add(new Product("B61 nuclear bomb", 965, "USD", "BOMMM", explosives, starkIndustry, "product_6.jpg"));
        productDataStore.add(new Product("Fluffy", 49, "USD", "When you didn't read the Apple's terms and conditions and it said u would turn into a cat and you like WTF but it's too late you are a cat now", cat, getSadCat, "product_1.jpg"));
        productDataStore.add(new Product("Winchester 760 gunpowder", 479, "EUR", "From China", explosives, hereIBomb, "product_2.jpg"));
        productDataStore.add(new Product("Pawny", 89, "USD", "Meow.", cat, getSadCat, "product_3.jpg"));
        productDataStore.add(new Product("Soviet Union 9K38 Igla ", 89, "USD", "You can see this usually in Russian fail videos", explosives, starkIndustry, "product_4.jpg"));
        productDataStore.add(new Product("Tom's TNT", 21, "EUR", "", explosives, hereIBomb, "tom.gif"));
        productDataStore.add(new Product("Nicolas Cate", 89, "USD", "Purrfect for acting", cat, getSadCat, "product_5.jpg"));
        productDataStore.add(new Product("Grumpy cat", 63, "USD", "No", cat, getSadCat, "product_7.jpg"));
        productDataStore.add(new Product("BOMB Petard bomb", 89, "EUR", "No, it's not cool if you throw this at people on New Year's Eve.", explosives, hereIBomb, "product_8.jpg"));
    }




}
