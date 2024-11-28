package org.example.pi_primo.service;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.pi_primo.HelloApplication;
import org.example.pi_primo.config.ConexaoDB;
import org.example.pi_primo.model.Produto;
import org.example.pi_primo.config.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;


public class TelaMenu {

    public Scene mainScene;
    public TextField pesquisarText;
    private final HelloApplication helloApplication = new HelloApplication();
    ConexaoDB conexaoDB = new ConexaoDB();
    private final ObservableList<Produto> produtos = FXCollections.observableArrayList();

    @FXML
    public TableView<Produto> TabelaListaProduto;

    @FXML
    public MenuItem menu;

    @FXML
    public void initialize() {
        try {
            pesquisarText.setText(Session.pesquisa);
            conexaoDB.conection();

            iniciarAtualizacaoPeriodica();
        } catch (SQLException e) {
            conexaoDB.showAlert("Erro", "Não foi possível inicializar a tela: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void iniciarAtualizacaoPeriodica() throws SQLException {
        listarProduto();

        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.seconds(5),
                        event -> {
                            try {
                                listarProduto();
                            } catch (SQLException e) {
                            }
                        }
                )
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @FXML
    public void sairUsuarioClicked() {
        helloApplication.openScreen("paginaMeuUsuario.fxml", "Empréstimo VK", mainScene);
    }

    @FXML
    public void meusPedidosClicked() {
       helloApplication.openScreen("paginaMeusPedidos.fxml","VK",mainScene);
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
        helloApplication.openScreen("paginaLogin.fxml", "Empréstimo VK", mainScene);
    }

    public void listarProduto() throws SQLException {
        String produtoQuery =
                "SELECT p.id AS id, p.nome AS nome, p.categoria_Produto AS 'categoria_Produto', p.descricao AS descricao, " +
                        "p.quantidadeDeEmprestimos AS quantidadeDeEmprestimos, p.preco AS preco, p.situacao AS situacao, " +
                        "prop.nome AS Proprietario, prop.id AS idProprietario FROM produto p " +
                        "INNER JOIN cliente prop ON prop.id = p.Proprietario " +
                        "ORDER BY p.quantidadeDeEmprestimos ASC LIMIT 10;";
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
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar produtos: " + e.getMessage(), e);
        }
    }

    public void handleProductSelection() {
        Produto produto = TabelaListaProduto.getSelectionModel().getSelectedItem();
        if(produto==null)
            return;

        Session.produto.setId(produto.getId());
        Session.produto.setNome(produto.getNome());
        Session.produto.setDescricao(produto.getDescricao());
        Session.produto.setPreco(produto.getPreco());
        Session.produto.setTipo(produto.getTipo());
        Session.produto.setQuantidadeDeEmprestimos(produto.getQuantidadeDeEmprestimos());
        Session.produto.setSituacao(produto.getSituacao());
        Session.produto.setProprietario(produto.getProprietario());
        Session.produto.setIdProprietario(produto.getidProprietario());


        TabelaListaProduto.getSelectionModel().clearSelection();
        if(Session.usuario.getid() == Session.produto.getidProprietario()){
            helloApplication.openScreen("paginaProdutoDono.fxml","k",mainScene);
        }else {
            helloApplication.openScreen("paginaProduto.fxml", "VK - Produto", mainScene);
        }
    }

    public void CadastrarProduto() {
        helloApplication.openScreen("paginaCadastroProduto.fxml", "VK - Cadastro de Produto", mainScene);
    }

    public void pesquisarClicked() {
        Session.pesquisa = pesquisarText.getText();
        Session.pesquisando = true;
        helloApplication.openScreen("paginaPesquisa.fxml", "VK - Pesquisa", mainScene);
    }
}
