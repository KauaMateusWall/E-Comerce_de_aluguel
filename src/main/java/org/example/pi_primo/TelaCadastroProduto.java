package org.example.pi_primo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static org.example.pi_primo.HelloController.*;

public class TelaCadastroProduto {

    HelloApplication helloAplication = new HelloApplication();

    public Button cadastrarButton;
    public Button voltarButton;
    public TextField precoText;
    public TextField nomeText;
    public TextArea descricaoText;
    public ChoiceBox<String> tipoChoiceBox;

    public void voltarClicked(ActionEvent event) {
        helloAplication.loadScreen("paginaLogin.fxml","VK",event);
    }

    public void cadastrarClicked(ActionEvent actionEvent) throws SQLException {
        HelloController helloController = new HelloController();
        try {
            helloController.conection();
            String InserSQL = "INSERT INTO Produto (nome, descricao, categoria_Produto, preco) VALUES (?, ?, ?, ?)";
            try (PreparedStatement InsertSMT = conn.prepareStatement(InserSQL)) {
                String nome = nomeText.getText();
                String preco = precoText.getText();
                String descricao = descricaoText.getText();
                String tipo = (String) tipoChoiceBox.getValue();

                if (nome.isEmpty() || preco.isEmpty() || descricao.isEmpty() || tipo == null) {
                    showAlert("Campos vazios", "Por favor, preencha todos os campos.");
                    return;
                }

                double precoValue;
                try {
                    precoValue = Double.parseDouble(preco);
                } catch (NumberFormatException e) {
                    showAlert("Erro no Preço", "Por favor, insira um valor numérico válido para o preço.");
                    return;
                }

                InsertSMT.setString(1, nome);
                InsertSMT.setString(2, descricao);
                InsertSMT.setString(3, tipo);
                InsertSMT.setDouble(4, precoValue);

                InsertSMT.execute();
                showAlert("Cadastro bem-sucedido", "Cadastro de " + nome + " foi um sucesso!!!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            helloController.closeConection();
        }
    }
}
