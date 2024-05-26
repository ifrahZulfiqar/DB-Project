package com.example.dbproject;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.controlsfx.control.textfield.TextFields;

import java.sql.ResultSet;
import java.util.ArrayList;

public class placeOrderController {
    String customerId;
    @FXML
    ComboBox searchComboBox;
    @FXML
    VBox productsVbox, productsVbox1;
    @FXML
    public Text amountPaid, discount, totalBill, totalBillAfterDiscount, returnAmount;
    @FXML
    TextField discountInPercentages, customerEmailInput, discountInRuppees, customerPhoneNoInput, customerNameInput, amountPaidInput;
    @FXML
    public void initialize() {
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        customerEmailInput.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    pause.setOnFinished(event -> {
                        fetchCustomer();
                    });
                    pause.playFromStart();
                }
        );


        ObservableList<String> items = FXCollections.observableArrayList();
        try {
          String q = "SELECT p.P_ID,p.P_NAME,p.P_PRICE,p.P_PURCHASE,pd.P_QUANTITY,pd.P_WEIGHT,pd.P_TYPE FROM PRODUCT p inner join [PRODUCT DETAILS] pd on p.P_ID = pd.P_ID and pd.P_AVAILABILITY='available'";
          ResultSet res = HelloApplication.statement.executeQuery(q);

          while (res.next()) {
              items.add(res.getString("P_NAME"));
          }

        } catch (Exception e) {
            e.printStackTrace();
        }


        FilteredList<String> filteredItems = new FilteredList<String>(items, p -> true);
        searchComboBox.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            final TextField editor = searchComboBox.getEditor();
            final String selected = (String)searchComboBox.getSelectionModel().getSelectedItem();
            Platform.runLater(() -> {
                if (selected == null || !selected.equals(editor.getText())) {
                    filteredItems.setPredicate(item -> {
                        if (item.toUpperCase().startsWith(newValue.toUpperCase())) {
                            return true;
                        } else {
                            return false;
                        }
                    });
                }
            });
        });

        searchComboBox.setItems(filteredItems);

    }

    public static ArrayList<Product> products = new ArrayList<>();

    @FXML
    public void handleSelection(ActionEvent e) {
        String typed = searchComboBox.getValue().toString();
        addInVbox(typed);
    }

    @FXML
    public void handleSearch() {
        String typed = searchComboBox.getValue().toString();
        addInVbox(typed);
    }
    @FXML
    public void handleCustomerName() {

    }
    @FXML
    public void handleAmountPaid() {
        amountPaid.setText(amountPaidInput.getText());
        returnAmount.setText((Double.parseDouble(amountPaid.getText()) - Double.parseDouble(totalBillAfterDiscount.getText())) + "");
    }
    @FXML
    public void handleCustomerPhoneNo() {

    }
    @FXML
    public void handleInPercentages() {
        discountInRuppees.setText("");
        double total = Double.parseDouble(totalBill.getText());

        double per = Double.parseDouble(discountInPercentages.getText()) / 100;
        System.out.println(total + " " + per);
        discount.setText(String.format("%.2f", (total * per)));
        totalBillAfterDiscount.setText(sumBill() - Double.parseDouble(discount.getText()) + "");
        returnAmount.setText(String.format("%.2f", Double.parseDouble(amountPaid.getText()) - Double.parseDouble(totalBillAfterDiscount.getText())) + "");
    }
    @FXML
    public void handleInRuppees() {
        discountInPercentages.setText("");
        discount.setText(discountInRuppees.getText());
        totalBillAfterDiscount.setText(sumBill() - Double.parseDouble(discount.getText()) + "");
        returnAmount.setText(String.format("%.2f", Double.parseDouble(amountPaid.getText()) - Double.parseDouble(totalBillAfterDiscount.getText())));
    }
    public void addInVbox(String typed) {
        try {
            String q = "select P_ID, P_NAME,P_PRICE, P_PURCHASE,\n" +
                    "(select P_AVAILABILITY from [PRODUCT DETAILS] pd\n" +
                    "where pd.P_ID=p.P_ID) as P_AVAILABILITY,\n" +
                    "(select P_WEIGHT from [PRODUCT DETAILS] pd\n" +
                    "where pd.P_ID=p.P_ID) as P_WEIGHT,\n" +
                    "(select P_TYPE from [PRODUCT DETAILS] pd\n" +
                    "where pd.P_ID=p.P_ID) as P_TYPE\n" +
                    "from PRODUCT p where p.P_NAME='"+ typed +"' and p.P_ID=\n" +
                    "(select P_ID from [PRODUCT DETAILS] pd \n" +
                    "where pd.P_ID=p.P_ID and pd.P_AVAILABILITY='available')";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            res.next();

            if (res.getString("P_TYPE").equals("Q")) {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("byQRow.fxml"));
                Parent comp = loader.load();

                byQRowController bqrc = loader.getController();
                bqrc.productsVbox = productsVbox;
                bqrc.comp = comp;
                bqrc.byQName.setText(res.getString("P_NAME"));
                bqrc.productId.setText(res.getString("P_ID"));
                bqrc.quantity.setText("1");
                bqrc.price.setText(res.getString("P_PRICE"));
                bqrc.totalPrice.setText(res.getString("P_PRICE"));
                bqrc.givenQuantity.setText(res.getString("P_WEIGHT"));
                bqrc.totalBill = totalBill;
                bqrc.totalBillAfterDiscount = totalBillAfterDiscount;
                bqrc.discount = discount;
                bqrc.returnAmount = returnAmount;
                bqrc.amountPaid = amountPaid;

                products.add(new Product());
                bqrc.p = products.get(products.size() - 1);

                bqrc.p.name = res.getString("P_NAME");
                bqrc.p.id = res.getString("P_ID");
                bqrc.p.price = Double.parseDouble(res.getString("P_PRICE"));
                bqrc.p.quantity = 1;

                double sum = sumBill();
                totalBill.setText(sum + "");
                totalBillAfterDiscount.setText(sum - Double.parseDouble(discount.getText()) + "");
                returnAmount.setText(String.format("%.2f",Double.parseDouble(amountPaid.getText()) - Double.parseDouble(totalBillAfterDiscount.getText())));

                productsVbox.getChildren().add(comp);
            } else {

                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("byWRow.fxml"));
                Parent comp = loader.load();

                byWRowController bwrc = loader.getController();
                bwrc.comp = comp;
                bwrc.productsVbox = productsVbox1;
                bwrc.byQName1.setText(res.getString("P_NAME"));
                bwrc.productId.setText(res.getString("P_ID"));
                bwrc.price.setText(res.getString("P_PRICE"));
                bwrc.amount.setText("0");
                bwrc.unitAmount = Double.parseDouble(res.getString("P_WEIGHT"));
                bwrc.totalPrice.setText("0");
                bwrc.weight.setText(res.getString("P_WEIGHT"));

                products.add(new Product());
                bwrc.p = products.get(products.size() - 1);

                bwrc.p.name = res.getString("P_NAME");
                bwrc.p.id = res.getString("P_ID");
                bwrc.p.price = Double.parseDouble(res.getString("P_PRICE"));
                bwrc.p.quantity = 0;
                bwrc.totalBill = totalBill;
                bwrc.totalBillAfterDiscount = totalBillAfterDiscount;
                bwrc.discount = discount;
                bwrc.returnAmount = returnAmount;
                bwrc.amountPaid = amountPaid;

                double sum = sumBill();
                totalBill.setText(sum + "");
                totalBillAfterDiscount.setText(sum - Double.parseDouble(discount.getText()) + "");
                returnAmount.setText(String.format("%.2f", Double.parseDouble(amountPaid.getText()) - Double.parseDouble(totalBillAfterDiscount.getText())));

                productsVbox1.getChildren().add(comp);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static double sumBill() {
        double sum = 0;
        for (Product p : products) {
            sum += p.price;
        }
        return sum;
    }
    public void fetchCustomer() {
        try {
            String q = "select * from CUSTOMER c where c.C_EMAIL='" + customerEmailInput.getText() + "'";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            if (res.next()) {
                customerNameInput.setText(res.getString("C_NAME"));
                customerPhoneNoInput.setText(res.getString("C_PHONE"));
                customerId = res.getString("C_PHONE");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void handleCustomerEmail() {

    }
    @FXML
    public void handlePlaceOrder() {
        try {

            String q = "declare @temp varchar(10)" +
                        "set @temp = dbo.customer_id()" +
                    "exec add_customer @id=@temp, @name='" + customerNameInput.getText() + "', @phone=" + customerPhoneNoInput.getText() +", @email='" + customerEmailInput.getText() + "'";
            ResultSet res = HelloApplication.statement.executeQuery(q);
            String cid = "";
            if (res.next()) {
                cid = res.getString("C_ID");
            }

            String q2 = "declare @temp2 varchar(10)\n" +
                    "\tset @temp2 = dbo.order_id();" +
                    "exec create_order @oid=@temp2, @cid="+cid+", @method='cash', @discount=" + discount.getText() + ", @eid='" + HelloApplication.employee.id + "' , @status='paid'";

            HelloApplication.statement.addBatch(q2);

            for (Product p:products) {
                 String q3 = "exec subtract_quantity @pid='" + p.id + "', @quantity=" + String.format("%.2f", p.quantity) + "";
                 String q4 = "exec add_order_items @pid='" + p.id + "', @oid=@temp2, @quantity=" + String.format("%.2f", p.quantity) + " , @price=" + p.price + "";

                 HelloApplication.statement.addBatch(q3);
                 HelloApplication.statement.addBatch(q4);
            }

            HelloApplication.statement.executeBatch();

            productsVbox.getChildren().clear();
            productsVbox1.getChildren().clear();
            products.clear();

            customerEmailInput.setText("");
            customerNameInput.setText("");
            customerPhoneNoInput.setText("");
            amountPaidInput.setText("");
            discountInPercentages.setText("");
            discountInRuppees.setText("");

            totalBill.setText("");
            totalBillAfterDiscount.setText("");
            returnAmount.setText("");
            amountPaid.setText("");
            discount.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
