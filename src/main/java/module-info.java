module org.example.pi_primo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires spring.context.support;
    requires commons.email;
    requires javafx.graphics;
    requires java.desktop;


    opens org.example.pi_primo to javafx.fxml;
    exports org.example.pi_primo;
    exports org.example.pi_primo.model;
    opens org.example.pi_primo.model to javafx.fxml;
    exports org.example.pi_primo.sevice;
    opens org.example.pi_primo.sevice to javafx.fxml;
}