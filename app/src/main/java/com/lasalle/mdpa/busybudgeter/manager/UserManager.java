package com.lasalle.mdpa.busybudgeter.manager;

import com.lasalle.mdpa.busybudgeter.model.User;

public interface UserManager {

    void loginUser(String username, String password);

    void updateUserPassword(String oldPassword, String newPassword);

    User retrieveUserData();

}
