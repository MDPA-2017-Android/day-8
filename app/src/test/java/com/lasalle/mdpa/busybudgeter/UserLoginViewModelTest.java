package com.lasalle.mdpa.busybudgeter;

import com.lasalle.mdpa.busybudgeter.manager.UserManager;
import com.lasalle.mdpa.busybudgeter.view.model.UserLoginViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.MockitoJUnitRunner;

import javax.inject.Inject;

import toothpick.testing.ToothPickRule;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserLoginViewModelTest {
    @Rule
    public ToothPickRule toothPickRule = new ToothPickRule(this, "UserLoginViewModel");

    @Mock
    private UserManager userManager;

    @Inject UserLoginViewModel userLoginViewModel;

    @Before
    public void setupUserLoginViewModel() {
        toothPickRule.inject(this);
    }

    @Test
    public void checkUserNameIsForwardedProperly() throws Exception {
        userLoginViewModel.OnLoginUser("test","123456");
        verify(userManager, times(1)).loginUser(eq("test"), anyString());
    }

    @Test
    public void checkPasswordForwardedIsSha256Encrypted() throws Exception {
        userLoginViewModel.OnLoginUser("test","123456");
        verify(userManager, times(1)).loginUser(anyString(), eq("8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkUserNameAndPasswordAreNotNull() throws Exception {
        userLoginViewModel.OnLoginUser(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkUserNameAndPasswordAreNotEmpty() throws Exception {
        userLoginViewModel.OnLoginUser("", "");
    }

}