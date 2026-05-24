package org.renzojasper.javawebsocketserver.services;

import org.renzojasper.javawebsocketserver.dao.UserDataDAO;
import org.renzojasper.javawebsocketserver.dao.UserInfoDAO;
import org.renzojasper.javawebsocketserver.models.UserData;
import org.renzojasper.javawebsocketserver.models.UserInfo;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {
    private final UserInfoDAO userInfoDAO;
    private final UserDataDAO userDataDAO;

    public UserInfoService(UserInfoDAO userInfoDAO, UserDataDAO userDataDAO) {
        this.userInfoDAO = userInfoDAO;
        this.userDataDAO = userDataDAO;
    }

    public UserInfo CreateUserInfo() {
        UserInfo userInfo = new UserInfo();
        this.userInfoDAO.saveUserInfo(userInfo);

        return userInfo;
    }

    public void setUserDataForUserInfo(UserInfo userInfo, UserData userData) {
        this.userInfoDAO.setUserData(userInfo,userData);
    }

    public UserInfo GetUserInfoForUserInfo(Long userId) {
        UserData userData = userDataDAO.getUserDataById(userId);
        return userData.getUserInfo();
    }
}
