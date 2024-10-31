package com.example.demo.service;

import com.example.demo.dto.MemberDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WebClientService {

    public String getName() {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:9090")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();

        return webClient.get()
                .uri("/server")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String getNameWithPathVariable() {
        WebClient webClient = WebClient.create("http://localhost:9090");
        ResponseEntity<String> responseEntity = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/server/{variable}")
                        .build("some variable")
                )
                .retrieve()
                .toEntity(String.class)
                .block();

        return responseEntity.getBody();
    }

    public String getNameWithParameter() {
        WebClient webClient = WebClient.create("http://localhost:9090");

        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/server/param")
                        .queryParam("name", "James")
                        .build()
                )
                .exchangeToMono(clientResponse -> {
                    if(clientResponse.statusCode().equals(HttpStatus.OK)) {
                        return clientResponse.bodyToMono(String.class);
                    } else {
                        return clientResponse.createException().flatMap(Mono::error);
                    }
                })
                .block();
    }


    public ResponseEntity<MemberDTO> postWithParamAndBody() {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:9090")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("james");
        memberDTO.setEmail("james@test.ai");
        memberDTO.setOrganization("some text");

        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/server")
                        .queryParam("name", "james")
                        .queryParam("email", "jaems@test.ai")
                        .queryParam("organization", "test").build()
                )
                .bodyValue(memberDTO)
                .retrieve()
                .toEntity(MemberDTO.class)
                .block();
    }

    public ResponseEntity<MemberDTO> postWithHeader() {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:9090")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("james");
        memberDTO.setEmail("james@test.ai");
        memberDTO.setOrganization("some text");

        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/server/add-header").build())
                .bodyValue(memberDTO)
                .header("my-header", "some header value")
                .retrieve()
                .toEntity(MemberDTO.class)
                .block();
    }

}
