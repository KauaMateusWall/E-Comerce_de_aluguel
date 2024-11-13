package org.example.pi_primo.service;

import javafx.scene.Scene;
import javafx.scene.control.TableView;
import org.example.pi_primo.HelloApplication;
import org.example.pi_primo.config.ConexaoDB;
import org.example.pi_primo.model.Pedido;

public class TelaMeusPedidos {
    public TableView<Pedido> pedidoTable;
    HelloApplication helloApplication = new HelloApplication();
    ConexaoDB conexaoDB = new ConexaoDB();
    public Scene mainScene;

    public void initialize(){

    }

    public void voltarClicked() {
        helloApplication.loadScreen("paginaMeuUsuario.fxml","VK",mainScene);
    }

    public void pedidoTabelaClicked() {
        String query =
                "SELECT ped.id AS id, ped.data AS data, ped.situacao AS situacao, " +
                        "p.nome AS nome, p.preco AS preco, " +
                        "prop.nome AS proprietario FROM pedido " +
                        "INNER JOIN cliente c ON c.id=ped.cliente_id " +
                        "INNER JOIN cliente prop ON prop.id=ped.proprietario_id " +
                        "INNER JOIN produto p ON p.id=ped.produto_id ORDER BY data ASC;";
    }
}