package org.example.pi_primo.service;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import org.example.pi_primo.config.ConexaoDB;
import org.example.pi_primo.model.Session;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.example.pi_primo.config.ConexaoDB.conn;

public class TelaProduto {

    public TextField tempoText;
    ConexaoDB conexaoDB = new ConexaoDB();

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
    public Button alugarButton;

    public boolean atualizarSituacao(){
        String testAlugado="SELECT * FROM emprestimo WHERE id_produto=? AND data_devolucao>NOW();";
        try(PreparedStatement pstmt= conn.prepareStatement(testAlugado)) {
            pstmt.setInt(1,Session.produto.getId());
            ResultSet rs=pstmt.executeQuery();

            if(!rs.next()){
                return true;
            }
            int id_cliente_receptor=rs.getInt("id_cliente_receptor");
            if(id_cliente_receptor==Session.usuario.getid()) {
                tempoText.setDisable(true);
                tempoText.setText("Em seus pedidos!");
                alugarButton.setDisable(true);
                alugarButton.setText("Usando...");
            } else{
                tempoText.setDisable(true);
                tempoText.setText("Já alugado!");
                alugarButton.setDisable(true);
                alugarButton.setText(Session.produto.getSituacao());
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

    @FXML
    public void initialize() throws SQLException {
        conexaoDB.conection();
        PrecoTXT.setText(String.valueOf(Session.produto.getPreco()));
        NomeTXT.setText(Session.produto.getNome());
        ProTXT.setText(Session.produto.getProprietario());
        DescricaoTXT.setText(Session.produto.getDescricao());

        if(Session.produto.getidProprietario()!=0 && Session.produto.getidProprietario()==Session.usuario.getid()){
            tempoText.setDisable(true);
            tempoText.setText("Você é o dono!");
            alugarButton.setDisable(true);
            alugarButton.setText("Dono");
        }
        atualizarSituacao();
    }

    @FXML
    public void alugarClicked() throws SQLException {
        if(tempoText.getText().isEmpty()){
            conexaoDB.showAlert("Necessário a quantidade","Coloque a quantidade de meses que se deseja alugar no campo acima do botão \"Alugar\"!", Alert.AlertType.WARNING);
            return;
        }

        conexaoDB.conection();
        if(atualizarSituacao()){
            conexaoDB.showAlert("Já foi alugado!","O produto já foi alugado por outra pessoa", Alert.AlertType.WARNING);
            return;
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

        String UPDATEProduto="UPDATE produto SET situacao=\"Indisponível\" quantidadeDeEmprestimos=? WHERE id=?;";
        try(PreparedStatement pstmt= conn.prepareStatement(UPDATEProduto)){
            pstmt.setInt(1,Session.produto.getId());
            pstmt.setInt(1,Session.produto.getQuantidadeDeEmprestimos()+1);
            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        initialize();
    }

    public void mesesmask(KeyEvent mouseEvent) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("##");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(tempoText);
        tff.formatter();
    }
}
