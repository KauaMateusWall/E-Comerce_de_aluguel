package org.example.pi_primo.service;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.example.pi_primo.HelloApplication;
import org.example.pi_primo.config.ConexaoDB;
import org.example.pi_primo.model.Produto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TelaPesquisa {
    HelloApplication helloApplication=new HelloApplication();
    public Scene mainScene;
    public TextField pesquisaText;
    public TableView produtosTableView;
    public Pagination pagination;

    public void initialize(){
        Stage stage =(Stage) mainScene.getWindow();
        pesquisaText.setText((String) stage.getUserData());
        pesquisarClicked();
    }

    public void voltarClicked(ActionEvent actionEvent) {
        helloApplication.loadScreen("paginaMenu.fxml","VK", mainScene);
    }

    public void pesquisarKeyPressed(KeyEvent keyEvent) {
        if(keyEvent.getCode()== KeyCode.ENTER)
            pesquisarClicked();
    }

    public void pesquisarClicked() {
        produtosTableView.getItems().clear();

        String SELECT="SELECT * FROM produtos WHERE nome LIKE %?% and situação=\"DISPONÍVEL\";";
        try(PreparedStatement stmt=ConexaoDB.conn.prepareStatement(SELECT)){

            stmt.setString(1,pesquisaText.getText());

            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                int id= rs.getInt("id");
                String nome=rs.getString("nome");
                String tipo= rs.getString("tipo");
                double preco=rs.getDouble("preco");
                int quantidadeDeEmprestimos = rs.getInt("quantidadeDeEmprestimos");
                String situcao =rs.getString("situação");
                String descricao =rs.getString("descricao");

                Produto produto= new Produto(nome,tipo, descricao, quantidadeDeEmprestimos,preco,id, situcao);

                produtosTableView.getItems().add(produto);
            }
        } catch (SQLException e){

        }
    }
}
