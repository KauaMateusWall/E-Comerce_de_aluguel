package org.example.pi_primo.sevice;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import org.example.pi_primo.HelloApplication;

import java.io.IOException;


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
