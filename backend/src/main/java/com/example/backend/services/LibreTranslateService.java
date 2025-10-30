package com.example.backend.services;


import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class LibreTranslateService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String baseUrl = "https://libretranslate.com"; // có thể đổi thành server riêng

    public String translate(String text, String source, String target) {
        String url = baseUrl + "/translate";

        Map<String, Object> body = new HashMap<>();
        body.put("q", text);
        body.put("source", source);
        body.put("target", target);
        body.put("format", "text");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
        Map<?, ?> response = restTemplate.postForObject(url, entity, Map.class);

        if (response != null && response.get("translatedText") != null) {
            return (String) response.get("translatedText");
        } else {
            return "(Lỗi dịch)";
        }
    }
}

