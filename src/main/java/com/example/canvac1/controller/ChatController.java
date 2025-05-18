package com.example.canvac1.controller;

import com.example.canvac1.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    

    @MessageMapping("/chat.private.{userId}")
    @SendTo("/user/{userId}/queue/private")
    public ChatMessage sendPrivateMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }
}
