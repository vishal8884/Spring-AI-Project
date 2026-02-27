package com.vishal.openai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

//    @GetMapping("/chat")
//    public String chat(@RequestParam("message") String message) {
//        return chatClient.prompt(message)
//                .call()
//                .content();
//    }

    @GetMapping("/chat")
    public String chatWithSystem(@RequestParam("message") String message) {
        return chatClient.prompt()
                .system("You are an Gym related AI Assistant. You should Answer all Gym realted questions only." +
                        " Others you can respond something very calm that you know it but you are not supposed to do that.Make sure you dont give them proper response in case of other topics.")
                .user(message)
                .call()
                .content();
    }
}
