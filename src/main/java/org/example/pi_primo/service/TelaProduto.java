package org.example.pi_primo.service;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.example.pi_primo.HelloApplication;
import org.example.pi_primo.config.ConexaoDB;
import org.example.pi_primo.model.Session;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.example.pi_primo.config.ConexaoDB.conn;
import static org.example.pi_primo.config.ConexaoDB.showAlert;

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


        String queryProduct = "SELECT p.situacao, p.Proprietario, e.id_cliente_receptor FROM produto p LEFT JOIN emprestimo e ON e.id_produto = p.id WHERE p.id = ? ;";
        try{
            helloController.conection();
            PreparedStatement pstmt = conn.prepareStatement(queryProduct);

            pstmt.setInt(1,Session.produto.getId());
            ResultSet rs = pstmt.executeQuery();
            if(!rs.next()){
                return;
            }
            if(rs.getString(1).equals("Indisponível")){
                tempoText.setDisable(true);
                tempoText.setText("Já alugado!");
                alugarButton.setDisable(true);
                alugarButton.setText("Indisponível");
            }
            if(rs.getInt(2)!=0 && rs.getInt(2)==Session.usuario.getid()){
                tempoText.setDisable(true);
                tempoText.setText("Você é o dono!");
                alugarButton.setDisable(true);
                alugarButton.setText("Dono");
            }
            if(rs.getInt(3)!=0 && rs.getInt(3)==Session.usuario.getid()){
                tempoText.setDisable(true);
                tempoText.setText("Em seus pedidos!");
                alugarButton.setDisable(true);
                alugarButton.setText("Usando...");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            helloController.closeConection();
        }
    }

    @FXML
    public void alugarClicked() {

        String testPedido="SELECT * FROM emprestimo WHERE id_cliente_fornecedor=?;";

        try(PreparedStatement pstmt = conn.prepareStatement(testPedido)){
            pstmt.setInt(1,Session.produto.getidProprietario());
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                initialize();
                showAlert("VK","O produto já foi alugado, desculpe!", Alert.AlertType.ERROR);
                return;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        String INSERTPedido="INSERT INTO emprestimo (id_cliente_fornecedor," +
                " id_cliente_receptor," +
                " id_produto, data_emprestimo," +
                " data_devolucao) " +
                "VALUES (?, ?, ?, NOW(), ?);";
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

    public void mesesmask(KeyEvent mouseEvent) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("##");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(tempoText);
        tff.formatter();
    }
}
