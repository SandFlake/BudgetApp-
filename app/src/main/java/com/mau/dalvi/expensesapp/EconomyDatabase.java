package com.mau.dalvi.expensesapp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {IncomeTable.class, ExpensesTable.class}, version=2, exportSchema = false)
public abstract class EconomyDatabase extends RoomDatabase {

    public abstract EconDao databaseAccess();
}
