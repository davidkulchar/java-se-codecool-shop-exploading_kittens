import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.*;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

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
        get("/", ProductController::renderProducts, new ThymeleafTemplateEngine());
        // Equivalent with above
        get("/index", (Request req, Response res) -> {
           return new ThymeleafTemplateEngine().render( ProductController.renderProducts(req, res) );
        });
        get("/addtocart/:id", (Request req, Response res) -> {
            ProductDaoMem productDaoMem = ProductDaoMem.getInstance();
            OrderDaoMem.getInstance().add(productDaoMem.find(Integer.parseInt(req.params(":id"))));
            System.out.println(OrderDaoMem.getInstance());
            return new ThymeleafTemplateEngine().render( ProductController.renderProducts(req, res) );
        });

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
        productDataStore.add(new Product("Fluffy", 49.9f, "USD", "Soft kitty, warm kitty, little ball of fur.\nHappy kitty, sleepy kitty,\npurr\npurr\npurr", cat, getSadCat));
        productDataStore.add(new Product("Winchester 760 gunpowder", 479, "USD", "Buy it, or you can make it from sparkpowder and charkcoal. Choose wisely...", explosives, hereIBomb));
        productDataStore.add(new Product("Pawny", 89, "USD", "Meow.", cat, getSadCat));
        productDataStore.add(new Product("Soviet Union 9K38 Igla ", 89, "USD", "asd", explosives, hereIBomb));
        productDataStore.add(new Product("Nicolas Cate", 89, "USD", "Purrfect for acting", cat, getSadCat));
        productDataStore.add(new Product("B61 nuclear bomb", 89, "USD", "BOMMM", explosives, starkIndustry));
        productDataStore.add(new Product("Grumpy cat", 89, "USD", "No", cat, getSadCat));;
    }




}
