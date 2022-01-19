package com.webcode.Webclientapiconsumer.controller;

import com.webcode.Webclientapiconsumer.client.RickandMortyClient;
import com.webcode.Webclientapiconsumer.domain.model.CharacterResponse;
import com.webcode.Webclientapiconsumer.domain.model.EpisodiosResponse;
import com.webcode.Webclientapiconsumer.domain.model.ListCharactersResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/webclient")
public class RickAndMortyController {

   private final RickandMortyClient rickandMortyClient;


   @GetMapping("/character/{id}")
   public Mono<CharacterResponse> findAndCharacterById(@PathVariable String id) {
        return rickandMortyClient.findAndCharacterById(id);
   }

    @GetMapping("/character")
    public Flux<ListCharactersResponse> findAllCharacters(ListCharactersResponse listCharactersResponse) {
        return rickandMortyClient.findAllCharacters(listCharactersResponse);
    }

    @GetMapping("/episode/{id}")
    public Mono<EpisodiosResponse> findAndEpisodiosById(@PathVariable String id) {
        return rickandMortyClient.findAndEpisodiosById(id);
    }
}
