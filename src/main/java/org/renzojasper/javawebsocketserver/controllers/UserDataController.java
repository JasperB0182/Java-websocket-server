package org.renzojasper.javawebsocketserver.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.renzojasper.javawebsocketserver.dto.LoginUserRequestDTO;
import org.renzojasper.javawebsocketserver.dto.LoginUserResponseDTO;
import org.renzojasper.javawebsocketserver.dto.RegisterUserRequestDTO;
import org.renzojasper.javawebsocketserver.services.UserDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserDataController {
    private final UserDataService userDataService;

    public UserDataController(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @PostMapping("/register")
    public ResponseEntity<LoginUserResponseDTO> registerAccount(@RequestBody RegisterUserRequestDTO registerUserRequestDTO, HttpServletRequest request) {
        return this.userDataService.registerUserData(registerUserRequestDTO, request);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUserResponseDTO> login(@RequestBody LoginUserRequestDTO loginUserRequestDTO, HttpServletRequest request) {
        return this.userDataService.loginUserData(loginUserRequestDTO, request);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        return userDataService.logoutUserData(request, response);
    }

    @GetMapping("/username")
    public ResponseEntity<Void> userNameAvailable(@RequestParam String username) {
        return userDataService.userNameAvailable(username);
    }
}
