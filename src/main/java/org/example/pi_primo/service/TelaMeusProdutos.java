package org.example.pi_primo.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.pi_primo.HelloApplication;
import org.example.pi_primo.config.ConexaoDB;
import org.example.pi_primo.model.Produto;
import org.example.pi_primo.model.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TelaMeusProdutos {
    public Scene mainScene;
    HelloApplication helloApplication = new HelloApplication();
    ConexaoDB conexaoDB = new ConexaoDB();

    private final ObservableList<Produto> produtos = FXCollections.observableArrayList();

    @FXML
    private TableView<Produto> TabelaListaProduto;
    @FXML
    private TableColumn<Produto, Integer> colunaId;
    @FXML
    private TableColumn<Produto, String> colunaNome;
    @FXML
    private TableColumn<Produto, Integer> colunaPreco;
    @FXML
    private TableColumn<Produto, String> colunaCategoria;
    @FXML
    private TableColumn<Produto, Integer> colunaQuantidadeDeEmprestimos;
    @FXML
    private TableColumn<Produto, String> colunaSituacao;

    @FXML
    public void initialize() throws SQLException {
        conexaoDB.conection();
        configurarColunas();
        try {
            listarProduto();
        } catch (SQLException e) {
            ConexaoDB.showAlert("Erro", "Não foi possível listar os produtos: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void configurarColunas() {
        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        colunaCategoria.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colunaQuantidadeDeEmprestimos.setCellValueFactory(new PropertyValueFactory<>("quantidadeDeEmprestimos"));
        colunaSituacao.setCellValueFactory(new PropertyValueFactory<>("situacao"));
    }

    public void listarProduto() throws SQLException {
        String query = "select * from produto p where Proprietario = ?";
        try (Connection conn = ConexaoDB.conn;
             PreparedStatement smt = conn.prepareStatement(query);)
              {
                  smt.setInt(1,Session.usuario.getid());
                  ResultSet rs = smt.executeQuery();
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

    @FXML
    public void handleProductSelection() {
        Produto produto = TabelaListaProduto.getSelectionModel().getSelectedItem();

        if (produto == null) {
            ConexaoDB.showAlert("VK", "Nenhum produto selecionado!", Alert.AlertType.ERROR);
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
        Session.produto.setIdProprietario(produto.getidProprietario());

        helloApplication.loadScreen("paginaProduto.fxml", "VK", mainScene);
    }

}
