package com.example.dbproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.ResultSet;

public class securityQuestionController{

    @FXML
    TextField username = new TextField();
    @FXML TextField question = new TextField();
    @FXML
    Text notification = new Text();
    @FXML
    protected void handleSubmit() {

        try {
            String query = "select E_USERNAME, E_ID, (select E_NAME from EMPLOYEE e where e.E_ID=ed.E_ID) as E_NAME, (select E_DESIGNATION from EMPLOYEE e where e.E_ID=ed.E_ID) as E_DESIGNATION from [EMPLOYEE DETAILS] ed where ed.E_USERNAME='" + username.getText() + "' and ed.E_QUESTION='" + question.getText() + "'";
            ResultSet res = HelloApplication.statement.executeQuery(query);

            FXMLLoader changePassword = new FXMLLoader(HelloApplication.class.getResource("changePassword.fxml"));

            if (res.next()) {
                Parent parent = changePassword.load();
                changePasswordController cp = new changePasswordController();
                cp = changePassword.getController();
                cp.setUsername(res.getString("E_USERNAME"));
                cp.enterYourNewPassword.setText("Enter your new password, " + res.getString("E_NAME"));

                cp.eid = res.getString("E_ID");
                cp.designation = res.getString("E_DESIGNATION");
                HelloApplication.mainStage.setScene(new Scene(parent, 600, 400));
            } else {
                notification.setText("Imposter???????");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
