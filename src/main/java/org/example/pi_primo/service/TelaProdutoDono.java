package org.example.pi_primo.service;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.example.pi_primo.HelloApplication;
import org.example.pi_primo.config.ConexaoDB;
import org.example.pi_primo.model.Session;

import java.sql.SQLException;

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
        helloApplication.loadScreen("paginaEditarProduto.fxml","k",mainScene);
    }
}
