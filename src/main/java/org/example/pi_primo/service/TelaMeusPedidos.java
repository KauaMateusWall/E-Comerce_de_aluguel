package org.example.pi_primo.service;

import javafx.scene.Scene;
import javafx.scene.control.TableView;
import org.example.pi_primo.HelloApplication;
import org.example.pi_primo.config.ConexaoDB;
import org.example.pi_primo.model.Emprestimo;
import org.example.pi_primo.model.Session;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.example.pi_primo.config.ConexaoDB.conn;

public class TelaMeusPedidos {
    public TableView<Emprestimo> pedidoTable;
    HelloApplication helloApplication = new HelloApplication();
    ConexaoDB conexaoDB = new ConexaoDB();
    public Scene mainScene;

    public void initialize(){
        String query =
                "SELECT  e.id AS id, e.data_emprestimo AS data, e.data_devolucao AS devolucao," +
                        "u2.nome AS fornecedor, u2.id AS id_fornecedor," +
                        "p.id as id_produto FROM emprestimo e " +
                        "INNER JOIN usuario usuario ON ?=e.id_cliente_receptor " +
                        "INNER JOIN usuario fornecedor" +
                        "INNER JOIN produto p ";

        try(PreparedStatement pstmt=conn.prepareStatement(query)){
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
                    situacao="devolvido";
                }

                Emprestimo emprestimo=new Emprestimo(data, devolucao, id, id_fornecedor,id_produto, fornecedor, produto,situacao);


            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void pedidoTabelaClicked() {


    }
}