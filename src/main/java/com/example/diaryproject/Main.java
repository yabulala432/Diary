package com.example.diaryproject;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;

import java.io.IOException;

public class Main extends javafx.application.Application {
    public static Statement statement;
    @Override
    public void start(Stage stage)
            throws IOException, ClassNotFoundException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                Main.class.getResource(
                        "main.fxml")
        );
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3307/Diary";
        Connection connection = DriverManager.
                getConnection(url, "root", "");
        statement = connection.createStatement();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Diary App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
