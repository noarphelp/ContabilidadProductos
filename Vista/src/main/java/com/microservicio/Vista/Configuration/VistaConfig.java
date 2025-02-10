package com.microservicio.Vista.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class VistaConfig {

    @Bean
    public RestClient.Builder restClientBuilder(){

        return RestClient.builder();
    }


}
