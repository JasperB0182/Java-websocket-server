package org.renzojasper.javawebsocketserver.controllers;

import org.renzojasper.javawebsocketserver.dto.SessionDTO;
import org.renzojasper.javawebsocketserver.models.UserInfo;
import org.renzojasper.javawebsocketserver.services.UserInfoService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserInfoController {
    private final UserInfoService userInfoService;

    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping("/me")
    public UserInfo getMyUserInfo(@AuthenticationPrincipal SessionDTO sessionDTO) {
        return userInfoService.GetUserInfoForUserInfo(sessionDTO.getId());
    }
}
