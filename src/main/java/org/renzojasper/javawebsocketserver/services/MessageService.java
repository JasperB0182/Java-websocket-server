package org.renzojasper.javawebsocketserver.services;

import org.renzojasper.javawebsocketserver.dto.SessionDTO;
import org.renzojasper.javawebsocketserver.models.Message;
import org.renzojasper.javawebsocketserver.models.UserInfo;
import org.renzojasper.javawebsocketserver.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    private final UserInfoService userInfoService;

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository, UserInfoService userInfoService) {
        this.messageRepository = messageRepository;
        this.userInfoService = userInfoService;
    }

    public void saveMessage(SessionDTO session, String message) {
        UserInfo userInfo = userInfoService.GetUserInfoForUserInfo(session.getId());

        Message newMessage = new Message(message, userInfo, LocalDateTime.now());

        this.messageRepository.save(newMessage);

    }

    public List<Message> retrieveLast50Messages() {
        return this.messageRepository.findTop50ByOrderByMessageIDAsc();
    }
}
