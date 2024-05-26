package com.example.dbproject;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class homeController {
    @FXML
    ComboBox<String> statusComboBox;
    @FXML
    public Button orders, inStock, sale, purchaseBill, pRecords;
    @FXML
    BorderPane homeBorderPane = new BorderPane();
    @FXML
    VBox productsVbox;
    @FXML
    Text name, bestPerformingProduct, totalRevenue, totalProfit;
    @FXML
    public void initialize() throws IOException {

        try {
            String q = "SELECT top 1 sub.PO_PRICE, sub.PO_QUANTITY, sub.PO_PURCHASE, (sub.PO_PRICE - sub.PO_PURCHASE) as PROFIT,\n" +
                    "(select P_NAME from PRODUCT p where p.P_ID=sub.P_ID) as P_NAME\n" +
                    "FROM (\n" +
                    "    SELECT SUM(PO_QUANTITY) AS PO_QUANTITY, sum(PO_PRICE) as PO_PRICE,\n" +
                    "\tsum(PO_PURCHASE * PO_QUANTITY) as PO_PURCHASE,  P_ID\n" +
                    "    FROM [PRO_ORD] po where MONTH(SYSDATETIME()) = \n" +
                    "\t(select MONTH(O_TIME) from [ORDER] o where o.O_ID=po.O_ID)\n" +
                    "    GROUP BY P_ID\n" +
                    ") as sub order by PO_PRICE desc\n";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                bestPerformingProduct.setText(res.getString("P_NAME"));
                totalRevenue.setText(res.getString("PO_PRICE"));
                totalProfit.setText(res.getString("PROFIT"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        switch (HelloApplication.employee.employeeLevels) {
            case WORKER -> {
                purchaseBill.setVisible(false);
                orders.setVisible(false);
            }
            case CASHIER -> {

            }
        }

        name.setText("Welcome Back, " + HelloApplication.employee.name);
        statusComboBox = new ComboBox<String>();
        statusComboBox.getItems().removeAll(statusComboBox.getItems());
        statusComboBox.getItems().addAll("completed", "payment due");
        statusComboBox.setStyle("-fx-prompt-text-fill: #fff");

        homeBorderPane.setCenter(new FXMLLoader(HelloApplication.class.getResource("orders.fxml")).load());
    }
    @FXML
    protected void handleOption(ActionEvent e) throws IOException {
        String opt = ((Button)e.getSource()).getId();
        switch (opt) {
            case "orders":
                homeBorderPane.setCenter(new FXMLLoader(HelloApplication.class.getResource("orders.fxml")).load());
                break;
            case "inStock":
                homeBorderPane.setCenter(new FXMLLoader(HelloApplication.class.getResource("products.fxml")).load());
                break;
            case "sale":
                homeBorderPane.setCenter(new FXMLLoader(HelloApplication.class.getResource("placeOrder.fxml")).load());
                break;
            case "purchaseBill":
                homeBorderPane.setCenter(new FXMLLoader(HelloApplication.class.getResource("addProduct.fxml")).load());
                break;
            case "pRecords":
                homeBorderPane.setCenter(new FXMLLoader(HelloApplication.class.getResource("purchaseRecords.fxml")).load());
                break;
        }
    }
}
