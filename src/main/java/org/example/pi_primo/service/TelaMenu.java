package org.example.pi_primo.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.pi_primo.HelloApplication;
import org.example.pi_primo.config.ConexaoDB;
import org.example.pi_primo.model.Produto;
import org.example.pi_primo.model.Session;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class TelaMenu {

    public Scene mainScene;
    public TextField pesquisarText;
    ConexaoDB conexaoDB = new ConexaoDB();
    private final HelloApplication helloApplication = new HelloApplication();
    private final ObservableList<Produto> produtos = FXCollections.observableArrayList();

    @FXML
    public TableView<Produto> TabelaListaProduto;
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
    public MenuItem menu;

    @FXML
    public void initialize() throws SQLException {
        conexaoDB.conection();
        try {
            listarProduto();
        } catch (SQLException e) {
            ConexaoDB.showAlert("Erro", "Não foi possível listar os produtos: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void meuUsuarioClicked(ActionEvent actionEvent) {
    }

    @FXML
    public void sairUsuarioClicked(ActionEvent event) throws IOException {
        helloApplication.loadScreen("paginaMeuUsuario.fxml", "Empréstimo VK",mainScene );
    }

    @FXML
    public void meusPedidosClicked(ActionEvent actionEvent) {
    }

    @FXML
    public void meusProdutosClicked(ActionEvent actionEvent) {
    }

    @FXML
    public void sairAplicacaoClicked(ActionEvent actionEvent) {
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
    public void sairContaClicked(ActionEvent event) {
        helloApplication.loadScreen("paginaLogin.fxml", "Empréstimo VK", mainScene);
    }

    public void listarProduto() throws SQLException {
        String query = "SELECT * FROM produto p ORDER BY quantidadeDeEmprestimos ASC;";
        try (Connection conn = ConexaoDB.conn;
             PreparedStatement smt = conn.prepareStatement(query);
             ResultSet rs = smt.executeQuery()) {

            produtos.clear();

            while (rs.next()) {
                Produto produto = new Produto(
                        rs.getString("nome"),
                        rs.getString("categoria_Produto"),
                        rs.getString("descricao"),
                        rs.getInt("quantidadeDeEmprestimos"),
                        rs.getDouble("preco"),
                        rs.getInt("id"),
                        rs.getString("situacao")
                );
                produtos.add(produto);
            }

            TabelaListaProduto.setItems(produtos);
        }
    }

    public void handleProductSelection(MouseEvent mouseEvent) throws Exception {
        Produto produto=TabelaListaProduto.getSelectionModel().getSelectedItem();

        if(produto == null){
            conexaoDB.showAlert("VK","O produto não existe!!", Alert.AlertType.ERROR);
            return;
        }

        Session.produto.setId(produto.getId());
        Session.produto.setNome(produto.getNome());
        Session.produto.setDescricao(produto.getDescricao());
        Session.produto.setPreco(produto.getPreco());
        Session.produto.setTipo(produto.getTipo());
        Session.produto.setQuantidadeDeEmprestimos(produto.getQuantidadeDeEmprestimos());
        Session.produto.setSituacao(produto.getSituacao());


        helloApplication.loadScreen("paginaProduto.fxml", "VK",mainScene);
    }

    public void CadastrarProduto() {
        helloApplication.loadScreen("paginaCadastroProduto.fxml","VK",mainScene);
    }

    public void pesquisarClicked() {
        Session.pesquisa=pesquisarText.getText();
        helloApplication.loadScreen("paginaPesquisa.fxml", "VK - Pesquisa", mainScene);
    }
}
