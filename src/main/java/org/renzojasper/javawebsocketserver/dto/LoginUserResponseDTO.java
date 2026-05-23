package org.renzojasper.javawebsocketserver.dto;

public class LoginUserResponseDTO {
    private final String username;
//    public String token;

    public LoginUserResponseDTO(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
