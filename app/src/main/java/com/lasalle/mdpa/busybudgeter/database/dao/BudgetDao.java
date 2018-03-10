package com.lasalle.mdpa.busybudgeter.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.lasalle.mdpa.busybudgeter.database.entity.Budget;

import java.util.List;

@Dao
public interface BudgetDao {

    @Query("Select * from budget order by name asc")
    LiveData<List<Budget>> getAll();

    @Insert
    void insert(Budget budget);

    @Update
    void update(Budget budget);

    @Delete
    void delete(Budget budget);

}
