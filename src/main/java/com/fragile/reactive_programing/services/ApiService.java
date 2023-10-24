package com.fragile.reactive_programing.services;
import com.fragile.reactive_programing.exceptions.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@RequestMapping("api/terra_home")

public class ApiService {

    private final WebClient webClient;

    public Flux<Object> getEvents() {
        return webClient.get()
                .uri("/public/events")
                .retrieve()
                .onStatus(httpStatus -> !httpStatus.is2xxSuccessful(),
                        clientResponse -> handleErrorResponse(clientResponse.statusCode()))
                .bodyToFlux(Object.class)
                .onErrorResume(Exception.class, e -> Flux.empty());

    }
        private Mono<? extends Throwable> handleErrorResponse(HttpStatusCode statusCode) {
            // Handle non-success status codes here (e.g., logging or custom error handling)
            return Mono.error(new CustomException("Failed to fetch employee. Status code: " + statusCode));
        }
}
