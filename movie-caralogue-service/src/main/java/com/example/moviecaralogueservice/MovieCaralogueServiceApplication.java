package com.example.moviecaralogueservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class MovieCaralogueServiceApplication {

    @Bean
    @LoadBalanced
    RestTemplate getResTemplate() {
        return new RestTemplate();
    }

    @Bean
    WebClient.Builder getWebClientBuilder() {
        return WebClient.builder();
    }

    public static void main(String[] args) {
        SpringApplication.run(MovieCaralogueServiceApplication.class, args);
    }
}
