package org.example.pi_primo.service;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.example.pi_primo.HelloApplication;
import org.example.pi_primo.config.ConexaoDB;
import org.example.pi_primo.model.Session;

public class TelaProduto {
    public Scene mainScene;
    HelloApplication helloApplication = new HelloApplication();
    ConexaoDB helloController = new ConexaoDB();

    @FXML public Button alugarButton;

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
    public void alugarClicked() {


    }

    @FXML
    public void voltarClicked() {
        helloApplication.loadScreen("paginaMenu.fxml","Empréstimo VK - Menu", mainScene);
    }
}
