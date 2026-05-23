package org.renzojasper.javawebsocketserver.services;

import org.renzojasper.javawebsocketserver.dao.UserInfoDAO;
import org.renzojasper.javawebsocketserver.models.UserData;
import org.renzojasper.javawebsocketserver.models.UserInfo;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {
    private final UserInfoDAO userInfoDAO;

    public UserInfoService(UserInfoDAO userInfoDAO) {
        this.userInfoDAO = userInfoDAO;
    }

    public UserInfo CreateUserInfo() {
        UserInfo userInfo = new UserInfo();
        this.userInfoDAO.saveUserInfo(userInfo);

        return userInfo;
    }

    public void setUserDataForUserInfo(UserInfo userInfo, UserData userData) {
        this.userInfoDAO.setUserData(userInfo,userData);
    }
}
