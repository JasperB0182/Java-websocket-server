package org.renzojasper.javawebsocketserver.dao;

import jakarta.persistence.EntityNotFoundException;
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

    public UserData getUserDataById(long id) {
        return userDataRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    public UserData getUserDataByUsername(String username) {
        return userDataRepository.findByUsername(username);
    }

    public void saveUserData(UserData userData) {
        userDataRepository.save(userData);
    }
}
