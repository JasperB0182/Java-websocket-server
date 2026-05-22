package org.renzojasper.javawebsocketserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaWebsocketServerApplication {
    // De url voor de websocket is ws://localhost:8080/api/chat

    public static void main(String[] args) {
        SpringApplication.run(JavaWebsocketServerApplication.class, args);
    }

}
