package com.microservicios.Pedidos.Configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ConfiPedidos {
    @LoadBalanced
    @Bean
    public RestClient.Builder restClient(){

        return RestClient.builder();
    }
}
