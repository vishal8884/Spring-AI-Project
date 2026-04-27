package com.vishal.openai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatMemoryClientConfig {

    @Bean(name = "chatMemoryClient")
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder){
        OllamaChatOptions chatOptions = OllamaChatOptions.builder()
                .temperature(0.1)
                .build();

        return chatClientBuilder
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .defaultAdvisors(new TokenUsageAuditAdvicer())
                .defaultOptions(chatOptions)
                .defaultSystem("You are an Gym related AI Assistant. You should Answer all Gym realted questions only.")
//                .defaultUser("This is default user message")
                .build();
    }
}
