package com.example.demo.controller;

import com.example.demo.dto.MemberDTO;
import com.example.demo.service.WebClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webclient")
public class WebClientController {

    private final WebClientService webClientService;

    public WebClientController(WebClientService webClientService) {
        this.webClientService = webClientService;
    }


    @GetMapping
    public String getName() {
        return this.webClientService.getName();
    }

    @GetMapping("/pathVariable")
    public String getNameWithPathVariable() {
        return this.webClientService.getNameWithPathVariable();
    }

    @GetMapping("/parameter")
    public String getNameWithParameter() {
        return this.webClientService.getNameWithParameter();
    }


    @PostMapping
    public ResponseEntity<MemberDTO> postDTO() {
        return this.webClientService.postWithParamAndBody();
    }

    @PostMapping("/header")
    public ResponseEntity<MemberDTO> postWithHeader() {
        return this.webClientService.postWithHeader();
    }

}
