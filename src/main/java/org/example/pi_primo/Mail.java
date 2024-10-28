package org.example.pi_primo;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.mail.SimpleMailMessage;

public class Mail {

    public static void EnviodDaSenhaPorEmail(String para, String tieulo, String coutenudo){

    String MeuEmail = "kaua99088wx@gmail.com";
    String MinhaSenha  = "990886449913";

    SimpleEmail email = new SimpleEmail();
    email.setHostName("smtp.gamil.com");
    email.setSmtpPort(465);
    email.setAuthenticator(new DefaultAuthenticator(MeuEmail,MinhaSenha));
    email.setSSLOnConnect(true);

    try {
        email.setFrom(MeuEmail);
        email.setSubject(tieulo);
        email.setMsg(coutenudo);
        email.addBcc(MeuEmail);
        email.send();
        System.out.println("enviado");

    }catch (Exception e){

    }

    }
}
