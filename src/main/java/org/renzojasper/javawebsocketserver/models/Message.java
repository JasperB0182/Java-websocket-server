package org.renzojasper.javawebsocketserver.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class Message {

    @Id
    private Long messageID;
    private String content;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private UserInfo sender;

    private LocalDateTime dateSent;

    public Message(String content, UserInfo sender, LocalDateTime dateSent) {
        this.content = content;
        this.sender = sender;
        this.dateSent = dateSent;
    }


    public Message() {

    }

    public Long getMessageID() {
        return messageID;
    }

    public void setMessageID(Long messageID) {
        this.messageID = messageID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserInfo getSender() {
        return sender;
    }

    public void setSender(UserInfo user) {
        this.sender = user;
    }

    public LocalDateTime getDateSent() {
        return dateSent;
    }

    public void setDateSent(LocalDateTime dateSent) {
        this.dateSent = dateSent;
    }
}
