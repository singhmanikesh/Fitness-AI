package com.fitness.aiservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;


@Service
public class GeminiService {

    @Value("${gemini.api.url}")
    private String geminiApiUrl;
    @Value("${gemini.api.key}")
    private String geminiApiKey;


    private final WebClient webClient;

    public GeminiService(WebClient.Builder webClient) {
        this.webClient = WebClient.builder().build();
    }

   public String getAnswer(String question){
       Map<String,Object> requestBody = Map.of(
               "contents", new Object[]{
                       Map.of("parts", new Object[]{
                               Map.of("text", question)
                       })
               }
       );
       /*String response = webClient.post()
               .uri(geminiApiUrl)
               .header("Content-Type", "application/json")
               .header("X-goog-api-key", geminiApiKey)
               .retrieve()
               .bodyToMono(String.class)
               .block();*/


       String response = webClient.post()
               .uri(geminiApiUrl) // Use the full URL directly
               .header("Content-Type", "application/json")
               .header("X-goog-api-key", geminiApiKey) // API key as a header
               .bodyValue(requestBody) // Set the request body
               .retrieve()
               .bodyToMono(String.class)
               .block();

            return response;
   }

}
