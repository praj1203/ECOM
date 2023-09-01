package com.example.ecom;

import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class orders {
    ProductList productList = new ProductList();

    public static boolean placeorder(customer Customer, Product product) {
        String groupOrderedId = "select max(group_orderd_id)+1 id from ecommerce.orders;";
        DBconnection dbconnection = new DBconnection();
        try {
            ResultSet rs = dbconnection.getQuery(groupOrderedId);
            if (rs.next()) {
                String placeorder = "INSERT INTO `ecommerce`.`orders` (`group_orderd_id`, `customer_id`, `product_id`) VALUES ('" + rs.getInt("id") + "', '" + Customer.getId() + "', '" + product.getId() + "');";
                return dbconnection.updateDataBase(placeorder) != 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int placeMultipleorder(customer Customer, ObservableList<Product> productList) {
        String groupOrderedId = "select max(group_orderd_id)+1 id from ecommerce.orders;";
        DBconnection dbconnection = new DBconnection();
        try {
            ResultSet rs = dbconnection.getQuery(groupOrderedId);
            int count = 0;
            if (rs.next()) {
                for (Product product : productList) {
                    String placeorder = "INSERT INTO `ecommerce`.`orders` (`group_orderd_id`, `customer_id`, `product_id`) VALUES ('" + rs.getInt("id") + "', '" + Customer.getId() + "', '" + product.getId() + "');";
                    count = count + dbconnection.updateDataBase(placeorder);
                }
                return count;
            }

            }catch(Exception e){
                e.printStackTrace();
            }
            return 0;

    }

}