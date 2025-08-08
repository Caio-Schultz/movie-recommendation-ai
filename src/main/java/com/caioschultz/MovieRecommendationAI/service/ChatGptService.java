package com.caioschultz.MovieRecommendationAI.service;

import com.caioschultz.MovieRecommendationAI.model.FilmeDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChatGptService {


    private final WebClient webClient;
    private String apiKey = System.getenv("API_KEY");

    public ChatGptService(WebClient webClient) {
        this.webClient = webClient;
    }

    /*

    curl "https://api.openai.com/v1/chat/completions" \
    -H "Content-Type: application/json" \
    -H "Authorization: Bearer $OPENAI_API_KEY" \
    -d '{
        "model": "gpt-4.1",
        "messages": [
            {
                "role": "user",
                "content": "Write a one-sentence bedtime story about a unicorn."
            }
        ]
    }'

     */


    public Mono<String> generate(List<FilmeDTO> filmeDTO){

        String filmes = filmeDTO.stream()
                .map(movie -> String.format(movie.getNome(), movie.getDescricao(), movie.getGenero(), movie.getFaixaEtaria()))
                .collect(Collectors.joining("\n"));

        String prompt = "Recomende os melhores filmes com base nesses aqui: " + filmes + ". Também indique apenas UM filme que você mais recomenda alisando todos os filmes do banco de dados. Faça tudo isso escrevendo o menos possível.";
        Map<String, Object> requestBody = Map.of(
          "model", "gpt-4.1",
          "messages", List.of(
                 Map.of("role", "system", "content", "Você agora é um especialista em filmes que recomenda os melhores filmes"),
                 Map.of("role", "user", "content", prompt)
                )
        );

        return webClient.post()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    var choices = (List<Map<String, Object>>) response.get("choices");
                    if (choices != null && !choices.isEmpty()) {
                        Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
                        return message.get("content").toString();
                    }
                    return "Nenhuma receita foi gerada.";
                });
    }
}
