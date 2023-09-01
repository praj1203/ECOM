package com.example.ecom;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.VBox;

import static javafx.scene.control.TableView.CONSTRAINED_RESIZE_POLICY;


public class ProductList  {

    private TableView<Product> productTable;



    public VBox createTable(  ObservableList<Product> data ) {
//        TableColumn <Product,Integer> id  = new TableColumn("ID");
//        id.setCellFactory(new PropertyValueFactory<>("id"));
         TableColumn<Product, Integer> id = new TableColumn<>("ID");
         id.setCellValueFactory(new PropertyValueFactory<>("id"));

         TableColumn<Product, String> name = new TableColumn<>("NAME");
         name.setCellValueFactory(new PropertyValueFactory<>("name"));

         TableColumn<Product,Double> price = new TableColumn<>("PRICE");
         price.setCellValueFactory(new PropertyValueFactory<>("price"));

         

//       TableColumn name = new TableColumn("NAME");
//        name.setCellFactory(new PropertyValueFactory<>("name"));
//
//
//        ObservableList<Product> data = FXCollections.observableArrayList();
//        data.add(new Product(1, "Lenovo",1234));
//        data.add(new Product(2,"Iphone",100000));

        productTable = new TableView<>();
        productTable.setItems(data);

        productTable.getColumns().addAll(id, name,price);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        VBox vBox = new VBox();
        vBox.getChildren().add(productTable);
        return vBox;


    }
   public VBox getDummyTable(){
       ObservableList<Product> data = FXCollections.observableArrayList();
       data.add(new Product(1, "Lenovo",1234));
       data.add(new Product(2,"Iphone",100000));
       return  createTable(data);
   }

   public VBox getAllProduct(){
        ObservableList<Product> data = Product.getAllProduct();
        return createTable(data);
   }

   public Product getSelectedProduct(){
        return  productTable.getSelectionModel().getSelectedItem();
   }

   public VBox getProductsInCart(ObservableList<Product> data){
        return createTable(data);
   }


}
