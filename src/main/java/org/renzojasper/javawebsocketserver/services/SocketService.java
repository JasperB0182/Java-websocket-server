package org.renzojasper.javawebsocketserver.services;

import org.renzojasper.javawebsocketserver.models.Message;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;

@Service
public class SocketService {

    private final MessageService messageService;

    public SocketService(MessageService messageService) {
        this.messageService = messageService;
    }

    public void setupChatHistory(WebSocketSession session) throws IOException {

        List<Message> last50Messages = this.messageService.retrieveLast50Messages();

        StringBuilder last50MessagesString = new StringBuilder();

        last50MessagesString.append("------------------------------------------------------------\n");

        for (Message message : last50Messages) {
            last50MessagesString.append(String.format("[%s]: %s\n", message.getSender().getUserData().getUsername(), message.getContent()));
        }

        last50MessagesString.append("----------------------Last 50 messages----------------------");

        WebSocketMessage<String> last50MessagesWebsocketString = new TextMessage(last50MessagesString);

        session.sendMessage(last50MessagesWebsocketString);

    }
}
