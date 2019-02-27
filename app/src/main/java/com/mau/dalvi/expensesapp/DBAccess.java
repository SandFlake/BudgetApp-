package com.mau.dalvi.expensesapp;


import android.arch.persistence.room.Room;
import android.content.Context;
import java.util.List;

public class DBAccess {

    private static final String DATABASE_NAME = "MoneyDatabase";
    private EconomyDatabase database;
    private EconDao daoAccess;


    public DBAccess(Context context){
        database = Room.databaseBuilder(context,
                EconomyDatabase.class,
                DATABASE_NAME).fallbackToDestructiveMigration().build();
        daoAccess = database.databaseAccess();

    }

    public void insertIncomeTable(IncomeTable incomeTable){
        daoAccess.insertIncomeTable(incomeTable);
    }

    public void updateIncomeTable(IncomeTable incomeTable){
        daoAccess.updateIncomeTable(incomeTable);
    }

    public void deleteIncomeTable(IncomeTable incomeTable){
        daoAccess.deleteIncomeTable(incomeTable);
    }


    public List<IncomeTable> fetchAllIncome() { return daoAccess.fetchAllIncome();
    }

    public List<IncomeTable> getIncomeForDate(String fromDate, String toDate){
        return daoAccess.getIncomeForDate(fromDate, toDate);
    }

    public void insertExpensesTable(ExpensesTable expensesTable){
        daoAccess.insertExpensesTable(expensesTable);
    }

    public void updateExpensesTable(ExpensesTable expensesTable){
        daoAccess.updateExpensesTable(expensesTable);
    }

    public void deleteExpensesTable(ExpensesTable expensesTable){
        daoAccess.deleteExpensesTable(expensesTable);
    }

    public List<ExpensesTable>fetchAllExpenses(){
        return daoAccess.fetchAllExpenses();
    }

    public List<ExpensesTable>getExpensesForDate(String fromDate, String toDate){
        return daoAccess.getExpensesForDate(fromDate, toDate);
    }

}
