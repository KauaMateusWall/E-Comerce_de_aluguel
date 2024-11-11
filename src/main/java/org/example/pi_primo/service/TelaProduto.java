package org.example.pi_primo.service;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.example.pi_primo.HelloApplication;
import org.example.pi_primo.config.ConexaoDB;
import org.example.pi_primo.model.Session;
import java.io.IOException;

public class TelaProduto {

    HelloApplication helloApplication = new HelloApplication();
    ConexaoDB helloController = new ConexaoDB();



    @FXML
    public Scene mainScene;
    @FXML
    public Label NomeTXT;
    @FXML
    public Label PrecoTXT;
    @FXML
    public Label ProTXT;
    @FXML
    public Label DescricaoTXT;


    @FXML
    public void initialize() {
        PrecoTXT.setText(String.valueOf(Session.produto.getPreco()));
        NomeTXT.setText(Session.produto.getNome());
        ProTXT.setText(Session.dono.getNome());
        DescricaoTXT.setText(Session.produto.getDescricao());
        
    }

    @FXML
    public void alugarClicked(ActionEvent actionEvent) {
    }

    @FXML
    public void voltarClicked(ActionEvent event) {
        helloApplication.loadScreen("paginaMenu.fxml","Empréstimo VK - Menu",mainScene);
    }
}
