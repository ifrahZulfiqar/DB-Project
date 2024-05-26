package com.example.dbproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.ResultSet;

public class productEditController {
    @FXML
    Text heading;
    @FXML
    TextField name, unitPrice, unitCost, weight, presetValue;
    Stage editStage;
    @FXML
    ComboBox<String> type;
    VBox productsVbox;
    String productId;
    @FXML
    public void initialize() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("By Weight");
        list.add("By Quantity");

        type.setItems(list);
    }
    @FXML
    public void handleSubmitChanges() {
        try {
            String q1 = "update PRODUCT set P_NAME='" + name.getText() + "', P_PURCHASE=" + unitCost.getText() + ", P_PRICE=" + unitPrice.getText() + ", P_WEIGHT=" + weight.getText() + ", P_TYPE=" + ((type.getValue() == null ? type.getPromptText() : type.getValue()).equals("By Weight") ? "'W'" : "'Q'") + " where P_NAME='" + name.getText() + "'";

            String test = "begin tran UPDATE_PRODUCT\n" +
                    "\n" +
                    "\tupdate PRODUCT\n" +
                    "\tset\tP_NAME='"+ name.getText() + "', P_PURCHASE="+ unitCost.getText() +", P_PRICE="+ unitPrice.getText() + "\n" +
                    "\twhere P_ID='"+ productId +"'\n" +
                    "\n" +
                    "\tupdate [PRODUCT DETAILS]\n" +
                    "\tset P_TYPE="+ ((type.getValue() == null ? type.getPromptText() : type.getValue()).equals("By Weight") ? "'W'" : "'Q'") +", P_WEIGHT="+ weight.getText() +", P_PRESET=null\n" +
                    "\twhere P_ID='"+ productId +"'\n" +
                    "\n" +
                    "commit";

            int row = HelloApplication.statement.executeUpdate(test);

            if (row == 1) {
                clearAllProducts();
                loadAllProducts();
                editStage.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
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
}
