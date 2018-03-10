package com.lasalle.mdpa.busybudgeter;

import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.lasalle.mdpa.busybudgeter.view.ContainerActivity;
import com.lasalle.mdpa.busybudgeter.view.HomeFragment;
import com.lasalle.mdpa.busybudgeter.view.SettingsFragment;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
@MediumTest
public class ContainerActivityTest {

    @Rule
    public ActivityTestRule<ContainerActivity> containerActivityRule = new ActivityTestRule<>(ContainerActivity.class);

    @Test
    public void checkFirstFragmentLoadedIsHome() {
        FragmentManager fragment = containerActivityRule.getActivity().getSupportFragmentManager();
        Fragment currentFragment = fragment.findFragmentById(R.id.fragment_container);

        assertThat(currentFragment, instanceOf(HomeFragment.class));
    }

    @Test
    public void clickingSettingsOptionLoadsProperFragment() {
        onView(withId(R.id.navigation_settings)).perform(click());

        FragmentManager fragment = containerActivityRule.getActivity().getSupportFragmentManager();
        Fragment currentFragment = fragment.findFragmentById(R.id.fragment_container);

        assertThat(currentFragment, instanceOf(SettingsFragment.class));
    }

}
