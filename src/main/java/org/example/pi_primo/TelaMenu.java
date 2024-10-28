package org.example.pi_primo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class TelaMenu {

    HelloApplication helloAplication = new HelloApplication();

    @FXML
    public Button meusProdutosButton;

    @FXML
    public Button meusPedidosButton;

    @FXML
    public Button pesquisarProdutosButton;

    @FXML
    public Button meuUsuarioButton;

    @FXML
    public Button sairAplicacaoButton;

    @FXML
    public Button sairContaButton;



    @FXML
    public Insets ListaProdutoMenu;
    public MenuItem menu;

    @FXML
    public void meuUsuarioClicked(ActionEvent actionEvent) {
    }

    @FXML
    public void sairUsuarioClicked(ActionEvent event) throws IOException {
       helloAplication.loadScreen("paginaMeuUsuario.fxml","Empréstimo VK",event);

    }

    @FXML
    public void meusPedidosClicked(ActionEvent actionEvent) {
    }

    @FXML
    public void meusProdutosClicked(ActionEvent actionEvent) {
    }

    @FXML
    public void sairApicacaoClicked(ActionEvent actionEvent) {
        System.exit(0);
    }

    @FXML
    public void sairContaClicked(ActionEvent event) {
        helloAplication.loadScreen("paginaLogin.fxml","Empréstimo VK",event);

    }
}
