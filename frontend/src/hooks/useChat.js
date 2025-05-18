import { useState, useEffect, useCallback } from 'react';
import WebSocketService from '../services/WebSocketService';

export const useChat = (username) => {
    const [messages, setMessages] = useState([]);
    const [isConnected, setIsConnected] = useState(false);
    const [error, setError] = useState(null);

    // Handle incoming messages
    const handleNewMessage = useCallback((message) => {
        setMessages(prevMessages => [...prevMessages, message]);
    }, []);

    // Connect to WebSocket and set up subscriptions
    useEffect(() => {
        WebSocketService.connect(
            () => {
                setIsConnected(true);
                // Subscribe to public messages
                WebSocketService.subscribe('/topic/public', handleNewMessage);
                // Join the chat
                if (username) {
                    WebSocketService.sendMessage('/chat.addUser', {
                        sender: username,
                        type: 'JOIN',
                        content: `${username} joined the chat`
                    });
                }
            },
            (error) => {
                console.error('WebSocket error:', error);
                setError('Failed to connect to chat');
                setIsConnected(false);
            }
        );

        return () => {
            WebSocketService.disconnect();
            setIsConnected(false);
        };
    }, [username, handleNewMessage]);

    // Send a chat message
    const sendMessage = useCallback((content) => {
        if (!username) return;
        
        const message = {
            sender: username,
            content,
            type: 'CHAT'
        };

        WebSocketService.sendMessage('/chat.sendMessage', message);
    }, [username]);

    return {
        messages,
        isConnected,
        error,
        sendMessage
    };
};
