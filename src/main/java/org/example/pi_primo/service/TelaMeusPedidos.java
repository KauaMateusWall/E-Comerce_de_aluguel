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

    public void pedidoTabelaClicked() {
        String query =
                "SELECT ped.data as data, ped.situacao as situacao" +
                        "p.nome as nome, p.preco as preco, "

                "INNER JOIN cliente prop ON prop.id=p.Proprietario ORDER BY p.quantidadeDeEmprestimos ASC;";
        try (Connection conn = ConexaoDB.conn;
             PreparedStatement smt = conn.prepareStatement(query);
             ResultSet rs = smt.executeQuery()) {

            while (rs.next()) {
                Pedido pedido = new Pedido(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("categoria_Produto"),
                        rs.getString("descricao"),
                        rs.getInt("quantidadeDeEmprestimos"),
                        rs.getInt("preco"),
                        rs.getString("situacao"),
                        rs.getString("Proprietario")
                );
                pedidoTable.getItems().add(pedido);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
