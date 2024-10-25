package org.example.pi_primo;

public class Mail {
    public void EnviodDaSenhaPorEmail(String para, String tieulo, String coutenudo){
        var mensagem = new SimpleMailMessage();
        mensagem.setText(para);

        mensagem.setSubject(tieulo);
        mensagem.setText(coutenudo);

    }
}
