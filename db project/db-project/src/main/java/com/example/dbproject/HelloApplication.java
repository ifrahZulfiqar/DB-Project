package com.example.dbproject;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        // ASSIGNING TO A PUBLIC VARIABLE SO IT CAN BE HANDLED ANYWHERE
        Stage splashStage;

        // LOADING FXML FILE
        FXMLLoader splashScreen = new FXMLLoader(HelloApplication.class.getResource("splashScreen.fxml"));
        FXMLLoader loginPage = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        mainScene = new Scene(splashScreen.load(), 600, 350);

        splashStage = stage;
        splashStage.setScene(mainScene);
        splashStage.initStyle(StageStyle.UNDECORATED);
        splashStage.setResizable(false);
        splashStage.show();

        // STAGE PROPERTIES
        mainStage = new Stage();
        mainStage.setTitle("Login!");

        Duration displayDuration = Duration.seconds(3);
        Timeline timeline = new Timeline(new KeyFrame(displayDuration, e -> {
            stage.close();

            try {
                mainStage.setScene(new Scene(loginPage.load(), 700, 400));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            mainStage.show();
        }));

        timeline.play();

//        mainStage = stage;
//        mainStage.setScene(new Scene(new FXMLLoader(HelloApplication.class.getResource("placeOrder.fxml")).load()));
//        mainStage.show();
    }

    // making connection static and public to reduce unnecessary connections everywhere
    public static Connection con;
    public static Statement statement;
    public static Stage mainStage;
    public static Scene mainScene;
    public static Employee employee = new Employee();

    // connection to database
    {
        try {
            DriverManager.registerDriver(new SQLServerDriver());
            con = DriverManager.getConnection("jdbc:sqlserver://"+ InetAddress.getLocalHost().getHostName() +";trustServerCertificate=true;IntegratedSecurity=true;encrypt=true;Database=ISMS");
            statement = con.createStatement();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        launch();
    }
}