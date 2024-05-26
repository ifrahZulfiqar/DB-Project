package com.example.dbproject;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.sql.ResultSet;
import java.time.LocalDate;

public class ordersController {
    @FXML
    VBox productsVbox;
    @FXML
    DatePicker from, to;
    @FXML
    TextField quickSearch, customerName;
    @FXML
    public void initialize() {
        PauseTransition pt = new PauseTransition(Duration.seconds(1));
        quickSearch.textProperty().addListener(
            (observable, oldValue, newValue) ->  {
                pt.setOnFinished(e -> {
                    searchByOrderId();
                });
                pt.playFromStart();
            }
        );
        customerName.textProperty().addListener(
                (observable, oldValue, newValue) ->  {
                    pt.setOnFinished(e -> {
                        searchByCustomerName();
                    });
                    pt.playFromStart();
                }
        );

        try {

            String q = "select O_ID, O_DISCOUNT, O_TIME,\n" +
                    "(select C_NAME from [CUSTOMER] c where c.C_ID=o.C_ID) as C_NAME,\n" +
                    "(select SUM(PO_PRICE) from [PRO_ORD] po where po.O_ID=o.O_ID) as [Total Price],\n" +
                    "(select COUNT(PO_QUANTITY) from [PRO_ORD] po where po.O_ID=o.O_ID) as [Total Products],\n" +
                    "(select E_NAME from EMPLOYEE e where e.E_ID=o.E_ID) as [E_NAME]\n" +
                    "from [ORDER] o";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                FXMLLoader row = new FXMLLoader(HelloApplication.class.getResource("orderRow.fxml"));
                Parent prow = row.load();

                orderRowController orc = row.getController();
                orc.oid.setText(res.getString("O_ID"));
                orc.cname.setText(res.getString("C_NAME"));
                orc.totalPrice.setText(res.getString("Total Price"));
                orc.totalProducts.setText(res.getString("Total Products"));
                orc.discount.setText(res.getString("O_DISCOUNT"));
                orc.date.setText(res.getString("O_TIME"));
                orc.employee.setText(res.getString("E_NAME"));
                orc.moreDetails.setId(res.getString("O_ID"));

                productsVbox.getChildren().add(prow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void searchByCustomerName() {
        removeAllProducts();
        try {
            String q = "select O_ID, O_DISCOUNT, O_TIME,\n" +
                    "(select C_NAME from [CUSTOMER] c where c.C_ID=o.C_ID) as C_NAME,\n" +
                    "(select SUM(PO_PRICE) from [PRO_ORD] po where po.O_ID=o.O_ID) as [Total Price],\n" +
                    "(select COUNT(PO_QUANTITY) from [PRO_ORD] po where po.O_ID=o.O_ID) as [Total Products],\n" +
                    "(select E_NAME from EMPLOYEE e where e.E_ID=o.E_ID) as [E_NAME]\n" +
                    "from [ORDER] o where '" + customerName.getText() + "' like \n" +
                    "(select C_NAME from [CUSTOMER] c where c.C_ID=o.C_ID)";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                FXMLLoader row = new FXMLLoader(HelloApplication.class.getResource("orderRow.fxml"));
                Parent prow = row.load();

                orderRowController orc = row.getController();
                orc.oid.setText(res.getString("O_ID"));
                orc.cname.setText(res.getString("C_NAME"));
                orc.totalPrice.setText(res.getString("Total Price"));
                orc.totalProducts.setText(res.getString("Total Products"));
                orc.discount.setText(res.getString("O_DISCOUNT"));
                orc.date.setText(res.getString("O_TIME"));
                orc.employee.setText(res.getString("E_NAME"));
                orc.moreDetails.setId(res.getString("O_ID"));

                productsVbox.getChildren().add(prow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void searchByOrderId() {
        removeAllProducts();
        try {
            String q = "select O_ID, O_DISCOUNT, O_TIME,\n" +
                    "(select C_NAME from [CUSTOMER] c where c.C_ID=o.C_ID) as C_NAME,\n" +
                    "(select SUM(PO_PRICE) from [PRO_ORD] po where po.O_ID=o.O_ID) as [Total Price],\n" +
                    "(select COUNT(PO_QUANTITY) from [PRO_ORD] po where po.O_ID=o.O_ID) as [Total Products],\n" +
                    "(select E_NAME from EMPLOYEE e where e.E_ID=o.E_ID) as [E_NAME]\n" +
                    "from [ORDER] o where O_ID='" + quickSearch.getText() + "'";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                FXMLLoader row = new FXMLLoader(HelloApplication.class.getResource("orderRow.fxml"));
                Parent prow = row.load();

                orderRowController orc = row.getController();
                orc.oid.setText(res.getString("O_ID"));
                orc.cname.setText(res.getString("C_NAME"));
                orc.totalPrice.setText(res.getString("Total Price"));
                orc.totalProducts.setText(res.getString("Total Products"));
                orc.discount.setText(res.getString("O_DISCOUNT"));
                orc.date.setText(res.getString("O_TIME"));
                orc.employee.setText(res.getString("E_NAME"));
                orc.moreDetails.setId(res.getString("O_ID"));

                productsVbox.getChildren().add(prow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void sortByOrderId() {
        removeAllProducts();
        try {

            String q = "select O_ID, O_DISCOUNT, O_TIME,\n" +
                    "(select C_NAME from [CUSTOMER] c where c.C_ID=o.C_ID) as C_NAME,\n" +
                    "(select SUM(PO_PRICE) from [PRO_ORD] po where po.O_ID=o.O_ID) as [Total Price],\n" +
                    "(select COUNT(PO_QUANTITY) from [PRO_ORD] po where po.O_ID=o.O_ID) as [Total Products],\n" +
                    "(select E_NAME from EMPLOYEE e where e.E_ID=o.E_ID) as [E_NAME]\n" +
                    "from [ORDER] o order by [O_ID]";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                FXMLLoader row = new FXMLLoader(HelloApplication.class.getResource("orderRow.fxml"));
                Parent prow = row.load();

                orderRowController orc = row.getController();
                orc.oid.setText(res.getString("O_ID"));
                orc.cname.setText(res.getString("C_NAME"));
                orc.totalPrice.setText(res.getString("Total Price"));
                orc.totalProducts.setText(res.getString("Total Products"));
                orc.discount.setText(res.getString("O_DISCOUNT"));
                orc.date.setText(res.getString("O_TIME"));
                orc.employee.setText(res.getString("E_NAME"));
                orc.moreDetails.setId(res.getString("O_ID"));

                productsVbox.getChildren().add(prow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void sortByCustomerName() {
        removeAllProducts();
        try {

            String q = "select O_ID, O_DISCOUNT, O_TIME,\n" +
                    "(select C_NAME from [CUSTOMER] c where c.C_ID=o.C_ID) as C_NAME,\n" +
                    "(select SUM(PO_PRICE) from [PRO_ORD] po where po.O_ID=o.O_ID) as [Total Price],\n" +
                    "(select COUNT(PO_QUANTITY) from [PRO_ORD] po where po.O_ID=o.O_ID) as [Total Products],\n" +
                    "(select E_NAME from EMPLOYEE e where e.E_ID=o.E_ID) as [E_NAME]\n" +
                    "from [ORDER] o order by [C_NAME]";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                FXMLLoader row = new FXMLLoader(HelloApplication.class.getResource("orderRow.fxml"));
                Parent prow = row.load();

                orderRowController orc = row.getController();
                orc.oid.setText(res.getString("O_ID"));
                orc.cname.setText(res.getString("C_NAME"));
                orc.totalPrice.setText(res.getString("Total Price"));
                orc.totalProducts.setText(res.getString("Total Products"));
                orc.discount.setText(res.getString("O_DISCOUNT"));
                orc.date.setText(res.getString("O_TIME"));
                orc.employee.setText(res.getString("E_NAME"));
                orc.moreDetails.setId(res.getString("O_ID"));

                productsVbox.getChildren().add(prow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void sortByTotalPrice() {
        removeAllProducts();
        try {

            String q = "select O_ID, O_DISCOUNT, O_TIME,\n" +
                    "(select C_NAME from [CUSTOMER] c where c.C_ID=o.C_ID) as C_NAME,\n" +
                    "(select SUM(PO_PRICE) from [PRO_ORD] po where po.O_ID=o.O_ID) as [Total Price],\n" +
                    "(select COUNT(PO_QUANTITY) from [PRO_ORD] po where po.O_ID=o.O_ID) as [Total Products],\n" +
                    "(select E_NAME from EMPLOYEE e where e.E_ID=o.E_ID) as [E_NAME]\n" +
                    "from [ORDER] o order by [Total Price]";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                FXMLLoader row = new FXMLLoader(HelloApplication.class.getResource("orderRow.fxml"));
                Parent prow = row.load();

                orderRowController orc = row.getController();
                orc.oid.setText(res.getString("O_ID"));
                orc.cname.setText(res.getString("C_NAME"));
                orc.totalPrice.setText(res.getString("Total Price"));
                orc.totalProducts.setText(res.getString("Total Products"));
                orc.discount.setText(res.getString("O_DISCOUNT"));
                orc.date.setText(res.getString("O_TIME"));
                orc.employee.setText(res.getString("E_NAME"));
                orc.moreDetails.setId(res.getString("O_ID"));

                productsVbox.getChildren().add(prow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void sortByTotalProducts() {
        removeAllProducts();
        try {

            String q = "select O_ID, O_DISCOUNT, O_TIME,\n" +
                    "(select C_NAME from [CUSTOMER] c where c.C_ID=o.C_ID) as C_NAME,\n" +
                    "(select SUM(PO_PRICE) from [PRO_ORD] po where po.O_ID=o.O_ID) as [Total Price],\n" +
                    "(select COUNT(PO_QUANTITY) from [PRO_ORD] po where po.O_ID=o.O_ID) as [Total Products],\n" +
                    "(select E_NAME from EMPLOYEE e where e.E_ID=o.E_ID) as [E_NAME]\n" +
                    "from [ORDER] o order by [Total Products]";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                FXMLLoader row = new FXMLLoader(HelloApplication.class.getResource("orderRow.fxml"));
                Parent prow = row.load();

                orderRowController orc = row.getController();
                orc.oid.setText(res.getString("O_ID"));
                orc.cname.setText(res.getString("C_NAME"));
                orc.totalPrice.setText(res.getString("Total Price"));
                orc.totalProducts.setText(res.getString("Total Products"));
                orc.discount.setText(res.getString("O_DISCOUNT"));
                orc.date.setText(res.getString("O_TIME"));
                orc.employee.setText(res.getString("E_NAME"));
                orc.moreDetails.setId(res.getString("O_ID"));

                productsVbox.getChildren().add(prow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void sortByDiscount() {
        removeAllProducts();
        try {

            String q = "select O_ID, O_DISCOUNT, O_TIME,\n" +
                    "(select C_NAME from [CUSTOMER] c where c.C_ID=o.C_ID) as C_NAME,\n" +
                    "(select SUM(PO_PRICE) from [PRO_ORD] po where po.O_ID=o.O_ID) as [Total Price],\n" +
                    "(select COUNT(PO_QUANTITY) from [PRO_ORD] po where po.O_ID=o.O_ID) as [Total Products],\n" +
                    "(select E_NAME from EMPLOYEE e where e.E_ID=o.E_ID) as [E_NAME]\n" +
                    "from [ORDER] o order by [O_DISCOUNT]";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                FXMLLoader row = new FXMLLoader(HelloApplication.class.getResource("orderRow.fxml"));
                Parent prow = row.load();

                orderRowController orc = row.getController();
                orc.oid.setText(res.getString("O_ID"));
                orc.cname.setText(res.getString("C_NAME"));
                orc.totalPrice.setText(res.getString("Total Price"));
                orc.totalProducts.setText(res.getString("Total Products"));
                orc.discount.setText(res.getString("O_DISCOUNT"));
                orc.date.setText(res.getString("O_TIME"));
                orc.employee.setText(res.getString("E_NAME"));
                orc.moreDetails.setId(res.getString("O_ID"));

                productsVbox.getChildren().add(prow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void sortByDate() {
        removeAllProducts();
        try {

            String q = "select O_ID, O_DISCOUNT, O_TIME,\n" +
                    "(select C_NAME from [CUSTOMER] c where c.C_ID=o.C_ID) as C_NAME,\n" +
                    "(select SUM(PO_PRICE) from [PRO_ORD] po where po.O_ID=o.O_ID) as [Total Price],\n" +
                    "(select COUNT(PO_QUANTITY) from [PRO_ORD] po where po.O_ID=o.O_ID) as [Total Products],\n" +
                    "(select E_NAME from EMPLOYEE e where e.E_ID=o.E_ID) as [E_NAME]\n" +
                    "from [ORDER] o order by [O_TIME]";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                FXMLLoader row = new FXMLLoader(HelloApplication.class.getResource("orderRow.fxml"));
                Parent prow = row.load();

                orderRowController orc = row.getController();
                orc.oid.setText(res.getString("O_ID"));
                orc.cname.setText(res.getString("C_NAME"));
                orc.totalPrice.setText(res.getString("Total Price"));
                orc.totalProducts.setText(res.getString("Total Products"));
                orc.discount.setText(res.getString("O_DISCOUNT"));
                orc.date.setText(res.getString("O_TIME"));
                orc.employee.setText(res.getString("E_NAME"));
                orc.moreDetails.setId(res.getString("O_ID"));

                productsVbox.getChildren().add(prow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void sortByEmployee() {
        removeAllProducts();
        try {

            String q = "select O_ID, O_DISCOUNT, O_TIME,\n" +
                    "(select C_NAME from [CUSTOMER] c where c.C_ID=o.C_ID) as C_NAME,\n" +
                    "(select SUM(PO_PRICE) from [PRO_ORD] po where po.O_ID=o.O_ID) as [Total Price],\n" +
                    "(select COUNT(PO_QUANTITY) from [PRO_ORD] po where po.O_ID=o.O_ID) as [Total Products],\n" +
                    "(select E_NAME from EMPLOYEE e where e.E_ID=o.E_ID) as [E_NAME]\n" +
                    "from [ORDER] o order by [E_NAME]";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                FXMLLoader row = new FXMLLoader(HelloApplication.class.getResource("orderRow.fxml"));
                Parent prow = row.load();

                orderRowController orc = row.getController();
                orc.oid.setText(res.getString("O_ID"));
                orc.cname.setText(res.getString("C_NAME"));
                orc.totalPrice.setText(res.getString("Total Price"));
                orc.totalProducts.setText(res.getString("Total Products"));
                orc.discount.setText(res.getString("O_DISCOUNT"));
                orc.date.setText(res.getString("O_TIME"));
                orc.employee.setText(res.getString("E_NAME"));
                orc.moreDetails.setId(res.getString("O_ID"));

                productsVbox.getChildren().add(prow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void handleGet() {
        removeAllProducts();
        try {
            LocalDate fromDate = from.getValue();
            LocalDate toDate = to.getValue();

            String q = "select O_ID, O_DISCOUNT, O_TIME,\n" +
                    "(select C_NAME from [CUSTOMER] c where c.C_ID=o.C_ID) as C_NAME,\n" +
                    "(select SUM(PO_PRICE) from [PRO_ORD] po where po.O_ID=o.O_ID) as [Total Price],\n" +
                    "(select COUNT(PO_QUANTITY) from [PRO_ORD] po where po.O_ID=o.O_ID) as [Total Products],\n" +
                    "(select E_NAME from EMPLOYEE e where e.E_ID=o.E_ID) as [E_NAME]\n" +
                    "from [ORDER] o where \n" +
                    "YEAR(O_TIME) >= "+ fromDate.getYear() +" and YEAR(O_TIME) <= "+toDate.getYear()+" and\n" +
                    "MONTH(O_TIME) >= "+fromDate.getMonthValue()+" and MONTH(O_TIME) <= "+toDate.getMonthValue()+" and\n" +
                    "DAY(O_TIME) >= "+fromDate.getDayOfMonth()+" and DAY(O_TIME) <= " + toDate.getDayOfMonth();
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                FXMLLoader row = new FXMLLoader(HelloApplication.class.getResource("orderRow.fxml"));
                Parent prow = row.load();

                orderRowController orc = row.getController();
                orc.oid.setText(res.getString("O_ID"));
                orc.cname.setText(res.getString("C_NAME"));
                orc.totalPrice.setText(res.getString("Total Price"));
                orc.totalProducts.setText(res.getString("Total Products"));
                orc.discount.setText(res.getString("O_DISCOUNT"));
                orc.date.setText(res.getString("O_TIME"));
                orc.employee.setText(res.getString("E_NAME"));
                orc.moreDetails.setId(res.getString("O_ID"));

                productsVbox.getChildren().add(prow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void removeAllProducts() {
        productsVbox.getChildren().clear();
    }
}
