package org.example.pi_primo.service;

import javafx.scene.Scene;
import javafx.scene.control.TableView;
import org.example.pi_primo.config.ConexaoDB;
import org.example.pi_primo.model.Emprestimo;
import org.example.pi_primo.config.Session;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.example.pi_primo.config.ConexaoDB.conn;

public class TelaMeusPedidos {
    ConexaoDB conexaoDB=new ConexaoDB();
    public TableView<Emprestimo> pedidoTable;
    public Scene mainScene;

    public void initialize() throws SQLException {
        conexaoDB.conection();
        String query =
        "SELECT e.id AS id, e.data_emprestimo AS data, e.data_devolucao AS devolucao, f.nome AS fornecedor, f.id AS id_fornecedor, p.id AS id_produto, p.nome AS produto FROM emprestimo e INNER JOIN cliente u ON u.id=e.id_cliente_receptor INNER JOIN cliente f ON e.id_cliente_fornecedor=f.id INNER JOIN produto p ON p.id=e.id_produto WHERE id_cliente_receptor=?";

        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setInt(1, Session.usuario.getid());
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                int id=rs.getInt("id");
                Date data=rs.getDate("data");
                Date devolucao=rs.getDate("devolucao");
                String fornecedor=rs.getString("fornecedor");
                int id_fornecedor=rs.getInt("id_fornecedor");
                String produto=rs.getString("produto");
                int id_produto=rs.getInt("id_produto");

                String situacao;
                if(devolucao.toLocalDate().isAfter(LocalDate.now())){
                    situacao="utilizando";
                } else {
                    situacao="completo";
                }

                Emprestimo emprestimo=new Emprestimo(data, devolucao, id, id_fornecedor,id_produto, fornecedor, produto,situacao);
                pedidoTable.getItems().add(emprestimo);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void pedidoTabelaClicked() {
    }
}