package org.renzojasper.javawebsocketserver.handlers;

import org.jspecify.annotations.NonNull;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SocketConnectionHandler extends TextWebSocketHandler {
    // Hier worden alle huidige connecties opgeslagen
    List<WebSocketSession> webSocketSessions = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) throws Exception {
        // Dit voert de afterConnectionEstablished functie uit van TextWebSocketHandler nadat er een connectie heeft plaatsgevonden
        super.afterConnectionEstablished(session);

        System.out.println(session.getId() + " has connected");

        webSocketSessions.add(session);
    }

    @Override
    public void afterConnectionClosed(@NonNull WebSocketSession session, @NonNull CloseStatus status) throws Exception {
        // Voert dus nu de functie uit van TextWebSocketHandler nadat de connectie is afgesloten
        super.afterConnectionClosed(session, status);

        System.out.println(session.getId() + " has disconnected.");

        webSocketSessions.remove(session);
    }

    @Override
    public void handleMessage(@NonNull WebSocketSession session, @NonNull WebSocketMessage<?> message) throws Exception {
        super.handleMessage(session, message);

        for (WebSocketSession webSocketSession : webSocketSessions) {
            if (session == webSocketSession) {
                continue;
            }
            webSocketSession.sendMessage(message);
        }
    }


}
