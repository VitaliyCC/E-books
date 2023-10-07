package com.savostian.ebooks.controllers;

import com.savostian.ebooks.DTO.ChatRequest;
import com.savostian.ebooks.DTO.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
public class ChatController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${openai.api.url}")
    private String apiUrl;
    @Value("${openai.api.model}")
    private String model;

    @GetMapping("/chat")
    public String chat() {
        return "chat";
    }

    @PostMapping("/send-message")
    @ResponseBody
    public String sendMessage(@RequestParam String message) {
        ChatRequest request=new ChatRequest(model, message);
        ChatResponse chatGptResponse = restTemplate.postForObject(apiUrl, request, ChatResponse.class);
        return chatGptResponse.getChoices().get(0).getMessage().getContent();
    }
}

