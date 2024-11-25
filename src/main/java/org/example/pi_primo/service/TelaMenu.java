package org.example.pi_primo.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.pi_primo.HelloApplication;
import org.example.pi_primo.config.ConexaoDB;
import org.example.pi_primo.model.Produto;
import org.example.pi_primo.model.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static org.example.pi_primo.config.ConexaoDB.showAlert;

public class TelaMenu {

    public Scene mainScene;
    public TextField pesquisarText;
    ConexaoDB conexaoDB = new ConexaoDB();
    private final HelloApplication helloApplication = new HelloApplication();
    private final ObservableList<Produto> produtos = FXCollections.observableArrayList();

    @FXML
    public TableView<Produto> TabelaListaProduto;
    @FXML
    public MenuItem menu;

    @FXML
    public void initialize() throws SQLException {
        pesquisarText.setText(Session.pesquisa);
        conexaoDB.conection();
        try {
            listarProduto();
        } catch (SQLException e) {
            showAlert("Erro", "Não foi possível listar os produtos: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void sairUsuarioClicked() {
        helloApplication.loadScreen("paginaMeuUsuario.fxml", "Empréstimo VK",mainScene );
    }

    @FXML
    public void meusPedidosClicked() {
    }

    @FXML
    public void sairAplicacaoClicked() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação de Saída");
        alert.setHeaderText("Deseja realmente fechar o programa?");
        alert.setContentText("Todas as alterações não salvas serão perdidas.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }

    @FXML
    public void sairContaClicked() {

        Stage telaAtual = (Stage) mainScene.getWindow();
        telaAtual.close();

        helloApplication.loadScreen("paginaLogin.fxml", "Empréstimo VK", mainScene);
    }

    public void listarProduto() throws SQLException {
        String produtoQuery =
                "SELECT p.id AS id, p.nome AS nome, p.categoria_Produto AS 'categoria_Produto', p.descricao AS descricao " +
                        ", p.quantidadeDeEmprestimos AS quantidadeDeEmprestimos, p.preco AS preco, p.situacao AS situacao" +
                        ", prop.nome AS Proprietario, prop.id as idProprietario FROM produto p " +
                        "INNER JOIN cliente prop ON prop.id=p.Proprietario ORDER BY p.quantidadeDeEmprestimos ASC LIMIT 10;";
        try (Connection conn = ConexaoDB.conn;
             PreparedStatement smt = conn.prepareStatement(produtoQuery);
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
                        rs.getInt("idProprietario")
                );
                produtos.add(produto);
            }
            TabelaListaProduto.setItems(produtos);
        }
    }

    public void handleProductSelection(){
        Produto produto=TabelaListaProduto.getSelectionModel().getSelectedItem();

        Session.produto.setId(produto.getId());
        Session.produto.setNome(produto.getNome());
        Session.produto.setDescricao(produto.getDescricao());
        Session.produto.setPreco(produto.getPreco());
        Session.produto.setTipo(produto.getTipo());
        Session.produto.setQuantidadeDeEmprestimos(produto.getQuantidadeDeEmprestimos());
        Session.produto.setSituacao(produto.getSituacao());
        Session.produto.setProprietario(produto.getProprietario());
        Session.produto.setIdProprietario(produto.getidProprietario());

        helloApplication.loadScreen("paginaProduto.fxml", "VK",mainScene);
    }

    public void CadastrarProduto() {
        helloApplication.loadScreen("paginaCadastroProduto.fxml","VK",mainScene);
    }

    public void pesquisarClicked() {
        Session.pesquisa=pesquisarText.getText();
        Session.pesquisando=true;
        helloApplication.loadScreen("paginaPesquisa.fxml", "VK - Pesquisa", mainScene);
    }
}
