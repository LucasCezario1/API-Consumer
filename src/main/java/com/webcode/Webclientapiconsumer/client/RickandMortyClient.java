package com.webcode.Webclientapiconsumer.client;

import com.webcode.Webclientapiconsumer.domain.model.CharacterResponse;
import com.webcode.Webclientapiconsumer.domain.model.EpisodiosResponse;
import com.webcode.Webclientapiconsumer.domain.model.ListCharactersResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class RickandMortyClient {


    WebClient webCLient;

    public RickandMortyClient(WebClient.Builder builder) {
        webCLient = WebClient.builder().baseUrl("https://rickandmortyapi.com/api").build();
    }

    public Flux<ListCharactersResponse> findAllCharacters(ListCharactersResponse listCharactersResponse){
        log.info("Buscando todos os personagens");
        return webCLient
                .get()
                .uri("/character/")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, error -> Mono.error(new RuntimeException("Vereficar os paramentos")))
                .bodyToFlux(ListCharactersResponse.class);
    }

    //tratamento de um personagem por isso do mono
    public Mono<CharacterResponse> findAndCharacterById(String id){
        log.info("Buscando o personagem com o id {}", id);
        return webCLient
                .get()
                .uri("/character/"+id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, error -> Mono.error(new RuntimeException("Vereficar os paramentos")))
                .bodyToMono(CharacterResponse.class);
    }


    //tratamento de um episodios por isso do mono
    public Mono<EpisodiosResponse> findAndEpisodiosById(String id){
        log.info("Buscando o episodio pelo id {}", id);
        return webCLient
                .get()
                .uri("/episode/"+id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::isError, error -> Mono.error(new RuntimeException("Vereficar os paramentos")))
                .bodyToMono(EpisodiosResponse.class);
    }
}
