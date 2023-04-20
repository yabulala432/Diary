package com.example.diaryproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller2 {
    @FXML
    private TextArea narrationArea;
    @FXML
    private TextArea lessonArea;
    @FXML
    private TextField titleField;
    private final Statement statement = Main.statement;
    public void submitAction(ActionEvent event) {
        String narration = "", lesson = "", title = "";
        narration = narrationArea.getText();
        lesson = lessonArea.getText();
        title = titleField.getText();
        String username = MainController.username;
        try {
            statement.executeUpdate(
                    "INSERT INTO Diary.diaryData VALUES " +
                            "('" + title + "','" + lesson +
                            "','" + narration + "','" + username + "')");
//            System.out.println("Success");
            narrationArea.setText("");
            lessonArea.setText("");
            titleField.setText("");
        } catch (SQLException e) {
            System.out.println("err: " + e.getMessage());
        }
    }

    public void historyButtonAction(ActionEvent event){
        try{
            MainController.switchScenes(event, "main3.fxml");
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
