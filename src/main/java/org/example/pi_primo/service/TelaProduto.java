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

    @FXML public Button alugarButton;
    @FXML public Button voltarButton;

    @FXML public Label precoText;
    @FXML public Label nomeProdutoText;

    @FXML public ImageView ProdutoImage;
    public Label descricaoText;
    public Label donoText;

    @FXML
    public void initialize() {
        precoText.setText(String.valueOf(Session.produto.getPreco()));
        nomeProdutoText.setText(Session.produto.getNome());
        donoText.setText(Session.dono.getNome());
        descricaoText.setText(Session.produto.getDescricao());
        
    }

    @FXML
    public void alugarClicked(ActionEvent actionEvent) {


    }

    @FXML
    public void voltarClicked(ActionEvent actionEvent) {
        helloApplication.loadScreen("paginaMenu.fxml","Empréstimo VK - Menu",voltarButton);
    }
}
