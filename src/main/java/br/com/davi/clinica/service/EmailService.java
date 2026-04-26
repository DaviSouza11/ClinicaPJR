package br.com.davi.clinica.service;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void EnviarCodigo(String destintario, int codigo){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(destintario);
        message.setSubject("Código de verificação: ");
        message.setText("Seu código de verificação é: " + codigo);

        mailSender.send(message);

    }

}



