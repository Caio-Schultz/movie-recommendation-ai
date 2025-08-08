package com.caioschultz.MovieRecommendationAI.controller;

import com.caioschultz.MovieRecommendationAI.model.FilmeDTO;
import com.caioschultz.MovieRecommendationAI.service.ChatGptService;
import com.caioschultz.MovieRecommendationAI.service.FilmeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping
public class MovieRecommendationController {

    private FilmeService service;
    private ChatGptService chatGptService;

    public MovieRecommendationController(FilmeService service, ChatGptService chatGptService) {
        this.service = service;
        this.chatGptService = chatGptService;
    }

    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> generate(){
        List<FilmeDTO> filmes = service.listar();
        return chatGptService.generate(filmes)
                .map(recommendation -> ResponseEntity.ok(recommendation))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

}
