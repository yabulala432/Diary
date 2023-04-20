package com.example.diaryproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class MainController {
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label failLabel;
    private final Statement statement = Main.statement;
    static String username;

    public void loginAction(ActionEvent event) {
        username = usernameTextField.getText();
        String password = passwordField.getText();
        try {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM users");
            boolean isCorrect = false;
            while (resultSet.next()) {
                if (username.length() >= 8 && password.length() >= 8) {
                    if (Objects.equals(username,
                            resultSet.getString(1)) &&
                            Objects.equals(password,
                                    resultSet.getString(2))) {
                        isCorrect = true;
                        switchScenes(event, "main2.fxml");
                    }
                }
            }
            if (!isCorrect) {
                failLabel.setText("Incorrect username or password !!!");
            } else {
                failLabel.setText("");
            }
        } catch (SQLException e) {
            System.out.println("err: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void registerAction(ActionEvent event) {
        username = usernameTextField.getText();
        var password = passwordField.getText();
        if (username.length() >= 8 && password.length() >= 8) {
            try{
                statement.executeUpdate(
                        "INSERT INTO users VALUES " +
                                "('" + username + "','" + password + "')");
                switchScenes(event,"main2.fxml");
            } catch (SQLException e) {
                failLabel.setText("username not unique !");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            failLabel.setText("too short username or password length !!!");
        }

    }

    public static void switchScenes(ActionEvent event, String fxml) throws IOException {
        Parent root = new FXMLLoader(MainController.class.getResource(fxml)).load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

