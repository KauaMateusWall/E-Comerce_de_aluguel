package org.example.pi_primo.service;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.example.pi_primo.HelloApplication;
import org.example.pi_primo.config.ConexaoDB;
import org.example.pi_primo.model.Session;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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
        NomeTXT.setText(Session.produto.getNome());
        PrecoTXT.setText(String.valueOf(Session.produto.getPreco()));
        CategoriaTXT.setText(Session.produto.getTipo());
        ProprietarioTXT.setText(Session.produto.getProprietario());
        DescricaoTXT.setText(Session.produto.getDescricao());

        CategoriaTXT.setDisable(true);
        ProprietarioTXT.setDisable(true);
    }

    public void editar(ActionEvent event) {
        String query = "UPDATE Produto " +
                "SET nome = ?, " +
                "descricao = ?, " +
                "preco = ? " +
                "WHERE id = ?";

        try (PreparedStatement stmt = conexaoDB.conn.prepareStatement(query)) {
            if (!validarCampos()) return;

            stmt.setString(1, NomeTXT.getText());
            stmt.setString(2, DescricaoTXT.getText());
            stmt.setDouble(3, Double.parseDouble(PrecoTXT.getText()));
            stmt.setInt(4, Session.produto.getId());

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
