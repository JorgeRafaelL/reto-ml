package com.ejercicio.cupondecompra.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import static com.ejercicio.cupondecompra.util.Constant.BASE_URL_MERCADO_LIBRE;

@Configuration
public class WebClientConfig {
    @Value(BASE_URL_MERCADO_LIBRE)
    private String baseUrlML;

    @Bean
    public WebClient webClient() {
        return WebClient.builder().baseUrl(baseUrlML).build();
    }
}
