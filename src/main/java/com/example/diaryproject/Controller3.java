package com.example.diaryproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller3 implements Initializable {
    @FXML
    private Label historyLabel;
    @FXML
    private Label titleLabel;
    @FXML
    private Label lessonLabel;
    @FXML
    private ChoiceBox<String> choiceBox;
    private ResultSet resultSet;
    private final Statement statement = Main.statement;
    private final String username = MainController.username;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> titleList = new ArrayList<>();
        try {
            resultSet = statement.executeQuery(
                    "SELECT title FROM Diary.diaryData WHERE username = '" + username + "'");
            while (resultSet.next()) {
                titleList.add(resultSet.getString("title"));
            }
            choiceBox.getItems().addAll(titleList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void readAction(ActionEvent event) throws SQLException {
        resultSet = statement.executeQuery(
                "SELECT * " +
                        "FROM diaryData " +
                        "WHERE title = '" + choiceBox.getValue() + "'");
        while (resultSet.next()) {
            lessonLabel.setText(resultSet.getString("lesson"));
            historyLabel.setText(resultSet.getString("narration"));
            titleLabel.setText(resultSet.getString("title"));
        }
    }

    public void backAction(ActionEvent event) throws IOException {
        MainController.switchScenes(event, "main2.fxml");
    }

    public void exitAction(ActionEvent event) throws IOException{
        MainController.switchScenes(event,"main.fxml");
    }

}