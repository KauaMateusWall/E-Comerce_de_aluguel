package org.example.pi_primo.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;


public class TelaAjuda {

    private static final String ACCOUNT_SID = "";
    private static final String AUTH_TOKEN = "";

    @FXML
    private Button Enviar;

    @FXML
    private TextArea Mensagem;

    @FXML
    private void enviarMensagem() {
        String textoMensagem = Mensagem.getText();

        if (textoMensagem.isEmpty()) {
            System.out.println("Erro: A mensagem está vazia.");
            return;
        }

        try {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            // Criar e enviar a mensagem pelo WhatsApp
            Message message = Message.creator(
                    new PhoneNumber("whatsapp:+555499530761"),  // Número de destino
                    new PhoneNumber("whatsapp:+14155238886"),  // Número oficial do Twilio para WhatsApp
                    textoMensagem // Mensagem capturada do TextArea
            ).create();

            System.out.println("Mensagem enviada com sucesso! SID: " + message.getSid());

        } catch (Exception e) {
            System.err.println("Erro ao enviar mensagem: " + e.getMessage());
        }
    }



}
