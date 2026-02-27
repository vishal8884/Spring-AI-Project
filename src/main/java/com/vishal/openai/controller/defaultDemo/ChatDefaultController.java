package com.vishal.openai.controller.defaultDemo;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatDefaultController {

    private final ChatClient chatClient;


    public ChatDefaultController(ChatClient chatClient) {
        this.chatClient = chatClient; //This bean registered in config package
    }

    @GetMapping("/default/chat")
    public String chatWithSystem(@RequestParam("message") String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }
}
