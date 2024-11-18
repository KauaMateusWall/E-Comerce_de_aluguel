package org.example.pi_primo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.pi_primo.config.ConexaoDB;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {

    ConexaoDB conexaoDB = new ConexaoDB();
    public static final String produtoQuery =
            "SELECT p.id AS id, p.nome AS nome, p.categoria_Produto AS 'categoria_Produto', p.descricao AS descricao " +
            ", p.quantidadeDeEmprestimos AS quantidadeDeEmprestimos, p.preco AS preco, p.situacao AS situacao" +
            ", prop.nome AS Proprietario, prop.id as idProprietario FROM produto p " +
            "INNER JOIN cliente prop ON prop.id=p.Proprietario ORDER BY p.quantidadeDeEmprestimos ASC;";


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("paginalogin.fxml"));
        Scene scene = fxmlLoader.load();
        stage.setTitle("Empréstimo VK - Login");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();
    }

    public void loadScreen(String fxmlFile, String title, Scene sceneOriginal) {
        try {
            Scene sceneNova = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
            Stage stage = (Stage) sceneOriginal.getWindow();
            stage.setScene(sceneNova);
            stage.setTitle(title);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch();
    }
}

