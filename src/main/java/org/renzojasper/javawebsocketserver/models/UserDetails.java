package org.renzojasper.javawebsocketserver.models;

import jakarta.persistence.*;
import org.apache.catalina.User;

import java.time.LocalDateTime;

@Entity
public class UserDetails {
    @Id
    @GeneratedValue
    private long id;

    private String nickname;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToOne
    private UserData userData;

    public UserDetails(String nickname) {
        this.nickname = nickname;
    }

    public UserDetails() {
    }
}
