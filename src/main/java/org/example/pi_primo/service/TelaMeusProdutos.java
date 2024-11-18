package org.example.pi_primo.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import org.example.pi_primo.HelloApplication;
import org.example.pi_primo.config.ConexaoDB;
import org.example.pi_primo.model.Produto;
import org.example.pi_primo.model.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.example.pi_primo.config.ConexaoDB.showAlert;

public class TelaMeusProdutos {
    public Scene mainScene;
    HelloApplication helloApplication = new HelloApplication();
    ConexaoDB conexaoDB = new ConexaoDB();

    private final ObservableList<Produto> produtos = FXCollections.observableArrayList();{
    }

    @FXML
    public TableView<Produto> TabelaListaProduto;


    @FXML
    public void initialize() throws SQLException {
        conexaoDB.conection();
        try {
            listarProduto();
        } catch (SQLException e) {
            showAlert("Erro", "Não foi possível listar os produtos: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void listarProduto() throws SQLException {
        String query = "select * from produto";
        try (Connection conn = ConexaoDB.conn;
             PreparedStatement smt = conn.prepareStatement(query);
             ResultSet rs = smt.executeQuery()) {

            produtos.clear();

            while (rs.next()) {
                Produto produto = new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("categoria_Produto"),
                        rs.getString("descricao"),
                        rs.getInt("quantidadeDeEmprestimos"),
                        rs.getInt("preco"),
                        rs.getString("situacao"),
                        rs.getString("Proprietario"),
                        Session.usuario.getid()
                );
                produtos.add(produto);
            }
            TabelaListaProduto.setItems(produtos);
        }
    }

    public void handleProductSelection() {
        Produto produto = (Produto) TabelaListaProduto.getSelectionModel().getSelectedItem();

        if (produto == null) {
            showAlert("VK", "O produto não existe!!", Alert.AlertType.ERROR);
            return;
        }

        Session.produto.setId(produto.getId());
        Session.produto.setNome(produto.getNome());
        Session.produto.setDescricao(produto.getDescricao());
        Session.produto.setPreco(produto.getPreco());
        Session.produto.setTipo(produto.getTipo());
        Session.produto.setQuantidadeDeEmprestimos(produto.getQuantidadeDeEmprestimos());
        Session.produto.setSituacao(produto.getSituacao());
        Session.produto.setProprietario(produto.getProprietario());


        helloApplication.loadScreen("paginaProduto.fxml", "VK", mainScene);
    }

    public void CadastrarProduto() {
        helloApplication.loadScreen("paginaCadastroProduto.fxml", "VK", mainScene);
    }
}