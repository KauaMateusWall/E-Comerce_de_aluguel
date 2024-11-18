package org.example.pi_primo.service;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.pi_primo.HelloApplication;
import org.example.pi_primo.config.ConexaoDB;
import org.example.pi_primo.model.Session;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.example.pi_primo.config.ConexaoDB.conn;

public class TelaProduto {

    public Button alugarButton;
    public TextField tempoText;
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
        ProTXT.setText(Session.produto.getProprietario());
        DescricaoTXT.setText(Session.produto.getDescricao());


        String queryProduct = "SELECT p.situacao, p.Proprietario, e.id_cliente_receptor from produto p INNER JOIN " +
                "emprestimo e ON e.id_produto=? WHERE p.id=?;";
        try{
            helloController.conection();
            PreparedStatement pstmt = conn.prepareStatement(queryProduct);

            pstmt.setInt(1,Session.produto.getId());
            pstmt.setInt(2,Session.produto.getId());
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            if(rs.getString(1).equals("Indisponível")){
                tempoText.setDisable(true);
                tempoText.setText("Já alugado!");
                alugarButton.setDisable(true);
                alugarButton.setText("Indisponível");
            }
            if(rs.getInt(2)==Session.usuario.getid()){
                tempoText.setDisable(true);
                tempoText.setText("Você é o dono!");
                alugarButton.setDisable(true);
                alugarButton.setText("Dono");
            }
            if(rs.getInt(3)==Session.usuario.getid()){
                tempoText.setDisable(true);
                tempoText.setText("Em seus pedidos!");
                alugarButton.setDisable(true);
                alugarButton.setText("Usando...");
            }
            helloController.closeConection();

        } catch (SQLException e) {
            e.printStackTrace();
            helloController.closeConection();
        }
    }

    @FXML
    public void alugarClicked() {
        String INSERTPedido="INSERT INTO emprestimo (id_cliente_fornecedor, id_cliente_receptor, id_produto, data_emprestimo, data_devolucao) " +
                "VALUES (?, ?, ?, NOW(), ?);";
        String testPedido="SELECT * FROM emprestimo WHERE id_cliente_fornecedor=?;";

        try(PreparedStatement pstmt = conn.prepareStatement(testPedido)){
            pstmt.setInt(1,Session.produto.getidProprietario());
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){

            }
        }catch (SQLException e){
            e.printStackTrace();
        }


        try(PreparedStatement pstmt = conn.prepareStatement(INSERTPedido)){
            long meses=Long.parseLong(tempoText.getText());
            pstmt.setInt(1,Session.produto.getidProprietario());
            pstmt.setInt(2,Session.usuario.getid());
            pstmt.setInt(3,Session.produto.getId());
            pstmt.setDate(4, java.sql.Date.valueOf(LocalDate.now().plusMonths(meses)));

            pstmt.executeUpdate();
        } catch (SQLException | NumberFormatException e){
            e.printStackTrace();
        }

    }

    @FXML
    public void voltarClicked() {
        if(Session.pesquisa.length()>=3){
            helloApplication.loadScreen("paginaPesquisa.fxml","VK - Pesquisa",mainScene);
        }
        helloApplication.loadScreen("paginaMenu.fxml","Empréstimo VK - Menu",mainScene);
    }

    private void atualizarPagina(){
        helloApplication.loadScreen("paginaProduto.fxml", "VK", mainScene);
    }
}
