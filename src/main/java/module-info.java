module org.example.pi_primo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens org.example.pi_primo to javafx.fxml;
    exports org.example.pi_primo;
}