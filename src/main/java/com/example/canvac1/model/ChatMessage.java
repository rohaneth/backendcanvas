package com.example.canvac1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    private String sender;
    private String content;
    private MessageType type;
    private String roomId; // For group chats or different chat rooms
    
    public enum MessageType {
        CHAT, JOIN, LEAVE
    }
}
