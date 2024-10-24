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


public class TelaMenu {

    @FXML public Button meusProdutosButton;
    @FXML public Button meusPedidosButton;
    @FXML public Button pesquisarProdutosButton;
    @FXML public Button meuUsuarioButton;




    @FXML
    public Insets ListaProdutoMenu;
    public MenuItem menu;

    @FXML
    public void meuUsuarioClicked(ActionEvent actionEvent) {
    }

    @FXML
    public void sairUsuarioClicked(ActionEvent event) throws IOException {
       loadScreen("paginaMeuUsuario.fxml","Empréstimo VK",event);

    }

    @FXML
    public void meusPedidosClicked(ActionEvent actionEvent) {
    }

    @FXML
    public void meusProdutosClicked(ActionEvent actionEvent) {
    }


    @FXML public Button sairAplicacaoButton;
    @FXML public Button sairContaButton;

    @FXML
    public void sairApicacaoClicked(ActionEvent actionEvent) {
        System.exit(0);
    }

    @FXML
    public void sairContaClicked(ActionEvent event) {
        loadScreen("paginaLogin.fxml","Empréstimo VK",event);

    }

    public void loadScreen(String fxmlFile, String title,ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Stage stage = (Stage) menu.getParentPopup().getOwnerWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
