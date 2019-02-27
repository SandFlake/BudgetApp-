package com.mau.dalvi.expensesapp;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

@Entity
public class IncomeTable {

    @NonNull
    @PrimaryKey

    @ColumnInfo(name = "in_title")
    private String title;

    @ColumnInfo(name = "in_amount")
    private double amount;

    @ColumnInfo(name = "in_category")
    private String category;

    @ColumnInfo(name = "in_date")
    @TypeConverters({TimestampConverter.class})
    private String date;

    public IncomeTable( String title, double amount, String category, String date){
        this.title = title;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }


    public String getTitle(){
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
