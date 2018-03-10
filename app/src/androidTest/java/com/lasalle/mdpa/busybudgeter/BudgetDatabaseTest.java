package com.lasalle.mdpa.busybudgeter;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.runner.AndroidJUnit4;

import com.lasalle.mdpa.busybudgeter.database.BudgetingDatabase;
import com.lasalle.mdpa.busybudgeter.database.dao.BudgetDao;
import com.lasalle.mdpa.busybudgeter.database.entity.Budget;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static junit.framework.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
@MediumTest
public class BudgetDatabaseTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Mock
    private Observer<List<Budget>> budgetObserver;

    private BudgetDao budgetDao;
    private BudgetingDatabase budgetingDatabase;

    @Before
    public void CreateDatabase() {
        Context context = InstrumentationRegistry.getTargetContext();
        budgetingDatabase = Room.inMemoryDatabaseBuilder(context, BudgetingDatabase.class).allowMainThreadQueries().build();
        budgetDao = budgetingDatabase.getBudgetDao();
    }

    @After
    public void CloseDatabase() {
        budgetingDatabase.close();
    }

    @Test
    public void writeNewBudgetIntoDatabase() {
        Budget budget = new Budget();
        budget.setName("Manolete manolete");

        // Setup observer
        budgetDao.getAll().observeForever(budgetObserver);

        // Add items
        budgetDao.insert(budget);

        // Verify calls.
        ArgumentCaptor<List<Budget>> budgetListArgumentCaptor = ArgumentCaptor.forClass(List.class);

        // We add a times(2) when verifying because when we first do the observerForever the onChanged
        // method is getting called with the actual LiveData data, so it gets initialised properly.
        // Once we do the insert it gets called again with the new data and then we can make sure that
        // the items returned contain the element we actually created.
        verify(budgetObserver, times(2)).onChanged(budgetListArgumentCaptor.capture());

        assertEquals(budget.getName(), budgetListArgumentCaptor.getValue().get(0).getName());
    }

}
