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

    // Метод обробки HTTP POST-запиту для надсилання повідомлення до моделі GPT-3.
    // Він використовує RestTemplate для взаємодії з зовнішнім API, надсилає повідомлення і отримує відповідь.
    @PostMapping("/send-message")
    @ResponseBody
    public String sendMessage(@RequestParam String message) {
        // Створюємо запит до моделі GPT-3, використовуючи змінну model та отримане повідомлення.
        ChatRequest request = new ChatRequest(model, message);

        // Надсилаємо запит до зазначеної apiUrl та отримуємо відповідь в формі об'єкта ChatResponse.
        ChatResponse chatGptResponse = restTemplate.postForObject(apiUrl, request, ChatResponse.class);

        // Повертаємо перше повідомлення з відповіді моделі GPT-3 як відповідь на HTTP-запит.
        return chatGptResponse.getChoices().get(0).getMessage().getContent();
    }
}


