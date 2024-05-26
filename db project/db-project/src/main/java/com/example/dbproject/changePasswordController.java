package com.example.dbproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.sql.ResultSet;

public class changePasswordController {
    @FXML
    TextField newPassword = new TextField();
    @FXML
    TextField repeatPassword = new TextField();
    @FXML
    Text notification = new Text();
    @FXML
    Text enterYourNewPassword = new Text();
    String eid, designation;
    private String username;
    @FXML
    protected void handleChangePassword() {

        if (newPassword.getText().equals(repeatPassword.getText())) {

            try {
                String query = "update [EMPLOYEE DETAILS] set E_PASS='" + newPassword.getText() + "' where E_USERNAME='" + this.username + "'";
                int res = HelloApplication.statement.executeUpdate(query);

                if (res > 0) {
                    HelloApplication.employee.id = eid;
                    HelloApplication.employee.name = username;

                    switch (designation) {
                        case "manager" -> HelloApplication.employee.employeeLevels = EmployeeLevels.MANAGER;
                        case "cashier" -> HelloApplication.employee.employeeLevels = EmployeeLevels.CASHIER;
                        case "worker" -> HelloApplication.employee.employeeLevels = EmployeeLevels.WORKER;
                    }

                    HelloApplication.mainStage.setScene(new Scene(new FXMLLoader(HelloApplication.class.getResource("home.fxml")).load(), 1000, 800));
                } else {
                    notification.setText("Something went wrong. Try Again :(");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            notification.setText("New password is not as same as repeated password");
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
