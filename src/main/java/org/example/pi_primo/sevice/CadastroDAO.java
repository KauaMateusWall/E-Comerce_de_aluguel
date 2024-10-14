package org.example.pi_primo.sevice;


import org.example.pi_primo.HelloAplication;
import org.example.pi_primo.HelloController;

import java.sql.*;

public class CadastroDAO {
    private static final String URL = ("jdbc:mysql://localhost:3306/PI_Primo");
    private static final String USER = ("root");
    private static final String PASSWORD = ("0000");
    public static Connection conn;

    public static void conection() throws SQLException {
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void closeConection() throws SQLException {
        conn.close();
    }

    public void Cadastrar(String nome, int cpf, String email, int telefone, String endereco, Date data_nascimento) throws SQLException {
        String InserSQL = "INSERT INTO Cliente (nome, cpf, email, telefone, endereco, data_nascimento) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement InsertSMT = CadastroDAO.conn.prepareStatement(InserSQL);
        InsertSMT.setString(1,nome);
        InsertSMT.setInt(2, cpf);
        InsertSMT.setString(3, email);
        InsertSMT.setInt(4, telefone);
        InsertSMT.setString(5, endereco);
        InsertSMT.setDate(6, data_nascimento);
        InsertSMT.execute();
        System.out.println("Inserido com sucesso\n");


    }
}
