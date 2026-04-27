package com.vishal.openai.controller;

import com.vishal.openai.config.TokenUsageAuditAdvicer;
import com.vishal.openai.model.CountryCities;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StructuredOutputController {

    private ChatClient chatClient;

    public StructuredOutputController(ChatClient.Builder chatClientBuilder){
        this.chatClient = chatClientBuilder
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .defaultUser("Return ONLY valid JSON.\n" +
                        "Do NOT wrap in markdown.\n" +
                        "Do NOT add backticks.\n" +
                        "Do NOT add explanation.") //This is temp as my model is very weak so adding this else not required
                .build();
    }

    @GetMapping("/bean/getCountries")
    public ResponseEntity<CountryCities> getCountryCities(@RequestParam("message") String message){
        CountryCities countryCities = chatClient.prompt()
                .user(message)
                .call()
                .entity(CountryCities.class);

        return ResponseEntity.ok(countryCities);
    }

    @GetMapping("/bean/list")
    public ResponseEntity<List<String>> getCountryCitiesStrList(@RequestParam("message") String message){
        List<String> countryCities = chatClient.prompt()
                .user(message)
                .call()
                .entity(new ListOutputConverter());

        return ResponseEntity.ok(countryCities);
    }

    @GetMapping("/bean/map")
    public ResponseEntity<Map<String, Object>> getCountryCitiesMap(@RequestParam("message") String message){
        Map<String, Object> countryCities = chatClient.prompt()
                .user(message)
                .call()
                .entity(new MapOutputConverter());

        return ResponseEntity.ok(countryCities);
    }

    @GetMapping("/bean/getCountriesList")
    public ResponseEntity<List<CountryCities>> getCountryCitiesList(@RequestParam("message") String message){
        List<CountryCities> countryCitiesList = chatClient.prompt()
                .user(message)
                .call()
                .entity(new ParameterizedTypeReference<List<CountryCities>>() {
                });

        return ResponseEntity.ok(countryCitiesList);
    }

}
