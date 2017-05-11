package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.google.gson.Gson;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductDaoJdbc implements ProductDao {

    private static final String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";
    
    private static ProductDaoJdbc ourInstance = new ProductDaoJdbc();

    public static ProductDaoJdbc getInstance() {
        return ourInstance;
    }

    private ProductDaoJdbc(){}

    @Override
    public void add(Product product) {
        String query = "INSERT INTO product (" +
                "name, " +
                "description, " +
                "default_price, " +
                "default_currency, " +
                "pic, " +
                "supplier, " +
                "product_category) " +
                "VALUES ('"
                + product.getName() + "', '"
                + product.getDescription() + "', '"
                + product.getDefaultPrice() + "','"
                + product.getDefaultCurrency() + "','"
                + product.getPic() + "','"
                + product.getSupplier().getId() + "','"
                + product.getProductCategory().getId()
                + "');";
        executeQuery(query);
    }

    @Override
    public Product find(int id) {
        String query = "SELECT * FROM product " +
                "LEFT JOIN supplier ON(product.id = supplier.id) " +
                "LEFT JOIN product_catgeroy ON(product.id = product_category.id) " +
                "WHERE product.id ='" + id + "';";

        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){
            if (resultSet.next()){
                Supplier supplier = new Supplier(resultSet.getString(""))
                Product result = new Product(resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("defaultPrice"),
                        resultSet.getString("defaultCurrency"),
                        resultSet.getString("pic"),
                        resultSet.getString("supplier"),
                        resultSet.getString("productCategory")));
                return result;
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
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
    public List<Product> getBy(Supplier supplier) {
        return DATA.stream().filter(t -> t.getSupplier().equals(supplier)).collect(Collectors.toList());
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return DATA.stream().filter(t -> t.getProductCategory().equals(productCategory)).collect(Collectors.toList());
    }

    @Override
    public String getAllProductsJSON(){
        Gson gson = new Gson();
        List<Map> productList = getHashListForJSON(DATA);
        return gson.toJson(productList);
    }

    @Override
    public String getProductsByCategoryJSON(String catName){
        Gson gson = new Gson();
        List<Product> filteredList = filterProductsByCategory(catName);
        List<Map> productList = getHashListForJSON(filteredList);
        return gson.toJson(productList);
    }

    @Override
    public String getProductsBySupplierJSON(String supName){
        Gson gson = new Gson();
        List<Product> filteredBySupplierList = filterProductsBySupplier(supName);
        List<Map> prodList = getHashListForJSON(filteredBySupplierList);
        return gson.toJson(prodList);
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

    private void executeQuery(String query) {
        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
        ){
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
