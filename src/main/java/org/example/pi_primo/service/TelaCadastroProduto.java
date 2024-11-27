package org.example.pi_primo.service;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import org.example.pi_primo.HelloApplication;
import org.example.pi_primo.config.ConexaoDB;
import org.example.pi_primo.model.Session;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import static org.example.pi_primo.config.ConexaoDB.*;

public class TelaCadastroProduto {

    public Scene mainScene;
    HelloApplication helloAplication = new HelloApplication();
    ConexaoDB conexaoDB = new ConexaoDB();

    public Button cadastrarButton;
    public Button voltarButton;
    public TextField precoText;
    public TextField nomeText;
    public TextArea descricaoText;
    public ChoiceBox<String> tipoChoiceBox;

    public void cadastrarClicked(ActionEvent actionEvent) throws SQLException {
        ConexaoDB helloController = new ConexaoDB();
        try {
            helloController.conection();
            String InserSQL = "INSERT INTO Produto (nome, descricao, categoria_Produto, situacao, preco,  Proprietario, quantidadeDeEmprestimos) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement InsertSMT = conn.prepareStatement(InserSQL)) {
                String nome = nomeText.getText();
                String preco = precoText.getText();
                String descricao = descricaoText.getText();
                String categoria = tipoChoiceBox.getValue();


                if (nome.isEmpty() || preco.isEmpty() || descricao.isEmpty() || categoria == null) {
                    conexaoDB.showAlert("Campos vazios", "Por favor, preencha todos os campos.", Alert.AlertType.ERROR);
                    return;
                }

                double precoValue;
                try {
                    precoValue = Double.parseDouble(preco);
                } catch (NumberFormatException e) {
                    conexaoDB.showAlert("Erro no Preço", "Por favor, insira um valor numérico válido para o preço.", Alert.AlertType.ERROR);
                    return;
                }

                InsertSMT.setString(1, nome);
                InsertSMT.setString(2, descricao);
                InsertSMT.setString(3, categoria);
                InsertSMT.setString(4, "Disponível");
                InsertSMT.setString(5,preco);
                InsertSMT.setInt(6, Session.usuario.getid());
                InsertSMT.setInt(7, 0);


                InsertSMT.execute();
                conexaoDB.showAlert("Cadastro bem-sucedido", "Cadastro de " + nome + " foi um sucesso!!!", Alert.AlertType.ERROR);

                nomeText.clear();
                precoText.clear();
                descricaoText.clear();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void precoMask(KeyEvent keyEvent) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("###");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(precoText);
        tff.formatter();
    }
}
