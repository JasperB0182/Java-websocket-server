package org.renzojasper.javawebsocketserver.dao;

import org.renzojasper.javawebsocketserver.dto.CreateUserDataDTO;
import org.renzojasper.javawebsocketserver.dto.RegisterUserDTO;
import org.renzojasper.javawebsocketserver.models.UserData;
import org.renzojasper.javawebsocketserver.repositories.UserDataRepository;
import org.springframework.stereotype.Component;

@Component
public class UserDataDAO {
    private final UserDataRepository userDataRepository;

    public UserDataDAO(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    public UserData getUserDataByEmail(String email) {
        return userDataRepository.findByEmail(email);
    }

    public UserData getUserDataByUsername(String username) {
        return userDataRepository.findByUsername(username);
    }

    public void saveUserData(UserData userData) {
        userDataRepository.save(userData);
    }
}
