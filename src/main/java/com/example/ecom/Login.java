package com.example.ecom;

import java.sql.ResultSet;

public class Login {
    public customer customerLogin(String userName , String password){
        String query = "select * from ecommerce.customer where Email='"+userName+"' AND Password = '"+password+"'";
        DBconnection dBconnection = new DBconnection();
//        try {
//            ResultSet rs = dBconnection.getQuery(query);
//            if(rs.next()){
//                return new customer(rs.getInt("ID"),rs.getString("Email"),rs.getString("Name"));
//
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }

//        try{
//            ResultSet rs = dBconnection.getQuery(query);
//            if(rs.next()) {
//                return true;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

        try {
            ResultSet rs = dBconnection.getQuery(query);
            if (rs.next()){
                  return  new customer(rs.getInt("ID"),rs.getString("Name"),rs.getString("Email"));
            }
           }catch (Exception e){
            e.printStackTrace();
        }
          return null;
        }

    public static void main(String[] args) {
       Login login = new Login();
       customer Customer = login.customerLogin("prajwaljawale@gmail.com","12344567");
        if (Customer==null){
            System.out.println("Null");
        }else{
            System.out.println("Not nulll");
        }
    }
}
