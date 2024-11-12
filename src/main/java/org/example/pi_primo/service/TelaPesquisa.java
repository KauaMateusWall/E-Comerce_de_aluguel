package org.example.pi_primo.service;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.example.pi_primo.HelloApplication;
import static org.example.pi_primo.config.ConexaoDB.showAlert;
import static org.example.pi_primo.config.ConexaoDB.conn;
import org.example.pi_primo.model.Produto;
import org.example.pi_primo.model.Session;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TelaPesquisa {
    HelloApplication helloApplication=new HelloApplication();
    public Scene mainScene;
    public TextField pesquisaText;
    public TableView<Produto> produtosTableView;

    public void initialize(){
        if(Session.pesquisa.length()>=3) {
            pesquisaText.setText(Session.pesquisa);
            pesquisarClicked();
        }
    }

    public void voltarClicked() {
        helloApplication.loadScreen("paginaMenu.fxml","VK", mainScene);
    }

    public void pesquisarKeyPressed(KeyEvent keyEvent) {
        if(keyEvent.getCode()== KeyCode.ENTER)
            pesquisarClicked();
    }

    public void pesquisarClicked() {
        produtosTableView.getItems().clear();
        Session.pesquisa= pesquisaText.getText();
        if(Session.pesquisa.length()<3){
            showAlert("Pesquisa inválida","Pelo menos 3 caracteres na pesquisa são necessários",
                    Alert.AlertType.WARNING);
            return;
        }

        String SELECT="SELECT * FROM produtos WHERE nome LIKE ? or tipo LIKE ? and situação=\"Disponível\";";
        try(PreparedStatement stmt=conn.prepareStatement(SELECT)) {

            String pesquisaTexto = "%"+Session.pesquisa+"%";

            stmt.setString(1,pesquisaTexto);
            stmt.setString(2,pesquisaTexto);

            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                int id= rs.getInt("id");
                String nome=rs.getString("nome");
                String tipo= rs.getString("tipo");
                double preco=rs.getDouble("preco");
                int quantidadeDeEmprestimos = rs.getInt("quantidadeDeEmprestimos");
                String situcao =rs.getString("situação");
                String descricao =rs.getString("descricao");
                int Proprietario = rs.getInt("Proprietario");

                Produto produto= new Produto(id,nome, tipo, descricao ,quantidadeDeEmprestimos, preco, situcao, Proprietario);
                produtosTableView.getItems().add(produto);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
