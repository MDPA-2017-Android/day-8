package com.lasalle.mdpa.busybudgeter;

import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.android21buttons.fragmenttestrule.FragmentTestRule;
import com.lasalle.mdpa.busybudgeter.manager.UserManager;
import com.lasalle.mdpa.busybudgeter.model.User;
import com.lasalle.mdpa.busybudgeter.view.ContainerActivity;
import com.lasalle.mdpa.busybudgeter.view.HomeFragment;
import com.lasalle.mdpa.busybudgeter.view.SettingsFragment;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.config.Module;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
@MediumTest
public class SettingsFragmentTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Rule
    public FragmentTestRule<?, SettingsFragment> fragmentTestRule = FragmentTestRule.create(SettingsFragment.class, false, false);

    @Mock
    private UserManager userManagerMock;

    private BudgetingApplication application;
    private Scope applicationScope;

    @Before
    public void setup() {
        application = (BudgetingApplication) InstrumentationRegistry.getTargetContext().getApplicationContext();
        applicationScope = Toothpick.openScope(application);

        // We need to install the modules and launch the activity manually
        applicationScope.installTestModules(new Module() {{
            bind(UserManager.class).toInstance(userManagerMock);
        }});
    }

    @Test
    public void userInformationIsDisplayedProperly() {
        User mockedUser = new User("Pepe","Pepito", "testing", "asdfasfdasdf");
        when(userManagerMock.retrieveUserData()).thenReturn(mockedUser);

        // Launch fragment now
        fragmentTestRule.launchFragment(fragmentTestRule.getFragment());

        // Check stuff
        onView(withId(R.id.name)).check(matches(withText(mockedUser.getName())));
    }

    // TODO: add test case for when updating password and checking it's not the same as before

}
