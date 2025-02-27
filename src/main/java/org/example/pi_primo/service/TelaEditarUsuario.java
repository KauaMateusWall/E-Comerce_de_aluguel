package org.example.pi_primo.service;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import org.example.pi_primo.HelloApplication;
import org.example.pi_primo.config.ConexaoDB;

public class TelaEditarUsuario {

    public Scene mainScene;
    HelloApplication helloApplication = new HelloApplication();
    ConexaoDB conexaoDB = new ConexaoDB();

    public TextField CEPTXT;
    public TextField TelefoneTXT;
    public TextField GmailTXT;
    public TextField BairroTXT;
    public TextField EnderecoTXT;

    @FXML
    public void initialize() {

    }


    public void editar(ActionEvent event) {

    }
}


