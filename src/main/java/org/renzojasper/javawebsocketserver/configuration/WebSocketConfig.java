package org.renzojasper.javawebsocketserver.configuration;

import org.renzojasper.javawebsocketserver.handlers.SocketConnectionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // De url voor de websocket is ws://localhost:8080/api/chat
        // We moeten oppassen met de origins, we willen zegmaar NIET dat alle websites dit kunnen bereiken..
        registry
                .addHandler(new SocketConnectionHandler(), "/chat")
                .setAllowedOrigins("*");
    }
}
