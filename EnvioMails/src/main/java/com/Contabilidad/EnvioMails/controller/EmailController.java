package com.Contabilidad.EnvioMails.controller;

import com.Contabilidad.EnvioMails.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    //CLASE CREADA PAR PROBAR EL FUNCIONAMIENTO DE ENVIO DE EMAILS ANTES DE PROBAR CON EL OBJETO PEDIDO RECIBIDO
    //DESDE EL MICROSERVICIO PEDIDOS ATRAVES DE KAFKA

        @Autowired
        private EmailService emailService;

        @GetMapping("/enviar-email")
        public String enviarEmail(
                @RequestParam String destinatario,
                @RequestParam String asunto,
                @RequestParam String mensaje) {
            try {
                emailService.enviarEmail(destinatario, asunto, mensaje);
                return "📧 Email enviado correctamente a: " + destinatario;
            } catch (Exception e) {
                e.printStackTrace();
                return "❌ Error al enviar el email: " + e.getMessage();
            }
        }
}





