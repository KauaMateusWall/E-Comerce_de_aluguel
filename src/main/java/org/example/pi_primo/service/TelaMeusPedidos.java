package org.example.pi_primo.service;

import javafx.scene.Scene;
import javafx.scene.control.TableView;
import org.example.pi_primo.HelloApplication;
import org.example.pi_primo.config.ConexaoDB;
import org.example.pi_primo.model.Emprestimo;

public class TelaMeusPedidos {
    public TableView<Emprestimo> pedidoTable;
    HelloApplication helloApplication = new HelloApplication();
    ConexaoDB conexaoDB = new ConexaoDB();
    public Scene mainScene;

    public void initialize(){

    }

    public void voltarClicked() {
        helloApplication.loadScreen("paginaMeuUsuario.fxml","VK",mainScene);
    }

    public void pedidoTabelaClicked() {
        String query = "SELECT  FROM emprestimo e INNER JOIN ";

    }
}