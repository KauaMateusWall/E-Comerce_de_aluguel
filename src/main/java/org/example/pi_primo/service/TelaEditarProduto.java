package org.example.pi_primo.service;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.example.pi_primo.HelloApplication;
import org.example.pi_primo.config.ConexaoDB;
import org.example.pi_primo.config.Session;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.example.pi_primo.config.ConexaoDB.conn;

public class TelaEditarProduto {
    public Scene mainScene;
    HelloApplication helloApplication = new HelloApplication();
    ConexaoDB conexaoDB = new ConexaoDB();

    @FXML
    public TextField NomeTXT;
    @FXML
    public TextField PrecoTXT;
    @FXML
    public TextField CategoriaTXT;
    @FXML
    public TextField ProprietarioTXT;
    @FXML
    public TextField DescricaoTXT;

    @FXML
    public void initialize() {
        if (Session.produto != null) {
            NomeTXT.setText(Session.produto.getNome());
            PrecoTXT.setText(String.valueOf(Session.produto.getPreco()));
            CategoriaTXT.setText(Session.produto.getTipo());
            ProprietarioTXT.setText(Session.produto.getProprietario());
            DescricaoTXT.setText(Session.produto.getDescricao());

            ProprietarioTXT.setDisable(true);
        } else {
            conexaoDB.showAlert("Erro", "Produto não carregado corretamente!", Alert.AlertType.ERROR);
        }
    }


    public void editar(ActionEvent event) {
        conexaoDB.conection();
        String nome = NomeTXT.getText();
        String preco = PrecoTXT.getText();
        String descricao = DescricaoTXT.getText();
        String categoria =CategoriaTXT.getText();
        String id = String.valueOf(Session.produto.getId());


        String query = "UPDATE produto\n" +
                "SET nome = ?, preco = ?, descricao = ?\n" +
                "categoria_produto= ?\n" +
                "WHERE id = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            if (!validarCampos()) return;

            stmt.setString(1, nome);
            stmt.setString(2, preco);
            stmt.setString(3, descricao);
            stmt.setString(4, categoria);
            stmt.setString(5, id);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                conexaoDB.showAlert("Editado", "Produto editado com sucesso!", Alert.AlertType.INFORMATION);
            } else {
                conexaoDB.showAlert("Erro", "Produto não encontrado!", Alert.AlertType.ERROR);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            conexaoDB.showAlert("Erro", "Erro ao atualizar o produto: " + e.getMessage(), Alert.AlertType.ERROR);
        } catch (NumberFormatException e) {
            conexaoDB.showAlert("Erro", "Preço deve ser um valor numérico válido!", Alert.AlertType.ERROR);
        }
    }

    private boolean validarCampos() {
        if (NomeTXT.getText().isEmpty() || DescricaoTXT.getText().isEmpty() || PrecoTXT.getText().isEmpty()) {
            conexaoDB.showAlert("Erro", "Todos os campos devem ser preenchidos!", Alert.AlertType.ERROR);
            return false;
        }
        try {
            double preco = Double.parseDouble(PrecoTXT.getText());
            if (preco < 0) {
                conexaoDB.showAlert("Erro", "O preço não pode ser negativo!", Alert.AlertType.ERROR);
                return false;
            }
        } catch (NumberFormatException e) {
            conexaoDB.showAlert("Erro", "Preço deve ser um valor numérico válido!", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }
}
