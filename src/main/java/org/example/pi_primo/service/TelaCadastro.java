package org.example.pi_primo.service;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.example.pi_primo.HelloApplication;
import org.example.pi_primo.config.ConexaoDB;

import java.awt.*;
import java.net.URI;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.regex.Pattern;


import static org.example.pi_primo.config.ConexaoDB.*;

public class TelaCadastro {

    public Scene mainScene;
    ConexaoDB hc= new ConexaoDB();
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
    @FXML public CheckBox CheckBoxcadastro;
    @FXML public Hyperlink termosHyperLink;
    @FXML public ChoiceBox<String> EstadoChoice;

    public void cadastrarClicked() {
        hc.conection();

        String testUsuario="SELECT nome FROM cliente WHERE nome LIKE ? OR cpf LIKE ?;";
        try(PreparedStatement pstmt = conn.prepareStatement(testUsuario)){
           String cpf='%'+CPFText.getText()+'%';
           String nome='%'+usuarioText.getText()+'%';
           pstmt.setString(1,nome);
           pstmt.setString(2,cpf);

           ResultSet rs=pstmt.executeQuery();

           if(rs.next()){
               hc.showAlert("Usuario já existe","O nome de usuário ja existe, ou o cpf já esta sendo usado", Alert.AlertType.ERROR);
               return;
           }
        } catch (SQLException e){
            e.printStackTrace();
        }

        String InserSQL = "INSERT INTO Cliente (nome, senha, cpf, Data_Nascimento, endereco, email, telefone) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement InsertSMT = conn.prepareStatement(InserSQL)) {

            String nome = usuarioText.getText();
            String cpf = CPFText.getText();
            String senha = senhaText.getText();
            String email = emailText.getText();

            String numeroTelefone = telefoneText.getText();

            String CEP = CEPText.getText();
            String rua = ruaText.getText();
            String numero = ruaNumeroText.getText();
            String bairro = bairroText.getText();
            String complemento = complementoText.getText();
            String Estado =  EstadoChoice.getValue();
            LocalDate nascimento = nascimentoText.getValue();
            System.out.println(nascimento);

            if (nome.isEmpty() ||
                    cpf.isEmpty() ||
                    senha.isEmpty() ||
                    email.isEmpty() ||
                    numeroTelefone.isEmpty() ||
                    CEP.isEmpty() ||
                    rua.isEmpty() ||
                    numero.isEmpty() ||
                    bairro.isEmpty() ||
                    Estado.isEmpty() ||
                    nascimento == null) {
                showAlert("Campos vazios", "Por favor, preencha todos os campos.", Alert.AlertType.ERROR);
                return;
            }

            if (!CheckBoxcadastro.isSelected()) {
                showAlert("Aceite os termos!","Clique em cima de termos, leia, e aceite, clicando na caixinha ao lado de \"Termos\" em azul.", Alert.AlertType.ERROR);
                return;
            }


            if (!nome.matches("[A-Za-zÀ-ÿ\\s]+")) {
                hc.showAlert("Nome inválido", "O campo 'Nome' deve conter apenas letras e espaços.", Alert.AlertType.ERROR);
                return;
            }

            if (!senha.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-={}|:;\"'<>,.?/~`]).+$")) {
                hc.showAlert("Senha inválida", "A senha deve conter pelo menos uma letra maiúscula, uma letra minúscula e um caractere especial.", Alert.AlertType.ERROR);
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


            if(Pattern.matches("[a-z][A-Z]",cpf)){
                showAlert("Somente números","Coloque somente números no número de CPF.", Alert.AlertType.ERROR);
                return;
            }

            if (cpf.matches("[0-9]")){
                showAlert("Somente números","Coloque somente números no número de CPF.", Alert.AlertType.ERROR);
                return;
            }

            if(Pattern.matches("[a-z][A-Z]",telefoneText.getText())){
                showAlert("Somente números","Coloque somente números no número de telefone", Alert.AlertType.ERROR);
                return;
            }
            if(Pattern.matches("[a-z][A-Z]",CEP)){
                showAlert("Somente números","Coloque somente números no número de telefone", Alert.AlertType.ERROR);
                return;
            }

            if(senha.length()<12 || senha.length()>32){
                showAlert("Sua senha não é válida","Temanho mínimo:12; Máximo: 32", Alert.AlertType.ERROR);
                return;
            }

            if(!Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",email)){
                showAlert("Email inválido","Digite corretamente seu email: nome@vk.com; nome123@gmail.com; etc...", Alert.AlertType.ERROR);
                return;
            }

            InsertSMT.setString(1, nome);
            InsertSMT.setString(2, senha);
            InsertSMT.setString(3, cpf);
            InsertSMT.setDate(4, Date.valueOf(nascimento));
            InsertSMT.setString(5, endereco);
            InsertSMT.setString(6, email);
            InsertSMT.setString(7, telefoneText.getText());

            InsertSMT.execute();
            showAlert("Cadastro bem-sucedido", "Cadastro de " + nome + " foi um sucesso!!!", Alert.AlertType.ERROR);


            Stage currentStage = (Stage) mainScene.getWindow();
            currentStage.close();

            ha.openScreen("paginalogin.fxml", "VK", mainScene);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void voltarClicked(){
        ha.loadScreen("paginalogin.fxml","k",mainScene);
    }

    public void termosClicked(ActionEvent actionEvent) {
        try {
            Desktop.getDesktop().browse(new URI("https://docs.google.com/document/d/1r3z3w2721rs7Jqg07W7eNpVwm8qyVrQ9KSICxcLpeag/edit?usp=sharing"));
        } catch (Exception e){
        }
    }

    public void cpfMask(KeyEvent keyEvent) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("###.###.###-##");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(CPFText);
        tff.formatter();
    }

    public void telefoneMask(KeyEvent keyEvent) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("(##)####-#####");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(telefoneText);
        tff.formatter();
    }

    public void cepMask(KeyEvent keyEvent) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("#####-###");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(CEPText);
        tff.formatter();
    }
}
