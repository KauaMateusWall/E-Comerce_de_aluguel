package org.example.pi_primo.service;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.pi_primo.HelloApplication;
import org.example.pi_primo.config.ConexaoDB;
import org.example.pi_primo.config.Session;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.example.pi_primo.config.ConexaoDB.conn;

public class TelaProdutoDono {

    public Scene mainScene;
    HelloApplication helloApplication = new HelloApplication();
    ConexaoDB conexaoDB = new ConexaoDB();

    @FXML
    public Button BotaoAlugar;
    @FXML
    public Label DescricaoTXT;
    @FXML
    public Label ProprietarioXT;
    @FXML
    public Label CategoriaTXT;
    @FXML
    public Label PrecoTXT;
    @FXML
    public Label nomeTXT;

    @FXML
    public void initialize() throws SQLException {
        PrecoTXT.setText(String.valueOf(Session.produto.getPreco()));
        nomeTXT.setText(Session.produto.getNome());
        ProprietarioXT.setText(Session.produto.getProprietario());
        DescricaoTXT.setText(Session.produto.getDescricao());
        CategoriaTXT.setText(Session.produto.getTipo());

        if(Session.usuario.getid() == Session.produto.getidProprietario()){
            BotaoAlugar.setText("Você é o dono!!");
            BotaoAlugar.setDisable(true);
        }
    }


    public void paginaEditar(ActionEvent event) {
        helloApplication.openScreen("paginaEditarProduto.fxml","k",mainScene);
    }

    public void deletar(ActionEvent event) throws SQLException {

        if(Session.produto.getSituacao().equals("Indisponível")){
            conexaoDB.showAlert("Produto já emprestado","O produto está emprestado!", Alert.AlertType.ERROR);
            return;
        }

        conexaoDB.conection();
        String id = String.valueOf(Session.produto.getId());

        String query = "delete from produto where id = ?";
        PreparedStatement smt = conn.prepareStatement(query);

        smt.setString(1, id);
        smt.execute();

        Stage currentStage = (Stage) mainScene.getWindow();
        currentStage.close();
    }
}
