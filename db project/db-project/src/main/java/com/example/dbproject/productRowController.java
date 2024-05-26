package com.example.dbproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;

public class productRowController {
    @FXML
    Text productId, productName, unitPurchaseCost, unitSellingCost, availableAmount, weightQuantity, productType;
    @FXML
    Button moreDetails, delete, edit;
    VBox productsVbox;
    Parent row;
    String presetValue = "";
    @FXML
    protected void handleMoreDetails(ActionEvent e) throws IOException {
        String pid = ((Button)e.getSource()).getId();

        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("productMoreDetails.fxml"));
        Parent page = loader.load();
        String stageTitle = "";

        productMoreDetailsController pmdc = loader.getController();

        try {
            String q = "select * from PRODUCT p where p.P_ID='" + pid + "'";
            ResultSet res = HelloApplication.statement.executeQuery(q);
            res.next();

            pmdc.productId.setText(res.getString("P_ID"));
            pmdc.productName.setText(res.getString("P_NAME"));
            pmdc.productNameHeading.setText(res.getString("P_NAME"));
            pmdc.availableQuantity.setText(res.getString("P_QUANTITY"));
            pmdc.unitPurchaseCost.setText(res.getString("P_PURCHASE"));
            pmdc.unitSellingCost.setText(res.getString("P_PRICE"));
            pmdc.productSoldBy.setText(res.getString("P_TYPE").equals("W") ? "By Weight": "By Quantity");
            pmdc.supplier.setText(res.getString("P_ID"));
            pmdc.supplierPhone.setText(res.getString("P_ID"));
            pmdc.timesSold.setText(res.getString("P_ID"));
            pmdc.revenueGenerated.setText(res.getString("P_ID"));

            stageTitle = res.getString("P_NAME");

        } catch (Exception er) {
            er.printStackTrace();
        }


        Stage moreDetailsStage = new Stage();
        moreDetailsStage.setScene(new Scene(page));
        moreDetailsStage.setTitle(stageTitle);
        moreDetailsStage.show();
    }
    @FXML
    protected void handleDelete() {
        try {
            String q = "UPDATE [PRODUCT DETAILS] set P_AVAILABILITY='deleted' WHERE P_ID='" + productId.getText() + "'";
            int res = HelloApplication.statement.executeUpdate(q);

            System.out.println(res);

        } catch (Exception e) {
            e.printStackTrace();
        }
        productsVbox.getChildren().remove(row);
    }
    @FXML
    protected void handleEdit() throws IOException {
        Stage editStage = new Stage();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("productEdit.fxml"));
        Parent box = loader.load();

        productEditController pec = loader.getController();
        pec.name.setText(productName.getText());
        pec.unitPrice.setText(unitSellingCost.getText());
        pec.unitCost.setText(unitPurchaseCost.getText());
        pec.type.setPromptText(productType.getText());
        pec.weight.setText(weightQuantity.getText());
        pec.presetValue.setText(presetValue);
        pec.heading.setText(productName.getText());
        pec.editStage = editStage;
        pec.productsVbox = productsVbox;
        pec.productId = productId.getText();

        editStage.setScene(new Scene(box));
        editStage.setResizable(false);
        editStage.show();
    }
}
