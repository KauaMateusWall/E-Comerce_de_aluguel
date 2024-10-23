package org.example.pi_primo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class TelaProduto {
    @FXML public Button alugarButton;
    @FXML public Button voltarButton;

    @FXML public Label precoText;
    @FXML public Label nomeProdutoText;

    @FXML public ImageView ProdutoImage;
    public Label descricaoText;

    @FXML
    public void alugarClicked(ActionEvent actionEvent) {
    }

    @FXML
    public void voltarClicked(ActionEvent actionEvent) {
    }
}
