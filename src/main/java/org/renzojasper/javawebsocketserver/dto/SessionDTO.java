package org.renzojasper.javawebsocketserver.dto;
import java.io.Serializable;

public class SessionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Long id;
    private final String username;

    public SessionDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
