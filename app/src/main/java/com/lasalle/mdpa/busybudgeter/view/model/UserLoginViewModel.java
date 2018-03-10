package com.lasalle.mdpa.busybudgeter.view.model;

import android.arch.lifecycle.ViewModel;

import com.google.common.base.Charsets;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import com.lasalle.mdpa.busybudgeter.manager.UserManager;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.google.common.base.Preconditions.checkArgument;

@Singleton
public class UserLoginViewModel extends ViewModel {

    @Inject UserManager userManager;

    public void OnLoginUser(String username, String password) throws IllegalArgumentException {
        checkArgument(username != null && !username.isEmpty(), "Username parameter must not be null or empty");
        checkArgument(password != null && !password.isEmpty(), "Password parameter must not be null or empty");

        Hasher hasher = Hashing.sha256().newHasher();
        hasher.putString(password, Charsets.UTF_8);
        userManager.loginUser(username, hasher.hash().toString());
    }
}
