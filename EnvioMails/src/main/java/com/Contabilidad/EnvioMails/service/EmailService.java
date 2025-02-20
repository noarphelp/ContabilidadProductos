package com.Contabilidad.EnvioMails.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarEmail(String destinatario, String asunto, String mensaje) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(destinatario);
            helper.setSubject(asunto);
            helper.setText(mensaje, true);
            mailSender.send(mimeMessage);
            System.out.println("📧 Email enviado a: " + destinatario);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("❌ Error al enviar el email: " + e.getMessage());
        }
    }
}
