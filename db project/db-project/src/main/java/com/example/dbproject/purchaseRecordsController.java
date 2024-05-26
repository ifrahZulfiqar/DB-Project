package com.example.dbproject;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.sql.ResultSet;
import java.time.LocalDate;

public class purchaseRecordsController {
    @FXML
    VBox purchaseVbox;
    @FXML
    ComboBox<String> statusComboBox;
    @FXML
    DatePicker from, to;
    @FXML
    Button get;
    @FXML
    TextField xquantity, search;
    @FXML
    public void initialize() {
        loadAllPurchases();

        // adding values in combo box
        ObservableList<String> status = FXCollections.observableArrayList();
        status.add("By Cash");
        status.add("By Card");
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
    protected void sortByPurchaseId() {
        clearAllProducts();

        try {
            String q = "select PUR_ID, PUR_METHOD, PUR_TIME,\n" +
                    "(select S_NAME from SUPPLIER s where s.S_ID=p.S_ID) as S_NAME,\n" +
                    "(select E_NAME from EMPLOYEE e where e.E_ID=p.E_ID) as E_NAME,\n" +
                    "(select count(P_ID) from [PRO_PUR] pp where pp.PUR_ID=p.PUR_ID) as [Total Products],\n" +
                    "(select sum(PUR_PRICE * PUR_QUANTITY) from [PRO_PUR] pp where pp.PUR_ID=p.PUR_ID) as [Total Amount]\n" +
                    "from PURCHASE p order by p.PUR_ID";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("purchaseRecordsRow.fxml"));
                Parent row = loader.load();

                purchaseRecordsRowController prc = loader.getController();
                prc.purchaseId.setText(res.getString("PUR_ID"));
                prc.totalProducts.setText(res.getString("Total Products"));
                prc.supplierName.setText(res.getString("S_NAME"));
                prc.paymentMethod.setText(res.getString("PUR_METHOD"));
                prc.employee.setText(res.getString("E_NAME"));
                prc.totalAmount.setText(res.getString("Total Amount"));
                prc.date.setText(res.getString("PUR_TIME"));
                prc.moreDetails.setId(res.getString("PUR_ID"));

                purchaseVbox.getChildren().add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void sortBySupplierName() {
        clearAllProducts();

        try {
            String q = "select PUR_ID, PUR_METHOD, PUR_TIME,\n" +
                    "(select S_NAME from SUPPLIER s where s.S_ID=p.S_ID) as S_NAME,\n" +
                    "(select E_NAME from EMPLOYEE e where e.E_ID=p.E_ID) as E_NAME,\n" +
                    "(select count(P_ID) from [PRO_PUR] pp where pp.PUR_ID=p.PUR_ID) as [Total Products],\n" +
                    "(select sum(PUR_PRICE * PUR_QUANTITY) from [PRO_PUR] pp where pp.PUR_ID=p.PUR_ID) as [Total Amount]\n" +
                    "from PURCHASE p order by S_NAME";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("purchaseRecordsRow.fxml"));
                Parent row = loader.load();

                purchaseRecordsRowController prc = loader.getController();
                prc.purchaseId.setText(res.getString("PUR_ID"));
                prc.totalProducts.setText(res.getString("Total Products"));
                prc.supplierName.setText(res.getString("S_NAME"));
                prc.paymentMethod.setText(res.getString("PUR_METHOD"));
                prc.employee.setText(res.getString("E_NAME"));
                prc.totalAmount.setText(res.getString("Total Amount"));
                prc.date.setText(res.getString("PUR_TIME"));
                prc.moreDetails.setId(res.getString("PUR_ID"));

                purchaseVbox.getChildren().add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void sortByTotalProducts() {
        clearAllProducts();

        try {
            String q = "select PUR_ID, PUR_METHOD, PUR_TIME,\n" +
                    "(select S_NAME from SUPPLIER s where s.S_ID=p.S_ID) as S_NAME,\n" +
                    "(select E_NAME from EMPLOYEE e where e.E_ID=p.E_ID) as E_NAME,\n" +
                    "(select count(P_ID) from [PRO_PUR] pp where pp.PUR_ID=p.PUR_ID) as [Total Products],\n" +
                    "(select sum(PUR_PRICE * PUR_QUANTITY) from [PRO_PUR] pp where pp.PUR_ID=p.PUR_ID) as [Total Amount]\n" +
                    "from PURCHASE p order by [Total Products] desc";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("purchaseRecordsRow.fxml"));
                Parent row = loader.load();

                purchaseRecordsRowController prc = loader.getController();
                prc.purchaseId.setText(res.getString("PUR_ID"));
                prc.totalProducts.setText(res.getString("Total Products"));
                prc.supplierName.setText(res.getString("S_NAME"));
                prc.paymentMethod.setText(res.getString("PUR_METHOD"));
                prc.employee.setText(res.getString("E_NAME"));
                prc.totalAmount.setText(res.getString("Total Amount"));
                prc.date.setText(res.getString("PUR_TIME"));
                prc.moreDetails.setId(res.getString("PUR_ID"));

                purchaseVbox.getChildren().add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void sortByPaymentMethod() {
        clearAllProducts();

        try {
            String q = "select PUR_ID, PUR_METHOD, PUR_TIME,\n" +
                    "(select S_NAME from SUPPLIER s where s.S_ID=p.S_ID) as S_NAME,\n" +
                    "(select E_NAME from EMPLOYEE e where e.E_ID=p.E_ID) as E_NAME,\n" +
                    "(select count(P_ID) from [PRO_PUR] pp where pp.PUR_ID=p.PUR_ID) as [Total Products],\n" +
                    "(select sum(PUR_PRICE * PUR_QUANTITY) from [PRO_PUR] pp where pp.PUR_ID=p.PUR_ID) as [Total Amount]\n" +
                    "from PURCHASE p order by PUR_METHOD";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("purchaseRecordsRow.fxml"));
                Parent row = loader.load();

                purchaseRecordsRowController prc = loader.getController();
                prc.purchaseId.setText(res.getString("PUR_ID"));
                prc.totalProducts.setText(res.getString("Total Products"));
                prc.supplierName.setText(res.getString("S_NAME"));
                prc.paymentMethod.setText(res.getString("PUR_METHOD"));
                prc.employee.setText(res.getString("E_NAME"));
                prc.totalAmount.setText(res.getString("Total Amount"));
                prc.date.setText(res.getString("PUR_TIME"));
                prc.moreDetails.setId(res.getString("PUR_ID"));

                purchaseVbox.getChildren().add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void sortByEmployee() {
        clearAllProducts();

        try {
            String q = "select PUR_ID, PUR_METHOD, PUR_TIME,\n" +
                    "(select S_NAME from SUPPLIER s where s.S_ID=p.S_ID) as S_NAME,\n" +
                    "(select E_NAME from EMPLOYEE e where e.E_ID=p.E_ID) as E_NAME,\n" +
                    "(select count(P_ID) from [PRO_PUR] pp where pp.PUR_ID=p.PUR_ID) as [Total Products],\n" +
                    "(select sum(PUR_PRICE * PUR_QUANTITY) from [PRO_PUR] pp where pp.PUR_ID=p.PUR_ID) as [Total Amount]\n" +
                    "from PURCHASE p order by E_NAME";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("purchaseRecordsRow.fxml"));
                Parent row = loader.load();

                purchaseRecordsRowController prc = loader.getController();
                prc.purchaseId.setText(res.getString("PUR_ID"));
                prc.totalProducts.setText(res.getString("Total Products"));
                prc.supplierName.setText(res.getString("S_NAME"));
                prc.paymentMethod.setText(res.getString("PUR_METHOD"));
                prc.employee.setText(res.getString("E_NAME"));
                prc.totalAmount.setText(res.getString("Total Amount"));
                prc.date.setText(res.getString("PUR_TIME"));
                prc.moreDetails.setId(res.getString("PUR_ID"));

                purchaseVbox.getChildren().add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void sortByTotalAmount() {
        clearAllProducts();

        try {
            String q = "select PUR_ID, PUR_METHOD, PUR_TIME,\n" +
                    "(select S_NAME from SUPPLIER s where s.S_ID=p.S_ID) as S_NAME,\n" +
                    "(select E_NAME from EMPLOYEE e where e.E_ID=p.E_ID) as E_NAME,\n" +
                    "(select count(P_ID) from [PRO_PUR] pp where pp.PUR_ID=p.PUR_ID) as [Total Products],\n" +
                    "(select sum(PUR_PRICE * PUR_QUANTITY) from [PRO_PUR] pp where pp.PUR_ID=p.PUR_ID) as [Total Amount]\n" +
                    "from PURCHASE p order by [Total Amount] desc";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("purchaseRecordsRow.fxml"));
                Parent row = loader.load();

                purchaseRecordsRowController prc = loader.getController();
                prc.purchaseId.setText(res.getString("PUR_ID"));
                prc.totalProducts.setText(res.getString("Total Products"));
                prc.supplierName.setText(res.getString("S_NAME"));
                prc.paymentMethod.setText(res.getString("PUR_METHOD"));
                prc.employee.setText(res.getString("E_NAME"));
                prc.totalAmount.setText(res.getString("Total Amount"));
                prc.date.setText(res.getString("PUR_TIME"));
                prc.moreDetails.setId(res.getString("PUR_ID"));

                purchaseVbox.getChildren().add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void sortByDate() {
        clearAllProducts();

        try {
            String q = "select PUR_ID, PUR_METHOD, PUR_TIME,\n" +
                    "(select S_NAME from SUPPLIER s where s.S_ID=p.S_ID) as S_NAME,\n" +
                    "(select E_NAME from EMPLOYEE e where e.E_ID=p.E_ID) as E_NAME,\n" +
                    "(select count(P_ID) from [PRO_PUR] pp where pp.PUR_ID=p.PUR_ID) as [Total Products],\n" +
                    "(select sum(PUR_PRICE * PUR_QUANTITY) from [PRO_PUR] pp where pp.PUR_ID=p.PUR_ID) as [Total Amount]\n" +
                    "from PURCHASE p order by PUR_TIME";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("purchaseRecordsRow.fxml"));
                Parent row = loader.load();

                purchaseRecordsRowController prc = loader.getController();
                prc.purchaseId.setText(res.getString("PUR_ID"));
                prc.totalProducts.setText(res.getString("Total Products"));
                prc.supplierName.setText(res.getString("S_NAME"));
                prc.paymentMethod.setText(res.getString("PUR_METHOD"));
                prc.employee.setText(res.getString("E_NAME"));
                prc.totalAmount.setText(res.getString("Total Amount"));
                prc.date.setText(res.getString("PUR_TIME"));
                prc.moreDetails.setId(res.getString("PUR_ID"));

                purchaseVbox.getChildren().add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void handleStatusController() {
        clearAllProducts();
        if (statusComboBox.getValue().equals("By Cash")) {

            try {
                String q = "select PUR_ID, PUR_METHOD, PUR_TIME,\n" +
                        "(select S_NAME from SUPPLIER s where s.S_ID=p.S_ID) as S_NAME,\n" +
                        "(select E_NAME from EMPLOYEE e where e.E_ID=p.E_ID) as E_NAME,\n" +
                        "(select count(P_ID) from [PRO_PUR] pp where pp.PUR_ID=p.PUR_ID) as [Total Products],\n" +
                        "(select sum(PUR_PRICE * PUR_QUANTITY) from [PRO_PUR] pp where pp.PUR_ID=p.PUR_ID) as [Total Amount]\n" +
                        "from PURCHASE p where PUR_METHOD='cash'\n";
                ResultSet res = HelloApplication.statement.executeQuery(q);

                while (res.next()) {
                    FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("purchaseRecordsRow.fxml"));
                    Parent row = loader.load();

                    purchaseRecordsRowController prc = loader.getController();
                    prc.purchaseId.setText(res.getString("PUR_ID"));
                    prc.totalProducts.setText(res.getString("Total Products"));
                    prc.supplierName.setText(res.getString("S_NAME"));
                    prc.paymentMethod.setText(res.getString("PUR_METHOD"));
                    prc.employee.setText(res.getString("E_NAME"));
                    prc.totalAmount.setText(res.getString("Total Amount"));
                    prc.date.setText(res.getString("PUR_TIME"));
                    prc.moreDetails.setId(res.getString("PUR_ID"));

                    purchaseVbox.getChildren().add(row);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (statusComboBox.getValue().equals("By Card")) {
            try {
                String q = "select PUR_ID, PUR_METHOD, PUR_TIME,\n" +
                        "(select S_NAME from SUPPLIER s where s.S_ID=p.S_ID) as S_NAME,\n" +
                        "(select E_NAME from EMPLOYEE e where e.E_ID=p.E_ID) as E_NAME,\n" +
                        "(select count(P_ID) from [PRO_PUR] pp where pp.PUR_ID=p.PUR_ID) as [Total Products],\n" +
                        "(select sum(PUR_PRICE * PUR_QUANTITY) from [PRO_PUR] pp where pp.PUR_ID=p.PUR_ID) as [Total Amount]\n" +
                        "from PURCHASE p where PUR_METHOD='card'\n";
                ResultSet res = HelloApplication.statement.executeQuery(q);

                while (res.next()) {
                    FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("purchaseRecordsRow.fxml"));
                    Parent row = loader.load();

                    purchaseRecordsRowController prc = loader.getController();
                    prc.purchaseId.setText(res.getString("PUR_ID"));
                    prc.totalProducts.setText(res.getString("Total Products"));
                    prc.supplierName.setText(res.getString("S_NAME"));
                    prc.paymentMethod.setText(res.getString("PUR_METHOD"));
                    prc.employee.setText(res.getString("E_NAME"));
                    prc.totalAmount.setText(res.getString("Total Amount"));
                    prc.date.setText(res.getString("PUR_TIME"));
                    prc.moreDetails.setId(res.getString("PUR_ID"));

                    purchaseVbox.getChildren().add(row);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                String q = "select PUR_ID, PUR_METHOD, PUR_TIME,\n" +
                        "(select S_NAME from SUPPLIER s where s.S_ID=p.S_ID) as S_NAME,\n" +
                        "(select E_NAME from EMPLOYEE e where e.E_ID=p.E_ID) as E_NAME,\n" +
                        "(select count(P_ID) from [PRO_PUR] pp where pp.PUR_ID=p.PUR_ID) as [Total Products],\n" +
                        "(select sum(PUR_PRICE * PUR_QUANTITY) from [PRO_PUR] pp where pp.PUR_ID=p.PUR_ID) as [Total Amount]\n" +
                        "from PURCHASE p";
                ResultSet res = HelloApplication.statement.executeQuery(q);

                while (res.next()) {
                    FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("purchaseRecordsRow.fxml"));
                    Parent row = loader.load();

                    purchaseRecordsRowController prc = loader.getController();
                    prc.purchaseId.setText(res.getString("PUR_ID"));
                    prc.totalProducts.setText(res.getString("Total Products"));
                    prc.supplierName.setText(res.getString("S_NAME"));
                    prc.paymentMethod.setText(res.getString("PUR_METHOD"));
                    prc.employee.setText(res.getString("E_NAME"));
                    prc.totalAmount.setText(res.getString("Total Amount"));
                    prc.date.setText(res.getString("PUR_TIME"));
                    prc.moreDetails.setId(res.getString("PUR_ID"));

                    purchaseVbox.getChildren().add(row);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void clearAllProducts() {
        purchaseVbox.getChildren().clear();
    }
    public void loadAllPurchases() {
        try {
            String q = "select PUR_ID, PUR_METHOD, PUR_TIME,\n" +
                    "(select S_NAME from SUPPLIER s where s.S_ID=p.S_ID) as S_NAME,\n" +
                    "(select E_NAME from EMPLOYEE e where e.E_ID=p.E_ID) as E_NAME,\n" +
                    "(select count(P_ID) from [PRO_PUR] pp where pp.PUR_ID=p.PUR_ID) as [Total Products],\n" +
                    "(select sum(PUR_PRICE * PUR_QUANTITY) from [PRO_PUR] pp where pp.PUR_ID=p.PUR_ID) as [Total Amount]\n" +
                    "from PURCHASE p";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("purchaseRecordsRow.fxml"));
                Parent row = loader.load();

                purchaseRecordsRowController prc = loader.getController();
                prc.purchaseId.setText(res.getString("PUR_ID"));
                prc.totalProducts.setText(res.getString("Total Products"));
                prc.supplierName.setText(res.getString("S_NAME"));
                prc.paymentMethod.setText(res.getString("PUR_METHOD"));
                prc.employee.setText(res.getString("E_NAME"));
                prc.totalAmount.setText(res.getString("Total Amount"));
                prc.date.setText(res.getString("PUR_TIME"));
                prc.moreDetails.setId(res.getString("PUR_ID"));
                prc.purchaseVbox = purchaseVbox;
                prc.row = row;

                purchaseVbox.getChildren().add(row);
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
                value = "0";
            }
            String q = "select PUR_ID, PUR_METHOD, PUR_TIME,\n" +
                    "(select S_NAME from SUPPLIER s where s.S_ID=p.S_ID) as S_NAME,\n" +
                    "(select E_NAME from EMPLOYEE e where e.E_ID=p.E_ID) as E_NAME,\n" +
                    "(select count(P_ID) from [PRO_PUR] pp where pp.PUR_ID=p.PUR_ID) as [Total Products],\n" +
                    "(select sum(PUR_PRICE * PUR_QUANTITY) as [T] from [PRO_PUR] pp where pp.PUR_ID=p.PUR_ID)\n" +
                    "as [Total Amount]\n" +
                    "from PURCHASE p\n" +
                    "WHERE (SELECT SUM(PUR_PRICE * PUR_QUANTITY) FROM [PRO_PUR] pp WHERE pp.PUR_ID = p.PUR_ID) >=" + value + " order by [Total Amount] desc";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("purchaseRecordsRow.fxml"));
                Parent row = loader.load();

                purchaseRecordsRowController prc = loader.getController();
                prc.purchaseId.setText(res.getString("PUR_ID"));
                prc.totalProducts.setText(res.getString("Total Products"));
                prc.supplierName.setText(res.getString("S_NAME"));
                prc.paymentMethod.setText(res.getString("PUR_METHOD"));
                prc.employee.setText(res.getString("E_NAME"));
                prc.totalAmount.setText(res.getString("Total Amount"));
                prc.date.setText(res.getString("PUR_TIME"));
                prc.moreDetails.setId(res.getString("PUR_ID"));

                purchaseVbox.getChildren().add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void handleGet() {
        clearAllProducts();
        try {
            LocalDate fromDate = from.getValue();
            LocalDate toDate = to.getValue();

            String q = "select PUR_ID, PUR_METHOD, PUR_TIME,\n" +
                    "(select S_NAME from SUPPLIER s where s.S_ID=p.S_ID) as S_NAME,\n" +
                    "(select E_NAME from EMPLOYEE e where e.E_ID=p.E_ID) as E_NAME,\n" +
                    "(select count(P_ID) from [PRO_PUR] pp where pp.PUR_ID=p.PUR_ID) as [Total Products],\n" +
                    "(select sum(PUR_PRICE * PUR_QUANTITY) as [T] from [PRO_PUR] pp where pp.PUR_ID=p.PUR_ID)\n" +
                    "as [Total Amount]\n" +
                    "from PURCHASE p where \n" +
                    "YEAR(PUR_TIME) >= "+ fromDate.getYear() +" and YEAR(PUR_TIME) <= "+toDate.getYear()+" and\n" +
                    "MONTH(PUR_TIME) >= "+fromDate.getMonthValue()+" and MONTH(PUR_TIME) <= "+toDate.getMonthValue()+" and\n" +
                    "DAY(PUR_TIME) >= "+fromDate.getDayOfMonth()+" and DAY(PUR_TIME) <= " + toDate.getDayOfMonth();
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("purchaseRecordsRow.fxml"));
                Parent row = loader.load();

                purchaseRecordsRowController prc = loader.getController();
                prc.purchaseId.setText(res.getString("PUR_ID"));
                prc.totalProducts.setText(res.getString("Total Products"));
                prc.supplierName.setText(res.getString("S_NAME"));
                prc.paymentMethod.setText(res.getString("PUR_METHOD"));
                prc.employee.setText(res.getString("E_NAME"));
                prc.totalAmount.setText(res.getString("Total Amount"));
                prc.date.setText(res.getString("PUR_TIME"));
                prc.moreDetails.setId(res.getString("PUR_ID"));

                purchaseVbox.getChildren().add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void filterBySearch() {
        clearAllProducts();
        try {
            String value = search.getText();
            String q = "select PUR_ID, PUR_METHOD, PUR_TIME,\n" +
                    "(select S_NAME from SUPPLIER s where s.S_ID=p.S_ID) as S_NAME,\n" +
                    "(select E_NAME from EMPLOYEE e where e.E_ID=p.E_ID) as E_NAME,\n" +
                    "(select count(P_ID) from [PRO_PUR] pp where pp.PUR_ID=p.PUR_ID) as [Total Products],\n" +
                    "(select sum(PUR_PRICE * PUR_QUANTITY) from [PRO_PUR] pp where pp.PUR_ID=p.PUR_ID) as [Total Amount]\n" +
                    "from PURCHASE p where PUR_ID ='"+value+"'\n";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("purchaseRecordsRow.fxml"));
                Parent row = loader.load();

                purchaseRecordsRowController prc = loader.getController();
                prc.purchaseId.setText(res.getString("PUR_ID"));
                prc.totalProducts.setText(res.getString("Total Products"));
                prc.supplierName.setText(res.getString("S_NAME"));
                prc.paymentMethod.setText(res.getString("PUR_METHOD"));
                prc.employee.setText(res.getString("E_NAME"));
                prc.totalAmount.setText(res.getString("Total Amount"));
                prc.date.setText(res.getString("PUR_TIME"));
                prc.moreDetails.setId(res.getString("PUR_ID"));

                purchaseVbox.getChildren().add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
