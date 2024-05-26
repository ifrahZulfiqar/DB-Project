package com.example.dbproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.ResultSet;

public class loginController {

    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private Text notification;
    @FXML
    protected void login() {

        try {
            String query = "select E_DESIGNATION, E_NAME, E_ID, (select E_USERNAME from [EMPLOYEE DETAILS] ed where ed.E_ID=e.E_ID) as E_USERNAME, (select E_PASS from [EMPLOYEE DETAILS] ed where ed.E_ID=e.E_ID) as E_PASS from EMPLOYEE e where e.E_ID=(select E_ID from [EMPLOYEE DETAILS] ed where ed.E_USERNAME = '" +username.getText() + "' and ed.E_PASS='" + password.getText() + "')";
            ResultSet res = HelloApplication.statement.executeQuery(query);

            if (!res.isBeforeFirst()) {
                notification.setText("Incorrect details.");
            } else {
                res.next();
                notification.setText("Welcome Back, " + res.getString("E_NAME"));

                HelloApplication.employee.id = res.getString("E_ID");
                HelloApplication.employee.name = res.getString("E_NAME");

                switch (res.getString("E_DESIGNATION")) {
                    case "manager" -> HelloApplication.employee.employeeLevels = EmployeeLevels.MANAGER;
                    case "cashier" -> HelloApplication.employee.employeeLevels = EmployeeLevels.CASHIER;
                    case "worker" -> HelloApplication.employee.employeeLevels = EmployeeLevels.WORKER;
                }

                HelloApplication.mainStage.setScene(new Scene(new FXMLLoader(HelloApplication.class.getResource("home.fxml")).load(), 1000, 800));
            }

            username.setText("");
            password.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleForgotPassword() throws IOException {
        HelloApplication.mainStage.setScene(new Scene(new FXMLLoader(HelloApplication.class.getResource("securityQuestion.fxml")).load(), 600, 400));
    }
}