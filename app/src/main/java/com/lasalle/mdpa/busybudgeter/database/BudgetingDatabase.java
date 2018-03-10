package com.lasalle.mdpa.busybudgeter.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.lasalle.mdpa.busybudgeter.database.dao.BudgetDao;
import com.lasalle.mdpa.busybudgeter.database.entity.Budget;
import com.lasalle.mdpa.busybudgeter.database.entity.BudgetItem;

@Database(entities = {Budget.class, BudgetItem.class}, version = 1)
public abstract class BudgetingDatabase extends RoomDatabase {

    public abstract BudgetDao getBudgetDao();

}
