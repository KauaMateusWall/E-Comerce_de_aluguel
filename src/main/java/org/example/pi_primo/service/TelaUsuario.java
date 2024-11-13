package org.example.pi_primo.service;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import org.example.pi_primo.HelloApplication;
import org.example.pi_primo.config.ConexaoDB;
import org.example.pi_primo.model.Session;

public class TelaUsuario {
    HelloApplication helloApplication = new HelloApplication();
    ConexaoDB conexaoDB = new ConexaoDB();
    public Scene mainScene;

    @FXML
    public Label NomeTXT;
    @FXML
    public Label EndetecoTXT;
    @FXML
    public Label EmailTXT;
    @FXML
    public Label TelefoneTXT;

    public void initialize(){
        NomeTXT.setText(String.valueOf(Session.usuario.getNome()));
        EndetecoTXT.setText(String.valueOf(Session.usuario.getEndereco()));
        EmailTXT.setText(String.valueOf(Session.usuario.getEmail()));
        TelefoneTXT.setText(String.valueOf(Session.usuario.getTelefone()));
    }

    public void VolatrClickd(ActionEvent event) {
        helloApplication.loadScreen("paginamenu.fxml","k",mainScene);
    }
}
