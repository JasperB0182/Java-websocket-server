package org.renzojasper.javawebsocketserver.services;

import org.renzojasper.javawebsocketserver.dao.UserDataDAO;
import org.renzojasper.javawebsocketserver.dto.CreateUserDataDTO;
import org.renzojasper.javawebsocketserver.models.UserData;
import org.renzojasper.javawebsocketserver.models.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDataService {
    private final UserDataDAO userDataDAO;
    private final CredentialValidator credentialValidator;
    private final PasswordEncoder passwordEncoder;

    public UserDataService(UserDataDAO userDataDAO, CredentialValidator credentialValidator, PasswordEncoder passwordEncoder) {
        this.userDataDAO = userDataDAO;
        this.credentialValidator = credentialValidator;
        this.passwordEncoder = passwordEncoder;
    }

    public UserData findUserDataByEmailOrUsername(String emailOrUsername) {
        boolean isEmail = credentialValidator.isValidEmail(emailOrUsername);
        if (isEmail) {
            userDataDAO.getUserDataByEmail(emailOrUsername);
        }
        return userDataDAO.getUserDataByUsername(emailOrUsername);
    }

    private void createUserData(CreateUserDataDTO createUserDataDTO) {
        UserDetails userDetails = new UserDetails();

        String hashedPassword = passwordEncoder.encode(createUserDataDTO.getPassword());
        UserData userData = new UserData(createUserDataDTO.getUsername(), createUserDataDTO.getEmail(), hashedPassword, userDetails);

        userDataDAO.saveUserData(userData);
    }
}
