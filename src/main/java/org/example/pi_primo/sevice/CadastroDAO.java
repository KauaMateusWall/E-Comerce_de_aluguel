package org.example.pi_primo.sevice;


import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.example.pi_primo.HelloAplication;
import org.example.pi_primo.HelloController;

import java.sql.*;
import java.time.LocalDate;

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

    public void Cadastrar(String nome, String cpf, String email, String telefone, String endereco, LocalDate data_nascimento) throws SQLException {
        conection();
        String InserSQL = "INSERT INTO Cliente (nome, cpf, email, telefone, endereco, data_nascimento) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement InsertSMT = conn.prepareStatement(InserSQL);

        InsertSMT.setString(1, nome);
        InsertSMT.setString(2, cpf);
        InsertSMT.setString(3, email);
        InsertSMT.setString(4, telefone);
        InsertSMT.setString(5, endereco);

        if (data_nascimento != null) {
            InsertSMT.setDate(6, Date.valueOf(data_nascimento));
        } else {
            InsertSMT.setNull(6, java.sql.Types.DATE);
        }

        InsertSMT.execute();

        System.out.println("Inserido com sucesso\n");
    }


}
