package org.renzojasper.javawebsocketserver.controllers;

import org.renzojasper.javawebsocketserver.dto.LoginUserRequestDTO;
import org.renzojasper.javawebsocketserver.dto.LoginUserResponseDTO;
import org.renzojasper.javawebsocketserver.dto.RegisterUserRequestDTO;
import org.renzojasper.javawebsocketserver.services.UserDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserDataController {
    private final UserDataService userDataService;

    public UserDataController(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @PostMapping("/register")
    public ResponseEntity<LoginUserResponseDTO> registerAccount(@RequestBody RegisterUserRequestDTO registerUserRequestDTO) {
        return this.userDataService.registerUserData(registerUserRequestDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUserResponseDTO> login(@RequestBody LoginUserRequestDTO loginUserRequestDTO) {
        return this.userDataService.loginUserData(loginUserRequestDTO);
    }
}
