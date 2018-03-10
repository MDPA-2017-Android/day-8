package com.lasalle.mdpa.busybudgeter;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.runner.AndroidJUnit4;

import com.android21buttons.fragmenttestrule.FragmentTestRule;
import com.lasalle.mdpa.busybudgeter.database.BudgetingDatabase;
import com.lasalle.mdpa.busybudgeter.database.dao.BudgetDao;
import com.lasalle.mdpa.busybudgeter.manager.UserManager;
import com.lasalle.mdpa.busybudgeter.view.HomeFragment;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.config.Module;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@MediumTest
public class AddingNewBudgetFlowTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Rule
    public FragmentTestRule<?, HomeFragment> fragmentTestRule = FragmentTestRule.create(HomeFragment.class, false, false);

    private BudgetDao budgetDao;
    private BudgetingDatabase budgetingDatabase;

    private static BudgetingApplication application;
    private static Scope applicationScope;

    @Before
    public void SetupTest() {
        // Create Database
        Context context = InstrumentationRegistry.getTargetContext();
        budgetingDatabase = Room.inMemoryDatabaseBuilder(context, BudgetingDatabase.class).allowMainThreadQueries().build();
        budgetDao = budgetingDatabase.getBudgetDao();

        // Inject database into application
        application = (BudgetingApplication) InstrumentationRegistry.getTargetContext().getApplicationContext();
        applicationScope = Toothpick.openScope(application);

        // We need to install the modules and launch the activity manually
        applicationScope.installTestModules(new Module() {{
            bind(BudgetingDatabase.class).toInstance(budgetingDatabase);
        }});

        // Start fragment
        fragmentTestRule.launchFragment(fragmentTestRule.getFragment());
    }

    @After
    public void CloseEverything() {
        Toothpick.reset(applicationScope);
        application.installToothPickModules(applicationScope);
        budgetingDatabase.close();
    }

    @Test
    public void addSingleItemAndVerifyTheyIsDisplayed() {
        // Click add button
        onView(withId(R.id.add_new_budget_button)).perform(click());

        // Fill form with budget name
        onView(withId(R.id.add_budget_name)).perform(typeText("Item"), closeSoftKeyboard());
        onView(withId(R.id.add_budget_submit)).perform(click());

        // Check list view contains item
        onView(withText("Item")).check(matches(isDisplayed()));
    }

    @Test
    public void addMultipleItemsAndVerifyTheyAreDisplayed() {
        for(int i = 0; i < 3; ++i)
        {
            String itemName = "Item_"+i;

            // Click add button
            onView(withId(R.id.add_new_budget_button)).perform(click());

            // Fill form with budget name
            onView(withId(R.id.add_budget_name)).perform(typeText(itemName), closeSoftKeyboard());
            onView(withId(R.id.add_budget_submit)).perform(click());

            // Check list view contains item
            onView(withText(itemName)).check(matches(isDisplayed()));
        }

    }
}
