module org.example.pi_primo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.beans;
    requires jakarta.mail;
    requires static lombok;

    requires java.desktop;
    requires spring.core;
    requires spring.context.support;
    requires java.mail;
    requires twilio;
    requires com.google.protobuf;

    opens org.example.pi_primo to javafx.fxml;
    exports org.example.pi_primo;
    exports org.example.pi_primo.model;
    opens org.example.pi_primo.model to javafx.fxml;
    exports org.example.pi_primo.service;
    opens org.example.pi_primo.service to javafx.fxml;
    exports org.example.pi_primo.config;
    opens org.example.pi_primo.config to javafx.fxml;
}
