package com.Contabilidad.EnvioMails.service;

import com.Contabilidad.EnvioMails.model.Pedidos;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class KafkaConsumerService {

    @Autowired
    private EmailService emailService;


    @KafkaListener(topics = "pedidos-topic", groupId = "pedidos-consumer-group", containerFactory = "kafkaListenerContainerFactory")
    public void recibirMensaje(Pedidos pedido) {

        try {
            System.out.println("📩 Mensaje recibido de Kafka: " + pedido);

            // Construir el contenido del email
            String asunto = "Confirmación de Pedido #" + pedido.getPedidosId();
            String contenido = "<h1>Detalles de su Pedido</h1>" +
                    "<p><b>Producto ID:</b> " + pedido.getCodProducto() + "</p>" +
                    "<p><b>Unidades:</b> " + pedido.getUnidades() + "</p>" +
                    "<p><b>Total:</b> $" + pedido.getTotal() + "</p>" +
                    "<p><b>Fecha del Pedido:</b> " + formatearFecha(pedido.getFechaPedido()) + "</p>";

            // Enviar el email (Reemplaza con el correo del cliente)
            emailService.enviarEmail("correoClienter@gmail.com", asunto, contenido);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("❌ Error al procesar el mensaje de Kafka: " + e.getMessage());
        }
    }

    private String formatearFecha(LocalDateTime fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return fecha.format(formatter);
    }

}