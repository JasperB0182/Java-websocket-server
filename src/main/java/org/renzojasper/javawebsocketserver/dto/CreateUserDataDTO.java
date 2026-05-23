package org.renzojasper.javawebsocketserver.dto;

import org.renzojasper.javawebsocketserver.models.UserDetails;

public class CreateUserDataDTO {
    private String username;
    private String email;
    private String password;
    private UserDetails userDetails;

    public CreateUserDataDTO(String username, String email, String password, UserDetails userDetails) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.userDetails = userDetails;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }
}
