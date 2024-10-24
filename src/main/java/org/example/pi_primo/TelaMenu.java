package org.example.pi_primo;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import org.example.pi_primo.model.Produto;

import java.sql.*;

import static org.example.pi_primo.HelloController.conn;
import static org.example.pi_primo.HelloController.showAlert;

public class TelaMenu {

    @FXML public Button meusProdutosButton;
    @FXML public Button meusPedidosButton;
    @FXML public Button pesquisarProdutosButton;
    @FXML public Button meuUsuarioButton;

    @FXML public void initialize() {
        String listProdutos ="SELECT count(emprestimo.id_produto) as counta, produto.* FROM produto " +
                "join emprestimo on emprestimo.id_produto=produto.id " +
                "group by produto.id " +
                "order by conta ASC " +
                "limit 10;";
        try(CallableStatement stmt= conn.prepareCall(listProdutos)){
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                String nome = rs.getString("nome");
                double preco= rs.getDouble("preco");


            }

        } catch(SQLException e){
            showAlert("Bah mano, não rolou o sql ;-;","HAHAHAHAHHAHHA, otário, faz de novo!");
        }

        ObservableList<Produto> produtos;

    }


    @FXML
    public Insets ListaProdutoMenu;
    public MenuItem menu;

    @FXML
    public void meuUsuarioClicked(ActionEvent actionEvent) {
    }

    @FXML
    public void pesquisarProdutosClicked(ActionEvent actionEvent) {
    }

    @FXML
    public void meusPedidosClicked(ActionEvent actionEvent) {
    }

    @FXML
    public void meusProdutosClicked(ActionEvent actionEvent) {
    }


    @FXML public Button sairAplicacaoButton;
    @FXML public Button sairContaButton;

    @FXML
    public void sairApicacaoClicked(ActionEvent actionEvent) {
        System.exit(0);
    }

    @FXML
    public void sairContaClicked(ActionEvent event) {
        loadScreen("paginaLogin.fxml","Empréstimo VK",event);

    }

    public void loadScreen(String fxmlFile, String title,ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Stage stage = (Stage) menu.getParentPopup().getOwnerWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
