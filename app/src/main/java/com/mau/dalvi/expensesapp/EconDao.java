package com.mau.dalvi.expensesapp;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface EconDao {

    @Insert
    void insertIncomeTable(IncomeTable incomeTable);

    @Update
    void updateIncomeTable(IncomeTable incomeTable);

    @Delete
    void deleteIncomeTable(IncomeTable incomeTable);

    @Query("SELECT * FROM INCOMETABLE ORDER BY in_date desc")
    List<IncomeTable> fetchAllIncome();

    @Query("SELECT * FROM INCOMETABLE WHERE in_date BETWEEN :fromDate AND :toDate")
    List<IncomeTable> getIncomeForDate(String fromDate, String toDate);


    @Insert
    void insertExpensesTable (ExpensesTable expensesTable);

    @Update
    void updateExpensesTable (ExpensesTable expensesTable);

    @Delete
    void deleteExpensesTable (ExpensesTable expensesTable);

    @Query("SELECT * FROM EXPENSESTABLE ORDER BY ex_date desc")
    List<ExpensesTable> fetchAllExpenses();

    @Query("SELECT * FROM EXPENSESTABLE WHERE ex_date BETWEEN :fromDate AND :toDate")
    List<ExpensesTable> getExpensesForDate(String fromDate, String toDate);
}
