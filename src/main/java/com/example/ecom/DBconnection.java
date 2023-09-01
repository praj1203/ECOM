package com.example.ecom;
import java.sql.*;
public class DBconnection {


    private final String dburl = "jdbc:mysql://localhost:3306/ecommerce";
    private final String userName = "root";
    private final String password = "PHW#84#jeor";

    public Statement getStatement(){
        try{
           Connection connection = DriverManager.getConnection(dburl,userName,password);
           return connection.createStatement();
        }
        catch (Exception e){
            e.printStackTrace();
        }
   return null;
    }

    public ResultSet getQuery(String query){
        try {
          Statement statement = getStatement();
          return statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    public int updateDataBase(String query){     //no of rows affected
        try {
            Statement statement = getStatement();
            return statement.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;

    }

    public static void main(String[] args) {
        DBconnection dbconnection = new DBconnection();
        ResultSet rs = dbconnection.getQuery("SELECT * FROM ecommerce.customer");
        if(rs!=null){
            System.out.println("Connecrtion successfull");
        }else{
            System.out.println("connection failed");
        }

    }

}
