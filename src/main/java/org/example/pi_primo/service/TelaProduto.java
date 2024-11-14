package org.example.pi_primo.service;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.example.pi_primo.HelloApplication;
import org.example.pi_primo.config.ConexaoDB;
import org.example.pi_primo.model.Session;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.example.pi_primo.config.ConexaoDB.conn;

public class TelaProduto {

    public Button alugarButton;
    HelloApplication helloApplication = new HelloApplication();
    ConexaoDB helloController = new ConexaoDB();

    @FXML
    public Scene mainScene;
    @FXML
    public Label NomeTXT;
    @FXML
    public Label PrecoTXT;
    @FXML
    public Label ProTXT;
    @FXML
    public Label DescricaoTXT;


    @FXML
    public void initialize() throws SQLException {
        PrecoTXT.setText(String.valueOf(Session.produto.getPreco()));
        NomeTXT.setText(Session.produto.getNome());
        ProTXT.setText(Session.usuario.getNome());
        DescricaoTXT.setText(Session.produto.getDescricao());


        String queryProduct = "SELECT situacao, Proprietario from produto where id=?;";
        try{
            helloController.conection();
            PreparedStatement pstmt = conn.prepareStatement(queryProduct);

            pstmt.setInt(1,Session.produto.getId());
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            if(rs.getString(1).equals("Indisponível")){
                alugarButton.setDisable(true);
                alugarButton.setText("Indisponível");
            }
            if(rs.getInt(2)==Session.usuario.getid()){
                alugarButton.setDisable(true);
                alugarButton.setText("Dono");
            }
            helloController.closeConection();

        } catch (SQLException e) {
            e.printStackTrace();
            helloController.closeConection();
        }
    }

    @FXML
    public void alugarClicked() {

    }

    @FXML
    public void voltarClicked(ActionEvent event) {
        helloApplication.loadScreen("paginaMenu.fxml","Empréstimo VK - Menu",mainScene);
    }
}
