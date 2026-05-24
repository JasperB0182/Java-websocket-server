package org.renzojasper.javawebsocketserver.handlers;

import org.jspecify.annotations.NonNull;
import org.renzojasper.javawebsocketserver.dto.SessionDTO;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SocketConnectionHandler extends TextWebSocketHandler {
    // Here are all the current connections saved
    List<WebSocketSession> webSocketSessions = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) throws Exception {
        // This executes the afterConnectionEstablished function of TextWebSocketHandler after a connection has been established
        SessionDTO sessionDTO = getSessionDTO(session);

        if (sessionDTO == null) {
            session.close(CloseStatus.NOT_ACCEPTABLE);
            System.out.println("Unauthenticated connection attempt rejected.");
            return;
        }

        super.afterConnectionEstablished(session);
        System.out.println(sessionDTO.getUsername() + " has connected");
        webSocketSessions.add(session);
    }

    @Override
    public void afterConnectionClosed(@NonNull WebSocketSession session, @NonNull CloseStatus status) throws Exception {
        // So now it executes the TextWebSocketHandler function after the connection has been closed
        SessionDTO sessionDTO = getSessionDTO(session);

        super.afterConnectionClosed(session, status);

        System.out.println(sessionDTO.getUsername() + " has disconnected.");

        webSocketSessions.remove(session);
    }

    @Override
    public void handleMessage(@NonNull WebSocketSession session, @NonNull WebSocketMessage<?> message) throws Exception {
        SessionDTO sessionDTO = getSessionDTO(session);

        super.handleMessage(session, message);
        System.out.println("[" + sessionDTO.getUsername() + "]:" + message.getPayload());

        for (WebSocketSession webSocketSession : webSocketSessions) {
            if (session == webSocketSession) {
                continue;
            }
            webSocketSession.sendMessage(message);
        }
    }

    private SessionDTO getSessionDTO(WebSocketSession session) {
        SecurityContext securityContext = (SecurityContext) session
                .getAttributes()
                .get(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);

        if (securityContext == null || securityContext.getAuthentication() == null
                || !securityContext.getAuthentication().isAuthenticated()
                || securityContext.getAuthentication() instanceof AnonymousAuthenticationToken) {
            return null;
        }

        return (SessionDTO) securityContext.getAuthentication().getPrincipal();
    }
}
