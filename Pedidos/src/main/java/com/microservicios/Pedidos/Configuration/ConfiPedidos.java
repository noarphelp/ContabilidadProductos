package com.microservicios.Pedidos.Configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.web.client.RestClient;

@Configuration
public class ConfiPedidos {

    @Value("${spring.kafka.topic.name}")
    private String topicName;


    @LoadBalanced
    @Bean
    public RestClient.Builder restClient(){

        return RestClient.builder();
    }

    @Bean
   public NewTopic createTopic() {
        return new NewTopic(topicName, 2, (short) 1);
    }

}
