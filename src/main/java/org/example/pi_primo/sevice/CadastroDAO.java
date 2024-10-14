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

    public void Cadastrar(TextField nome, TextField cpf, TextField email, TextField telefone, TextField endereco, DatePicker data_nascimento) throws SQLException {
        String InserSQL = "INSERT INTO Cliente (nome, cpf, email, telefone, endereco, data_nascimento) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement InsertSMT = CadastroDAO.conn.prepareStatement(InserSQL);

        InsertSMT.setString(1, nome.getText());
        InsertSMT.setString(2, cpf.getText());
        InsertSMT.setString(3, email.getText());
        InsertSMT.setString(4, telefone.getText());
        InsertSMT.setString(5, endereco.getText());

        LocalDate localDate = data_nascimento.getValue();
        if (localDate != null) {
            InsertSMT.setDate(6, Date.valueOf(localDate));
        } else {
            InsertSMT.setNull(6, java.sql.Types.DATE);
        }

        InsertSMT.execute();

        System.out.println("Inserido com sucesso\n");
    }


}
