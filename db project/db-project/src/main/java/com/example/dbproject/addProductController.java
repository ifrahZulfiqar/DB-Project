package com.example.dbproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

public class addProductController {
    @FXML
    TextField productName, preset, quantity, sellingPrice, purchasePrice, weight, supplierPhone, discount;
    @FXML
    ScrollPane productScrollpane;
    @FXML
    ComboBox<String> type, method, supplierName;
    @FXML
    VBox productsVbox;
    ArrayList<Supplier> suppliers = new ArrayList<>();
    @FXML
    public void initialize() {
        ObservableList<String> list = FXCollections.observableArrayList();
        ObservableList<String> list2 = FXCollections.observableArrayList();
        ObservableList<String> list3 = FXCollections.observableArrayList();

        list.add("Sold by Weight");
        list.add("Sold by Quantity");

        list2.add("cash");
        list2.add("card");

        try {
            ResultSet res = HelloApplication.statement.executeQuery("select * from SUPPLIER");

            while (res.next()) {
                list3.add(res.getString("S_NAME"));
                suppliers.add(new Supplier(res.getString("S_NAME"), res.getString("S_ID"), res.getString("S_PHONE")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        type.setItems(list);
        method.setItems(list2);
        supplierName.setItems(list3);
    }

    public static ArrayList<AddProduct> products = new ArrayList<>();
    @FXML
    public void handleAdd() throws IOException {
        if (!productName.getText().equals("") && !quantity.getText().equals("") && !sellingPrice.getText().equals("") && !purchasePrice.getText().equals("") && !weight.getText().equals("") && !(type.getValue() == null)) {

            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("addProductRow.fxml"));
            Parent root = loader.load();

            addProductRowController apr = loader.getController();
            apr.name.setText(productName.getText());
            apr.weight.setText(weight.getText());
            apr.quantity.setText(quantity.getText());
            apr.uprice.setText(sellingPrice.getText());
            apr.upurchase.setText(purchasePrice.getText());

            apr.productsVbox = productsVbox;
            apr.root = root;
            apr.productName = productName;
            apr.tquantity = quantity;
            apr.tweight = weight;
            apr.preset = preset;
            apr.sellingPrice = sellingPrice;
            apr.purchasePrice = purchasePrice;
            apr.typeCombo = type;
            apr.type = type.getValue().equals("Sold by Weight") ? "W" : "Q";

            // adding in array list
            AddProduct p = new AddProduct(productName.getText(), apr.type, Double.parseDouble(purchasePrice.getText()), Double.parseDouble(sellingPrice.getText()), Double.parseDouble(weight.getText()), Integer.parseInt(quantity.getText()), Integer.parseInt(preset.getText()));
            products.add(p);
            apr.p = p;

            // clearing inputs fields
            productName.setText("");
            weight.setText("");
            quantity.setText("");
            preset.setText("");
            purchasePrice.setText("");
            sellingPrice.setText("");

            productsVbox.getChildren().add(root);
            productScrollpane.setVvalue(1.0);
        }
    }
    @FXML
    public void handleAddToDatabase() {
        try {

            // getting supplier id by checking if it already exists or needs a new one (creates new if needed)
            ResultSet supplier_res = HelloApplication.statement.executeQuery("declare @temp varchar(10)\n" +
                    "set @temp = dbo.supplier_id()\n" +
                    "exec add_supplier @id=@temp, @name='"+ supplierName.getValue() +"', @phone="+supplierPhone.getText()+"");
            supplier_res.next();
            String sid = supplier_res.getString("S_ID");

            // Adding in purchase table as a bill record
            HelloApplication.statement.addBatch("declare @temp3 varchar(10)" +
                    "set @temp3 = dbo.purchase_id()" +
                    "exec add_purchase @purid=@temp3, @sid='" + sid + "', @method='"+ method.getValue() +"', @discount=" + discount.getText() + ", @eid='"+ HelloApplication.employee.id +"'");

            // adding in product tables and in pro_pur table
            HelloApplication.statement.addBatch("declare @temp2 varchar(10)");
            for (AddProduct p:products) {

                // procedure for products
                String q = "set @temp2 = dbo.product_id();\n" +
                        "exec dbo.add_product @pname='"+ p.name +"', @pid=@temp2, @purchase="+ p.purchase +", @price="+ p.price +", @quantity="+p.quantity+", @weight="+p.weight+", @type='"+p.type+"', @preset="+p.preset+", @supplier='"+ sid +"'";

                // procedure for pro_pur
                String q2 = "exec add_pro_pur @purid=@temp3, @pid=@temp2, @quantity=" + p.quantity + ", @price=" + p.price;
                HelloApplication.statement.addBatch(q);
                HelloApplication.statement.addBatch(q2);
            }
            HelloApplication.statement.executeBatch();

            productsVbox.getChildren().clear();
            products.clear();

            discount.setText("");
            supplierName.setValue("");
            supplierPhone.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void handleSupplier() {

        for (Supplier s:suppliers) {
            if (s.name.equals(supplierName.getValue())) {
                supplierPhone.setText(s.phone);
            }
        }
    }
    class Supplier {
        String name, id, phone;

        public Supplier(String name, String id, String phone) {
            this.name = name;
            this.id = id;
            this.phone = phone;
        }
    }
}
