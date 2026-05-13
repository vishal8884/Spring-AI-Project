package com.vishal.openai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ChatMemoryController {

    ChatClient chatClient;

    public ChatMemoryController(@Qualifier("chatMemoryClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/chatMemory")
    public String chatWithSystem(@RequestHeader("userName") String userName, @RequestParam("message") String message) {
        return chatClient.prompt()
                .user(message)
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID,userName))
                .call()
                .content();
    }
}
