package org.example.pi_primo.service;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.example.pi_primo.HelloApplication;

import static org.example.pi_primo.HelloApplication.produtoQuery;
import static org.example.pi_primo.config.ConexaoDB.showAlert;
import static org.example.pi_primo.config.ConexaoDB.conn;

import org.example.pi_primo.config.ConexaoDB;
import org.example.pi_primo.model.Produto;
import org.example.pi_primo.model.Session;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;


public class TelaPesquisa {

    ConexaoDB conexaoDB=new ConexaoDB();
    HelloApplication helloApplication = new HelloApplication();
    public Scene mainScene;
    public TextField pesquisaText;
    public TableView<Produto> produtosTableView;

    public void initialize() throws SQLException {
        if(Objects.equals(Session.pesquisa, "")){
            pesquisarClicked();
            return;
        }

        if (Session.pesquisa.length() >= 3) {
            pesquisaText.setText(Session.pesquisa);
            pesquisarClicked();
        }
    }

    public void voltarClicked() {
        Session.pesquisa = "";
        helloApplication.loadScreen("paginaMenu.fxml", "VK", mainScene);
    }

    public void pesquisarKeyPressed(KeyEvent keyEvent) throws SQLException {
        if (keyEvent.getCode() == KeyCode.ENTER)
            pesquisarClicked();
    }

    public void pesquisarClicked() throws SQLException {
        produtosTableView.getItems().clear();
        Session.pesquisa = pesquisaText.getText();
        if (Session.pesquisa.length() < 3) {
            showAlert("Pesquisa inválida", "Pelo menos 3 caracteres na pesquisa são necessários",
                    Alert.AlertType.WARNING);
            return;
        }
        try {
            conexaoDB.conection() ;
            PreparedStatement stmt = conn.prepareStatement(produtoQuery);
            String pesquisaTexto = "%" + Session.pesquisa + "%";

            stmt.setString(1, pesquisaTexto);
            stmt.setString(2, pesquisaTexto);

            ResultSet rs = stmt.executeQuery();
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
                produtosTableView.getItems().add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexaoDB.closeConection();
        }
    }

    public void handleProductSelection() {
        Produto produto = produtosTableView.getSelectionModel().getSelectedItem();
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

        helloApplication.loadScreen("paginaProduto.fxml", "VK", mainScene);
    }
}
