module com.example.diaryproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.diaryproject to javafx.fxml;
    exports com.example.diaryproject;
}