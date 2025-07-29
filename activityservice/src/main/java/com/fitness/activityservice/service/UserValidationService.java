package com.fitness.activityservice.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserValidationService {

    private final WebClient userServiceWebClient;

    public boolean validateUser(String userId){

        log.info("checking if user exists with id: {}", userId);

        try{
            return userServiceWebClient
                    .get()
                    .uri("api/users/{userId}/validate", userId)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block(); // blocking for simplicity, consider using reactive approach in real applications

        }catch (WebClientResponseException e) {

            if(e.getStatusCode() == HttpStatus.NOT_FOUND){
               throw new RuntimeException("Activity not found with id: " + userId);
            }
            else if(e.getStatusCode() == HttpStatus.BAD_REQUEST){
                throw new RuntimeException("Invalid request: " + userId);
            }
        }
        return false;
    }


}
