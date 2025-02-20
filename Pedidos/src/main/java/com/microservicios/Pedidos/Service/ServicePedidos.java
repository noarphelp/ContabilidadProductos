package com.microservicios.Pedidos.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.microservicios.Pedidos.Model.Pedidos;
import com.microservicios.Pedidos.ProductoDTO;
import com.microservicios.Pedidos.Repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.format.DateTimeFormatter;
import java.util.List;
@Service
public class ServicePedidos implements ImplPedidos{

    private PedidoRepository pedidoRepository;
    private RestClient restClient;
    private String url = "http://servicio-productos/productos/";

    private KafkaTemplate<String, String> kafkaTemplate;
    @Value("${spring.kafka.topic.name}")
    private String topicName;


    private  ObjectMapper objectMapper;  // Inyectamos ObjectMapper


    public ServicePedidos(PedidoRepository pedidoRepository,
                          RestClient.Builder restClientBuilder, KafkaTemplate<String, String> kafkaTemplate
                            ,ObjectMapper objectMapper) {
        this.pedidoRepository = pedidoRepository;
        this.restClient = restClientBuilder.build();
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;

    }

    @Override
    public List<Pedidos> allPedidos() {
        Pedidos pedidos= new Pedidos();
       double total= pedidos.getTotal();


        return pedidoRepository.findAll();
    }


    @Transactional
    @Override
    public void newPedido(Pedidos pedidos) {
        int codProd = pedidos.getCodProducto();
        int stock = pedidos.getUnidades();


        ProductoDTO productoDTO = restClient.get()
                .uri(url + codProd)
                .retrieve()
                .body(ProductoDTO.class);

        if (productoDTO == null) {
            throw new RuntimeException("Producto no encontrado con ID: " + codProd);
        }

        double total = productoDTO.getPrecioUnitario() * stock;
        pedidos.setTotal(total);

        restClient.put()
                .uri(url + codProd)
                .body(stock)
                .retrieve()
                .toBodilessEntity();

        pedidoRepository.save(pedidos);

        // Convertimos el objeto Pedidos a JSON usando el método writeValueAsString
        try {
            // Crear un nuevo ObjectMapper con formato específico para LocalDateTime
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            // Definir un `ObjectNode` y modificar `fechaPedido`
            ObjectNode jsonNode = mapper.valueToTree(pedidos);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
            jsonNode.put("fechaPedido", pedidos.getFechaPedido().format(formatter));

            // Convertimos el objeto a JSON String
            String mensajeJson = mapper.writeValueAsString(jsonNode);

            // Enviamos el mensaje JSON a Kafka
            kafkaTemplate.send(topicName, mensajeJson);

            System.out.println("📢 Pedido enviado a Kafka: " + mensajeJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            System.err.println("Error al convertir el pedido a JSON: " + e.getMessage());
        }
    }
}
