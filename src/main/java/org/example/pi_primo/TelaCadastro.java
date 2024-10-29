package org.example.pi_primo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.awt.*;
import java.net.URI;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.regex.Pattern;


import static org.example.pi_primo.HelloController.*;

public class TelaCadastro {

    HelloController hc= new HelloController();
    HelloApplication ha= new HelloApplication();

    @FXML public TextField usuarioText;
    @FXML public PasswordField senhaText;
    @FXML public TextField CPFText;
    @FXML public TextField emailText;
    @FXML public TextField dddText;
    @FXML public TextField telefoneText;
    @FXML public DatePicker nascimentoText;
    @FXML public TextField ruaText;
    @FXML public TextField ruaNumeroText;
    @FXML public TextField CEPText;
    @FXML public TextField bairroText;
    @FXML public TextField complementoText;
    @FXML public Button cadastrarButton;
    @FXML public Button voltarButton;
    @FXML public CheckBox CheckBoxcadastro;
    @FXML public Hyperlink termosHyperLink;
    @FXML public ChoiceBox<String> EstadoChoice;

    public void cadastrarClicked(ActionEvent event) throws SQLException {
        conection();
        String InserSQL = "INSERT INTO Cliente (nome, senha, cpf, Data_Nascimento, endereco, email, telefone) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement InsertSMT = conn.prepareStatement(InserSQL)) {

            String nome = usuarioText.getText();
            String cpf = CPFText.getText();
            String senha = senhaText.getText();
            String email = emailText.getText();

            String ddd = dddText.getText();
            String numeroTelefone = telefoneText.getText();

            String CEP = CEPText.getText();
            String rua = ruaText.getText();
            String numero = ruaNumeroText.getText();
            String bairro = bairroText.getText();
            String complemento = complementoText.getText();
            String Estado = (String) EstadoChoice.getValue();
            LocalDate nascimento = nascimentoText.getValue();
            System.out.println(nascimento);

            if (nome.isEmpty() ||
                    cpf.isEmpty() ||
                    senha.isEmpty() ||
                    email.isEmpty() ||
                    ddd.isEmpty() ||
                    numeroTelefone.isEmpty() ||
                    CEP.isEmpty() ||
                    rua.isEmpty() ||
                    numero.isEmpty() ||
                    bairro.isEmpty() ||
                    Estado.isEmpty() ||
                    nascimento == null) {
                showAlert("Campos vazios", "Por favor, preencha todos os campos.");
                return;
            }

            if (!CheckBoxcadastro.isSelected()) {
                showAlert("Aceite os termos!","Clique em cima de termos, leia, e aceite, clicando na caixinha ao lado de \"Termos\" em azul.");
                return;
            }

            StringBuilder stringBuilder =new StringBuilder();

            stringBuilder.  append(CEP).append(", ").
                            append(rua).append(", ").
                            append(numero).append(", ");


            if(!complemento.isEmpty()){
                stringBuilder.append(complemento).append(", ");
            }
            stringBuilder.  append(bairro).append(", ").
                            append(Estado).append('.');

            String endereco= stringBuilder.toString();

            stringBuilder.setLength(0);
            stringBuilder.append('(').append(ddd).append(')').append(numeroTelefone);

            String telefone=stringBuilder.toString();
            if(Pattern.matches("[a-z][A-Z]",cpf)){
                showAlert("Somente números","Coloque somente números no número de CPF.");
                return;
            }
            if(Pattern.matches("[a-z][A-Z]",telefone)){
                showAlert("Somente números","Coloque somente números no número de telefone");
                return;
            }
            if(Pattern.matches("[a-z][A-Z]",CEP)){
                showAlert("Somente números","Coloque somente números no número de telefone");
                return;
            }

            if(senha.length()<12 || senha.length()>32){
                showAlert("Sua senha não é válida","Temanho mínomo:12; Máximo: 32");
                return;
            }

            if(!Pattern.matches("[@]",email)){
                showAlert("Email inválido","Digite corretamente seu email: nome@vk.com; nome123@gmail.com; etc...");
                return;
            }

            InsertSMT.setString(1, nome);
            InsertSMT.setString(2, senha);
            InsertSMT.setString(3, cpf);
            InsertSMT.setDate(4, Date.valueOf(nascimento));
            InsertSMT.setString(5, endereco);
            InsertSMT.setString(6, email);
            InsertSMT.setString(7, telefone);

            InsertSMT.execute();
            showAlert("Cadastro bem-sucedido", "Cadastro de " + nome + " foi um sucesso!!!");
            ha.loadScreen("paginalogin.fxml", "VK", event);
            } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void voltarClicked(ActionEvent actionEvent) {
        ha.loadScreen("paginaLogin.fxml", "VK", actionEvent);
    }

    public void termosClicked(ActionEvent actionEvent) {
        try {
            Desktop.getDesktop().browse(new URI("https://docs.google.com/document/d/1r3z3w2721rs7Jqg07W7eNpVwm8qyVrQ9KSICxcLpeag/edit?usp=sharing"));
        } catch (Exception e){
        }
    }
}
