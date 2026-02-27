package com.vishal.openai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PromptTemplateController {

    private ChatClient chatClient;

    public PromptTemplateController(ChatClient chatClient){
        this.chatClient = chatClient;
    }

    String promptTemplate = """
            A Customer named {customerName} sent the following message: {customerMessage}
            
            Write a polite and helpful email response addressing the issue.
            Maintain a professional tone and provide reassurance.
            
            Respond as if you are writing the email body only. Don't include subject, signature.
            """;

    @GetMapping("/email")
    public String chatWithSystem(@RequestParam("customerName") String customerName, @RequestParam("customerMessage") String customerMessage) {
        return chatClient.prompt()
                .system("""
                        You are professional customer service assistant which helps drafting email response to improve
                        the productivity of the customer support team.
                        """)
                .user(promptUserSpec -> promptUserSpec.text(promptTemplate)
                        .param("customerName",customerName)
                        .param("customerMessage",customerMessage))
                .call()
                .content();
    }
}
