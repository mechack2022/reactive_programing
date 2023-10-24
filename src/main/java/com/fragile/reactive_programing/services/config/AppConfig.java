package com.fragile.reactive_programing.services.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {


    @Bean
    public WebClient webClient() {

        return WebClient.builder()
                  .baseUrl("http://localhost:9999")
                  .defaultCookie("cookie-name", "cookie-value")
                  .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                  .build();
    }


}
