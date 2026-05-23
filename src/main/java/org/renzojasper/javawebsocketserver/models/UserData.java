package org.renzojasper.javawebsocketserver.models;

import jakarta.persistence.*;
import org.apache.catalina.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_data")
public class UserData {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToOne
    private UserDetails userDetails;

    public UserData() {}

    public UserData(String username, String email, String password, UserDetails userDetails) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.userDetails = userDetails;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
