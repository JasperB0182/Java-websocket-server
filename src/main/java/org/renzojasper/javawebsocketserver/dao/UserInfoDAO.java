package org.renzojasper.javawebsocketserver.dao;

import org.renzojasper.javawebsocketserver.models.UserData;
import org.renzojasper.javawebsocketserver.models.UserInfo;
import org.renzojasper.javawebsocketserver.repositories.UserInfoRepository;
import org.springframework.stereotype.Component;

@Component
public class UserInfoDAO {
    private final UserInfoRepository userInfoRepository;

    public UserInfoDAO(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    public void saveUserInfo(UserInfo userInfo) {
        userInfoRepository.save(userInfo);
    }

    public void setUserData(UserInfo userInfo, UserData userData) {
        userInfo.setUserData(userData);
        saveUserInfo(userInfo);
    }
}
