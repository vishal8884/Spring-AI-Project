package com.vishal.openai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder){
        return chatClientBuilder
                .defaultSystem("You are an Gym related AI Assistant. You should Answer all Gym realted questions only.")
//                .defaultUser("This is default user message")
                .build();
    }
}
