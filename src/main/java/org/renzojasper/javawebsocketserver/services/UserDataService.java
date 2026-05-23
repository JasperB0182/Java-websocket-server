package org.renzojasper.javawebsocketserver.services;

import org.renzojasper.javawebsocketserver.dao.UserDataDAO;
import org.renzojasper.javawebsocketserver.dto.LoginUserRequestDTO;
import org.renzojasper.javawebsocketserver.dto.LoginUserResponseDTO;
import org.renzojasper.javawebsocketserver.dto.RegisterUserRequestDTO;
import org.renzojasper.javawebsocketserver.models.Role.Role;
import org.renzojasper.javawebsocketserver.models.UserData;
import org.renzojasper.javawebsocketserver.models.UserInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;

@Service
public class UserDataService implements UserDetailsService {
    private final UserDataDAO userDataDAO;
    private final UserInfoService userInfoService;
    private final CredentialValidator credentialValidator;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public UserDataService(UserDataDAO userDataDAO, UserInfoService userInfoService, CredentialValidator credentialValidator, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userDataDAO = userDataDAO;
        this.userInfoService = userInfoService;
        this.credentialValidator = credentialValidator;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    public UserDetails loadUserByUsername(String username) {
        UserData userData = userDataDAO.getUserDataByUsername(username);

        return new User(userData.getUsername(), userData.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(userData.getRole().getName().toString())));
    }

    public ResponseEntity<LoginUserResponseDTO> registerUserData(RegisterUserRequestDTO registerUserRequestDTO) {
        validateEmail(registerUserRequestDTO.email);
        validateUsername(registerUserRequestDTO.username);
        validateUniqueEmail(registerUserRequestDTO.email);
        validateUniqueUsername(registerUserRequestDTO.username);
        validatePassword(registerUserRequestDTO.password);

        createUserData(registerUserRequestDTO);

        // generate token

        LoginUserResponseDTO loginResponse = new LoginUserResponseDTO(registerUserRequestDTO.username); // add token in loginResponseDTO
        return ResponseEntity.ok(loginResponse);
    }

    public ResponseEntity<LoginUserResponseDTO> loginUserData(LoginUserRequestDTO loginUserRequestDTO) {
        try {
            UserData userData = findUserDataByEmailOrUsername(loginUserRequestDTO.emailOrUsername);

            if (userData == null || !passwordEncoder.matches(loginUserRequestDTO.password, userData.getPassword())) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No valid credentials");
            }

            // generate token

            LoginUserResponseDTO loginResponse = new LoginUserResponseDTO(userData.getUsername()); // add token in loginResponseDTO
            return ResponseEntity.ok(loginResponse);
        } catch (AuthenticationException authExc) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "No valid credentials"
            );
        }
    }

    public UserData findUserDataByEmailOrUsername(String emailOrUsername) {
        boolean isEmail = credentialValidator.isValidEmail(emailOrUsername);
        if (isEmail) {
            return userDataDAO.getUserDataByEmail(emailOrUsername.toLowerCase());
        }
        return userDataDAO.getUserDataByUsername(emailOrUsername);
    }

    private void createUserData(RegisterUserRequestDTO registerUserRequestDTO) {
        UserInfo userInfo = userInfoService.CreateUserInfo();
        String hashedPassword = passwordEncoder.encode(registerUserRequestDTO.password);
        Role userRole = roleService.getRoleByName("ROLE_USER");

        UserData userData = new UserData(registerUserRequestDTO.username, registerUserRequestDTO.email.toLowerCase(), hashedPassword, userInfo, userRole);
        userDataDAO.saveUserData(userData);
        userInfoService.setUserDataForUserInfo(userInfo, userData);
    }

    private void validateEmail(String email) {
        if (!credentialValidator.isValidEmail(email)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No valid email provided");
        }
    }

    private void validateUsername(String username) {
        if (!credentialValidator.isValidUsername(username)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No username provided");
        }
    }

    private void validatePassword(String password) {
        if (!credentialValidator.isValidPassword(password)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No valid password provided");
        }
    }

    private void validateUniqueEmail(String email) {
        if (userDataDAO.getUserDataByEmail(email) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "No valid email provided");
        }
    }

    private void validateUniqueUsername(String username) {
        if (userDataDAO.getUserDataByUsername(username) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "No valid username provided, username is already in use");
        }
    }
}
