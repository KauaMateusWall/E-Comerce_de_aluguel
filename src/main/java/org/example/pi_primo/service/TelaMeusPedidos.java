package org.example.pi_primo.service;

import javafx.scene.Scene;
import javafx.scene.control.TableView;
import org.example.pi_primo.HelloApplication;
import org.example.pi_primo.config.ConexaoDB;
import org.example.pi_primo.model.Pedido;
import org.example.pi_primo.model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TelaMeusPedidos {
    public TableView<Pedido> pedidoTable;
    HelloApplication helloApplication = new HelloApplication();
    ConexaoDB conexaoDB = new ConexaoDB();
    public Scene mainScene;

    public void initialize(){

    }

    public void voltarClicked() {
        helloApplication.loadScreen("paginaUsuario.fxml","VK",mainScene);
    }




}
