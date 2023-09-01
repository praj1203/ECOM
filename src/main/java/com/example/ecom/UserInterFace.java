package com.example.ecom;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.event.ActionEvent;
        import javafx.event.EventHandler;
        import javafx.geometry.Insets;
        import javafx.geometry.Pos;
        import javafx.scene.control.*;
        import javafx.scene.image.Image;
        import javafx.scene.image.ImageView;
        import javafx.scene.layout.*;
        import javafx.scene.text.Text;

public class UserInterFace {
    GridPane loginpage;
    HBox headerBox;
    HBox footerBar;
    ProductList productList = new ProductList();
    VBox body ;
    VBox productpage;
    customer LoggedinCustomer;

    Label welcomeLabel;

    Button PlaceOrderButton = new Button("Place Order");

    ObservableList<Product> itemsInCart = FXCollections.observableArrayList();



    public BorderPane createContent() {
        BorderPane root = new BorderPane();
        root.setPrefSize(800, 600);
        // root.setStyle("-fx-background-color: #C0C0C0");
        body = new VBox();
        body.setPadding(new Insets(10));
        body.setAlignment(Pos.CENTER);
        root.setTop(headerBox);
        root.setCenter(body);
        productpage = productList.getAllProduct();
        body.getChildren().add(productpage);
        root.setBottom(footerBar);
        return root;
    }

    public UserInterFace() {
        createLoginPage();
        createHeaderBar();
        createFooterBar();


    }

    public void createLoginPage() {
        loginpage = new GridPane();
        //  loginpage.setStyle("-fx-background-color: RED");
        Text username = new Text("Username");
        Text password = new Text("Password");

        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();

        passwordField.setPromptText("Enter Password");
        usernameField.setPromptText("EnterUserName");

        Button loginButton = new Button("Login");
        Label messegeLabel = new Label("hi");

        //  LoginButton.setStyle("-fx-background-color: YELLOW");
        loginpage.add(loginButton, 1, 2);
        loginpage.setAlignment(Pos.CENTER);
        loginpage.add(password, 0, 1);
        loginpage.add(passwordField, 1, 1);
        loginpage.add(username, 0, 0);
        loginpage.add(usernameField, 1, 0);
        loginpage.setHgap(10);
        loginpage.setVgap(10);
        loginpage.add(messegeLabel,0,2);

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String name = usernameField.getText();
                String pass = passwordField.getText();
                Login login = new Login();
                LoggedinCustomer = login.customerLogin(name, pass);
                if(LoggedinCustomer!=null){
                    messegeLabel.setText("Welcome : " + LoggedinCustomer.getName());
                    welcomeLabel = new Label("Welcome "+ LoggedinCustomer.getName());
                    headerBox.getChildren().add(welcomeLabel);
                    body.getChildren().clear();
                    body.getChildren().add(productpage);

                }
                else{
                    messegeLabel.setText("Loggin Failed");
                }


            }
        });



    }

    public void createHeaderBar() {
        Button homeButton = new Button();
        Image homeImage = new Image("C:\\Users\\Lenovo\\Desktop\\ECOM\\src\\main\\java\\com\\example\\ecom\\ecommerceLogo.jpg");
        ImageView imageView = new ImageView();
        imageView.setImage(homeImage);
        imageView.setFitHeight(20);
        imageView.setFitWidth(80);
        homeButton.setGraphic(imageView);

        TextField searchBar = new TextField();
        searchBar.setPromptText("Search Here ...");
        Button searchButton = new Button("Search");
        Button SignIn = new Button("SignIn");
        Button cartButton = new Button("Cart");
        Button ordres = new Button("Orders");
        headerBox = new HBox();
        headerBox.setStyle("-fx-background-color: #C0C0C0");
        headerBox.setPadding(new Insets(10));
        headerBox.setAlignment(Pos.CENTER);
        headerBox.setSpacing(10);
        headerBox.getChildren().addAll(homeButton,searchBar, searchButton, SignIn,cartButton,ordres);

        homeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                body.getChildren().add(productpage);
                footerBar.setVisible(true);
                if(LoggedinCustomer==null&& headerBox.getChildren().indexOf(SignIn)==-1){
                   headerBox.getChildren().add(SignIn);
                }

            }
        });

        SignIn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                body.getChildren().add(loginpage);
                headerBox.getChildren().remove(SignIn);

            }
        });

        cartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                VBox productInCartPage= productList.getProductsInCart(itemsInCart);
                productInCartPage.setAlignment(Pos.CENTER);
                productInCartPage.setSpacing(10);
                productInCartPage.getChildren().add(PlaceOrderButton);
                body.getChildren().add(productInCartPage);
                footerBar.setVisible(false);

            }
        });

        PlaceOrderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if(itemsInCart == null){
                    showMessege("Please add the product in  the Cart");
                    return;
                }
                if(LoggedinCustomer==null){
                    showMessege("Login to place the Order");
                    return;
                }

                int count = orders.placeMultipleorder(LoggedinCustomer,itemsInCart);
                if(count != 0){
                    showMessege("Order of "+count+ " product  Placed Sucessfully");
                }else{
                    showMessege("Order failed");
                }
            }
        });

        ordres.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });

    }


    public void createFooterBar () {



        Button buyButton = new Button("Buy");
        Button cartButton = new Button("AddCart");
        footerBar = new HBox();
        footerBar.setStyle("-fx-background-color: #C0C0C0");
        footerBar.setPadding(new Insets(10));
        footerBar.setAlignment(Pos.BOTTOM_CENTER);
        footerBar.setSpacing(10);
        footerBar.getChildren().addAll(buyButton,cartButton);

        buyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product = productList.getSelectedProduct();
                if(product == null){
                    showMessege("Please Select the Product");
                    return;
                }
                if(LoggedinCustomer==null){
                    showMessege("Login to place the Order");
                    return;
                }

                boolean status = orders.placeorder(LoggedinCustomer,product);
                if(status == true){
                    showMessege("Order Placed Sucessfully");
                }else{
                    showMessege("Order failed");
                }


            }


        });


        //AddTo Cart Functionalityy

        cartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product = productList.getSelectedProduct();
                if(product == null){
                    showMessege("Please Select the Product to Add in the Cart");
                    return;
                }
                itemsInCart.add(product);
                showMessege("Added In Cart Successfully ");
            }
        });


    }

    public void showMessege(String messege){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Messege");
        alert.setContentText(messege);
        alert.showAndWait();
    }

}