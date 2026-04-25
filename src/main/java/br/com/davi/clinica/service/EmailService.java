package br.com.davi.clinica.service;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailService {

    private final Dotenv dotenv =  Dotenv.load();

    private final String remetente = dotenv.get("MEU_EMAIL");
    private final String senhaApp = dotenv.get("MINHA_SENHA_APP");

    public void enviarCodigo(String destinatario, int codigo){
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remetente, senhaApp);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remetente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject("Código de Ativação - ClínicaPRJ");
            message.setText("Olá!\n\nSeu código de segurança é: " + codigo + "\n\nSeja bem-vindo!");

            System.out.println("Conectando ao Gmail e enviando código para " + destinatario + "...");
            Transport.send(message);
            System.out.println("E-mail enviado com sucesso!");

        } catch (MessagingException e) {
            throw new RuntimeException("Falha ao enviar e-mail: " + e.getMessage());
        }
    }
}
