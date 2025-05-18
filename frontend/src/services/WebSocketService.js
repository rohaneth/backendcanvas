import SockJS from 'sockjs-client';
import { Client } from '@stomp/stompjs';

class WebSocketService {
    constructor() {
        this.client = null;
        this.subscriptions = new Map();
    }

    connect(onConnect, onError) {
        if (this.client && this.client.connected) {
            return Promise.resolve();
        }

        const socket = new SockJS('http://localhost:8080/ws');
        this.client = new Client({
            webSocketFactory: () => socket,
            reconnectDelay: 5000,
            debug: (str) => {
                console.log(str);
            },
            onConnect: (frame) => {
                console.log('Connected: ' + frame);
                if (onConnect) onConnect(frame);
            },
            onStompError: (frame) => {
                console.error('Broker reported error: ' + frame.headers['message']);
                console.error('Additional details: ' + frame.body);
                if (onError) onError(frame);
            }
        });

        this.client.activate();
    }

    disconnect() {
        if (this.client) {
            this.unsubscribeAll();
            this.client.deactivate();
        }
    }


    subscribe(destination, callback) {
        if (!this.client || !this.client.connected) {
            console.error('WebSocket is not connected');
            return null;
        }

        const subscription = this.client.subscribe(destination, (message) => {
            if (message.body) {
                callback(JSON.parse(message.body));
            }
        });

        this.subscriptions.set(destination, subscription);
        return subscription;
    }


    unsubscribe(destination) {
        const subscription = this.subscriptions.get(destination);
        if (subscription) {
            subscription.unsubscribe();
            this.subscriptions.delete(destination);
        }
    }


    unsubscribeAll() {
        this.subscriptions.forEach((subscription, destination) => {
            subscription.unsubscribe();
        });
        this.subscriptions.clear();
    }


    sendMessage(destination, message) {
        if (!this.client || !this.client.connected) {
            console.error('WebSocket is not connected');
            return false;
        }

        this.client.publish({
            destination: `/app${destination}`,
            body: JSON.stringify(message)
        });
        return true;
    }
}

export default new WebSocketService();
