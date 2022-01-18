package com.webcode.Webclientapiconsumer.client;

import com.webcode.Webclientapiconsumer.domain.model.CharacterResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@Slf4j
public class RickandMortyClient {

    private final WebClient webCLient;

    public RickandMortyClient(WebClient.Builder builder) {
        webCLient = WebClient.builder().baseUrl("https://rickandmortyapi.com/api").build();
    }

    //tratamento de um personagem por isso do mono
    public Mono<CharacterResponse> findAndCharacterById(String id){
        log.info("Buscando o personagem com o id {}", id);
        return webCLient
                .get()
                .uri("/character/"+id)
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, error -> Mono.error(new RuntimeException("Vereficar os paramentos")))
                .bodyToMono(CharacterResponse.class);
    }
}
