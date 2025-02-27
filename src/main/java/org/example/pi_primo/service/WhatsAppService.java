package org.example.pi_primo.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class WhatsAppService {
    // Substitua pelos valores reais da sua conta Twilio
    public static final String ACCOUNT_SID = "e0e8cad6044de746d07f60312e1af9c9";
    public static final String AUTH_TOKEN = "AC366ac1ca7052def5eb6836fb7ab41d52";

    public static void main(String[] args) {
        // Inicializa o Twilio
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        // Criar e enviar a mensagem pelo WhatsApp
        Message message = Message.creator(
                        new PhoneNumber("whatsapp:+555499530761"),  // Número de destino
                        new PhoneNumber("whatsapp:+14155238886"),  // Número oficial do Twilio para WhatsApp
                        "seu tio morreu")  // Corpo da mensagem
                .create();

        // Exibir o SID da mensagem
        System.out.println("Mensagem enviada com sucesso! SID: " + message.getSid());
    }
}
