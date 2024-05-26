package com.example.dbproject;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import java.sql.ResultSet;

public class productsController {
    @FXML
    VBox productsVbox;
    @FXML
    ComboBox<String> statusComboBox;
    @FXML
    TextField xquantity, xprice, search;
    @FXML
    public void initialize() {
        loadAllProducts();

        // adding values in combo box
        ObservableList<String> status = FXCollections.observableArrayList();
        status.add("By Weight");
        status.add("By Quantity");
        status.add("Both");

        statusComboBox.setItems(status);

        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        xquantity.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    pause.setOnFinished(event -> {
                        filterByQuantity();
                    });
                    pause.playFromStart();
                }
        );
        xprice.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    pause.setOnFinished(event -> {
                        filterByPrice();
                    });
                    pause.playFromStart();
                }
        );
        search.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    pause.setOnFinished(event -> {
                        filterBySearch();
                    });
                    pause.playFromStart();
                }
        );
    }
    @FXML
    protected void sortByUnitSellingCost() {
        clearAllProducts();

        try {
            String q = "SELECT p.P_ID,p.P_NAME,p.P_PRICE,p.P_PURCHASE,pd.P_QUANTITY,pd.P_WEIGHT,pd.P_TYPE FROM PRODUCT p\n" +
                    "inner join [PRODUCT DETAILS] pd on p.P_ID = pd.P_ID and pd.P_AVAILABILITY='available'" +
                    "order by\n" +
                    "p.P_PRICE asc";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("productRow.fxml"));
                Parent row = loader.load();

                productRowController prc = loader.getController();
                prc.productId.setText(res.getString("P_ID"));
                prc.productName.setText(res.getString("P_NAME"));
                prc.productType.setText(res.getString("P_TYPE").equals("W") ? "By Weight": "By Quantity");
                prc.unitPurchaseCost.setText(res.getString("P_PURCHASE"));
                prc.unitSellingCost.setText(res.getString("P_PRICE"));
                prc.weightQuantity.setText(res.getString("P_WEIGHT"));
                prc.availableAmount.setText(res.getString("P_QUANTITY"));

                productsVbox.getChildren().add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void sortByUnitPurchaseCost() {
        clearAllProducts();

        try {
            String q = "SELECT p.P_ID,p.P_NAME,p.P_PRICE,p.P_PURCHASE,pd.P_QUANTITY,pd.P_WEIGHT,pd.P_TYPE FROM PRODUCT p\n" +
                    "inner join [PRODUCT DETAILS] pd on p.P_ID = pd.P_ID and pd.P_AVAILABILITY='available'" +
                    "order by\n" +
                    "p.P_PURCHASE asc";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("productRow.fxml"));
                Parent row = loader.load();

                productRowController prc = loader.getController();
                prc.productId.setText(res.getString("P_ID"));
                prc.productName.setText(res.getString("P_NAME"));
                prc.productType.setText(res.getString("P_TYPE").equals("W") ? "By Weight": "By Quantity");
                prc.unitPurchaseCost.setText(res.getString("P_PURCHASE"));
                prc.unitSellingCost.setText(res.getString("P_PRICE"));
                prc.weightQuantity.setText(res.getString("P_WEIGHT"));
                prc.availableAmount.setText(res.getString("P_QUANTITY"));
                prc.moreDetails.setId(res.getString("P_ID"));

                productsVbox.getChildren().add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void sortByProductName() {
        clearAllProducts();

        try {
            String q = "SELECT p.P_ID,p.P_NAME,p.P_PRICE,p.P_PURCHASE,pd.P_QUANTITY,pd.P_WEIGHT,pd.P_TYPE FROM PRODUCT p\n" +
                    "inner join [PRODUCT DETAILS] pd on p.P_ID = pd.P_ID and pd.P_AVAILABILITY='available'" +
                    "order by\n" +
                    "p.P_NAME asc";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("productRow.fxml"));
                Parent row = loader.load();

                productRowController prc = loader.getController();
                prc.productId.setText(res.getString("P_ID"));
                prc.productName.setText(res.getString("P_NAME"));
                prc.productType.setText(res.getString("P_TYPE").equals("W") ? "By Weight": "By Quantity");
                prc.unitPurchaseCost.setText(res.getString("P_PURCHASE"));
                prc.unitSellingCost.setText(res.getString("P_PRICE"));
                prc.weightQuantity.setText(res.getString("P_WEIGHT"));
                prc.availableAmount.setText(res.getString("P_QUANTITY"));
                prc.moreDetails.setId(res.getString("P_ID"));

                productsVbox.getChildren().add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void sortByProductId() {
        clearAllProducts();

        try {
            String q = "SELECT p.P_ID,p.P_NAME,p.P_PRICE,p.P_PURCHASE,pd.P_QUANTITY,pd.P_WEIGHT,pd.P_TYPE FROM PRODUCT p\n" +
                    "inner join [PRODUCT DETAILS] pd on p.P_ID = pd.P_ID and pd.P_AVAILABILITY='available'\n" +
                    "order by\n" +
                    "pd.P_ID asc\n";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("productRow.fxml"));
                Parent row = loader.load();

                productRowController prc = loader.getController();
                prc.productId.setText(res.getString("P_ID"));
                prc.productName.setText(res.getString("P_NAME"));
                prc.productType.setText(res.getString("P_TYPE").equals("W") ? "By Weight": "By Quantity");
                prc.unitPurchaseCost.setText(res.getString("P_PURCHASE"));
                prc.unitSellingCost.setText(res.getString("P_PRICE"));
                prc.weightQuantity.setText(res.getString("P_WEIGHT"));
                prc.availableAmount.setText(res.getString("P_QUANTITY"));
                prc.moreDetails.setId(res.getString("P_ID"));

                productsVbox.getChildren().add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void sortByAvailableAmount() {
        clearAllProducts();

        try {
            String q = "SELECT p.P_ID,p.P_NAME,p.P_PRICE,p.P_PURCHASE,pd.P_QUANTITY,pd.P_WEIGHT,pd.P_TYPE FROM PRODUCT p\n" +
                    "inner join [PRODUCT DETAILS] pd on p.P_ID = pd.P_ID and pd.P_AVAILABILITY='available'" +
                    "order by\n" +
                    "pd.P_QUANTITY asc";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("productRow.fxml"));
                Parent row = loader.load();

                productRowController prc = loader.getController();
                prc.productId.setText(res.getString("P_ID"));
                prc.productName.setText(res.getString("P_NAME"));
                prc.productType.setText(res.getString("P_TYPE").equals("W") ? "By Weight": "By Quantity");
                prc.unitPurchaseCost.setText(res.getString("P_PURCHASE"));
                prc.unitSellingCost.setText(res.getString("P_PRICE"));
                prc.weightQuantity.setText(res.getString("P_WEIGHT"));
                prc.availableAmount.setText(res.getString("P_QUANTITY"));
                prc.moreDetails.setId(res.getString("P_ID"));

                productsVbox.getChildren().add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void sortByProductType() {
        clearAllProducts();

        try {
            String q = "SELECT p.P_ID,p.P_NAME,p.P_PRICE,p.P_PURCHASE,pd.P_QUANTITY,pd.P_WEIGHT,pd.P_TYPE FROM PRODUCT p\n" +
                    "inner join [PRODUCT DETAILS] pd on p.P_ID = pd.P_ID and pd.P_AVAILABILITY='available'" +
                    "order by\n" +
                    "pd.P_TYPE asc";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("productRow.fxml"));
                Parent row = loader.load();

                productRowController prc = loader.getController();
                prc.productId.setText(res.getString("P_ID"));
                prc.productName.setText(res.getString("P_NAME"));
                prc.productType.setText(res.getString("P_TYPE").equals("W") ? "By Weight": "By Quantity");
                prc.unitPurchaseCost.setText(res.getString("P_PURCHASE"));
                prc.unitSellingCost.setText(res.getString("P_PRICE"));
                prc.weightQuantity.setText(res.getString("P_WEIGHT"));
                prc.availableAmount.setText(res.getString("P_QUANTITY"));
                prc.moreDetails.setId(res.getString("P_ID"));

                productsVbox.getChildren().add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void sortByWeightQuantity() {
        clearAllProducts();

        try {
            String q = "SELECT p.P_ID,p.P_NAME,p.P_PRICE,p.P_PURCHASE,pd.P_QUANTITY,pd.P_WEIGHT,pd.P_TYPE FROM PRODUCT p\n" +
                    "inner join [PRODUCT DETAILS] pd on p.P_ID = pd.P_ID and pd.P_AVAILABILITY='available'" +
                    "order by\n" +
                    "pd.P_WEIGHT asc";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("productRow.fxml"));
                Parent row = loader.load();

                productRowController prc = loader.getController();
                prc.productId.setText(res.getString("P_ID"));
                prc.productName.setText(res.getString("P_NAME"));
                prc.productType.setText(res.getString("P_TYPE").equals("W") ? "By Weight": "By Quantity");
                prc.unitPurchaseCost.setText(res.getString("P_PURCHASE"));
                prc.unitSellingCost.setText(res.getString("P_PRICE"));
                prc.weightQuantity.setText(res.getString("P_WEIGHT"));
                prc.availableAmount.setText(res.getString("P_QUANTITY"));
                prc.moreDetails.setId(res.getString("P_ID"));

                productsVbox.getChildren().add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void handleStatusController() {
        clearAllProducts();
        if (statusComboBox.getValue().equals("By Weight")) {

            try {
                String q = "SELECT p.P_ID,p.P_NAME,p.P_PRICE,p.P_PURCHASE,pd.P_QUANTITY,pd.P_WEIGHT,pd.P_TYPE FROM PRODUCT p\n" +
                        "inner join [PRODUCT DETAILS] pd on pd.P_ID=p.P_ID and pd.P_TYPE='W' and pd.P_AVAILABILITY='available'\n";
                ResultSet res = HelloApplication.statement.executeQuery(q);

                while (res.next()) {
                    FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("productRow.fxml"));
                    Parent row = loader.load();

                    productRowController prc = loader.getController();
                    prc.productId.setText(res.getString("P_ID"));
                    prc.productName.setText(res.getString("P_NAME"));
                    prc.productType.setText(res.getString("P_TYPE").equals("W") ? "By Weight": "By Quantity");
                    prc.unitPurchaseCost.setText(res.getString("P_PURCHASE"));
                    prc.unitSellingCost.setText(res.getString("P_PRICE"));
                    prc.weightQuantity.setText(res.getString("P_WEIGHT"));
                    prc.availableAmount.setText(res.getString("P_QUANTITY"));
                    prc.moreDetails.setId(res.getString("P_ID"));

                    productsVbox.getChildren().add(row);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (statusComboBox.getValue().equals("By Quantity")) {
            try {
                String q = "SELECT p.P_ID,p.P_NAME,p.P_PRICE,p.P_PURCHASE,pd.P_QUANTITY,pd.P_WEIGHT,pd.P_TYPE FROM PRODUCT p\n" +
                        "inner join [PRODUCT DETAILS] pd on pd.P_ID=p.P_ID and pd.P_TYPE='Q' and pd.P_AVAILABILITY='available'\n";
                ResultSet res = HelloApplication.statement.executeQuery(q);

                while (res.next()) {
                    FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("productRow.fxml"));
                    Parent row = loader.load();

                    productRowController prc = loader.getController();
                    prc.productId.setText(res.getString("P_ID"));
                    prc.productName.setText(res.getString("P_NAME"));
                    prc.productType.setText(res.getString("P_TYPE").equals("W") ? "By Weight": "By Quantity");
                    prc.unitPurchaseCost.setText(res.getString("P_PURCHASE"));
                    prc.unitSellingCost.setText(res.getString("P_PRICE"));
                    prc.weightQuantity.setText(res.getString("P_WEIGHT"));
                    prc.availableAmount.setText(res.getString("P_QUANTITY"));
                    prc.moreDetails.setId(res.getString("P_ID"));

                    productsVbox.getChildren().add(row);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                String q = "SELECT p.P_ID,p.P_NAME,p.P_PRICE,p.P_PURCHASE,pd.P_QUANTITY,pd.P_WEIGHT,pd.P_TYPE FROM PRODUCT p\n" +
                        "inner join [PRODUCT DETAILS] pd on p.P_ID = pd.P_ID and pd.P_AVAILABILITY='available'";
                ResultSet res = HelloApplication.statement.executeQuery(q);

                while (res.next()) {
                    FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("productRow.fxml"));
                    Parent row = loader.load();

                    productRowController prc = loader.getController();
                    prc.productId.setText(res.getString("P_ID"));
                    prc.productName.setText(res.getString("P_NAME"));
                    prc.productType.setText(res.getString("P_TYPE").equals("W") ? "By Weight": "By Quantity");
                    prc.unitPurchaseCost.setText(res.getString("P_PURCHASE"));
                    prc.unitSellingCost.setText(res.getString("P_PRICE"));
                    prc.weightQuantity.setText(res.getString("P_WEIGHT"));
                    prc.availableAmount.setText(res.getString("P_QUANTITY"));
                    prc.moreDetails.setId(res.getString("P_ID"));

                    productsVbox.getChildren().add(row);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void clearAllProducts() {
        productsVbox.getChildren().clear();
    }
    public void loadAllProducts() {
        try {
            String q = "SELECT p.P_ID,p.P_NAME,p.P_PRICE,p.P_PURCHASE,pd.P_QUANTITY,pd.P_WEIGHT,pd.P_TYPE FROM PRODUCT p\n" +
                    "inner join [PRODUCT DETAILS] pd on p.P_ID = pd.P_ID and pd.P_AVAILABILITY='available'\n";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("productRow.fxml"));
                Parent row = loader.load();

                productRowController prc = loader.getController();
                prc.productId.setText(res.getString("P_ID"));
                prc.productName.setText(res.getString("P_NAME"));
                prc.productType.setText(res.getString("P_TYPE").equals("W") ? "By Weight": "By Quantity");
                prc.unitPurchaseCost.setText(res.getString("P_PURCHASE"));
                prc.unitSellingCost.setText(res.getString("P_PRICE"));
                prc.weightQuantity.setText(res.getString("P_WEIGHT"));
                prc.availableAmount.setText(res.getString("P_QUANTITY"));
                prc.moreDetails.setId(res.getString("P_ID"));
                prc.productsVbox = productsVbox;
                prc.row = row;

                productsVbox.getChildren().add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void filterByQuantity() {
        clearAllProducts();
        try {
            String value = xquantity.getText();
            if (value.equals("")) {
                value = "999999999";
            }
            String q = "SELECT p.P_ID,p.P_NAME,p.P_PRICE,p.P_PURCHASE,pd.P_QUANTITY,pd.P_WEIGHT,pd.P_TYPE FROM PRODUCT p\n" +
                    "inner join [PRODUCT DETAILS] pd on p.P_ID = pd.P_ID and pd.P_AVAILABILITY='available' and pd.P_QUANTITY <=  " + value;
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("productRow.fxml"));
                Parent row = loader.load();

                productRowController prc = loader.getController();
                prc.productId.setText(res.getString("P_ID"));
                prc.productName.setText(res.getString("P_NAME"));
                prc.productType.setText(res.getString("P_TYPE").equals("W") ? "By Weight": "By Quantity");
                prc.unitPurchaseCost.setText(res.getString("P_PURCHASE"));
                prc.unitSellingCost.setText(res.getString("P_PRICE"));
                prc.weightQuantity.setText(res.getString("P_WEIGHT"));
                prc.availableAmount.setText(res.getString("P_QUANTITY"));
                prc.moreDetails.setId(res.getString("P_ID"));

                productsVbox.getChildren().add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void filterByPrice() {
        clearAllProducts();
        try {
            String value = xprice.getText();
            if (value.equals("")) {
                value = "999999999";
            }
            String q = "SELECT p.P_ID,p.P_NAME,p.P_PRICE,p.P_PURCHASE,pd.P_QUANTITY,pd.P_WEIGHT,pd.P_TYPE FROM PRODUCT p\n" +
                    "inner join [PRODUCT DETAILS] pd on p.P_ID = pd.P_ID and pd.P_AVAILABILITY='available' and p.P_PRICE <= " + value;
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("productRow.fxml"));
                Parent row = loader.load();

                productRowController prc = loader.getController();
                prc.productId.setText(res.getString("P_ID"));
                prc.productName.setText(res.getString("P_NAME"));
                prc.productType.setText(res.getString("P_TYPE").equals("W") ? "By Weight": "By Quantity");
                prc.unitPurchaseCost.setText(res.getString("P_PURCHASE"));
                prc.unitSellingCost.setText(res.getString("P_PRICE"));
                prc.weightQuantity.setText(res.getString("P_WEIGHT"));
                prc.availableAmount.setText(res.getString("P_QUANTITY"));
                prc.moreDetails.setId(res.getString("P_ID"));

                productsVbox.getChildren().add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void filterBySearch() {
        clearAllProducts();
        try {
            String value = search.getText();
            String q = "SELECT p.P_ID,p.P_NAME,p.P_PRICE,p.P_PURCHASE,pd.P_QUANTITY,pd.P_WEIGHT,pd.P_TYPE FROM PRODUCT p\n" +
                    "inner join [PRODUCT DETAILS] pd on p.P_ID = pd.P_ID and pd.P_AVAILABILITY='available' and p.P_NAME like '" + value + "%'";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("productRow.fxml"));
                Parent row = loader.load();

                productRowController prc = loader.getController();
                prc.productId.setText(res.getString("P_ID"));
                prc.productName.setText(res.getString("P_NAME"));
                prc.productType.setText(res.getString("P_TYPE").equals("W") ? "By Weight": "By Quantity");
                prc.unitPurchaseCost.setText(res.getString("P_PURCHASE"));
                prc.unitSellingCost.setText(res.getString("P_PRICE"));
                prc.weightQuantity.setText(res.getString("P_WEIGHT"));
                prc.availableAmount.setText(res.getString("P_QUANTITY"));
                prc.moreDetails.setId(res.getString("P_ID"));

                productsVbox.getChildren().add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
