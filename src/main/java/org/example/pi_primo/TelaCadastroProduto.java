package org.example.pi_primo;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TelaCadastroProduto {
    public Button cadastrarButton;
    public Button voltarButton;
    public TextField tipoText;
    public TextField precoText;
    public TextField nomeText;
    public TextArea descricaoText;

    public void voltarClicked(ActionEvent actionEvent) {
        // todo:Fazer toda a lógica de volar para a ultima página
    }

    public void cadastrarClicked(ActionEvent actionEvent) {
        String getDescricao=descricaoText.getText();
        String getTipo=tipoText.getText();
        String getPreco=precoText.getText();
        String getNome=nomeText.getText();

        if(getDescricao.isEmpty()||getTipo.isEmpty()||getNome.isEmpty()||getPreco.isEmpty()){
            HelloController.showAlert("Cadastro incompleto",
                    "Porfavor preecha todos os campos antes de registrar o produto");
            return;
        }

        try{
            double preco = Double.parseDouble(getPreco);

        } catch (NumberFormatException _){
            HelloController.showAlert("Valor inválido","Passe um valor válido para o campo \"Preço\"");
            return;
        }

        // todo:Fazer toda a lógica de cadastro de produto
    }

}
